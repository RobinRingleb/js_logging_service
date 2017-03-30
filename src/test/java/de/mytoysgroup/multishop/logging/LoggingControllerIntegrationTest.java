package de.mytoysgroup.multishop.logging;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Integration tests for REST endpoints of {@link de.mytoysgroup.multishop.logging.controller.LoggingController}
 * <p>
 * Hint: Good introduction into testing with Spring Boot 1.4
 * https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
 *
 * @author Robin Ringleb (2017)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoggingControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}

	/**
	 * Tests the endpoints that GETs a text
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetText() throws Exception {
		// Get text
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/show/status", String.class,
				"Hello");

		// Compare that both BisRequestEntitys have the same values
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody()).isEqualTo("I'm fine!");
	}

}
