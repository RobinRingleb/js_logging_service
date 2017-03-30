package de.mytoysgroup.multishop.logging.controller;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * A controller with RESTful endpoints mostly used by the frontend.
 *
 * @author Robin Ringleb (2017)
 */
@RestController
@Api(value = "logging")
public class ShowStatusController {
	/**
	 * Rest endpoint returning a status message.
	 *
	 * @return a status message
	 */
	@ApiOperation(value = "Returns a status-message")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(method = RequestMethod.GET, value = "/show/status", produces = TEXT_PLAIN_VALUE + ";charset=UTF-8")
	public String getStatus() {
		return "I'm fine!";
	}
}