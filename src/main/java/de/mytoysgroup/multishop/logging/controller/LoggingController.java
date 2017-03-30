package de.mytoysgroup.multishop.logging.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.mytoysgroup.multishop.logging.model.ErrorCount;
import de.mytoysgroup.multishop.logging.model.ErrorInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * A controller with RESTful endpoints mostly used by the frontend.
 *
 * @author Robin Ringleb (2017)
 */
@Controller
@Api(value = "logging")
public class LoggingController {
	List<ErrorInformation> errorList = new ArrayList<ErrorInformation>();

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	/**
	 * Rest endpoint receiving error-data.
	 *
	 * @param errorData a <code>String</code> with error-data
	 * @return only receiving, no return
	 */
	@ApiOperation(value = "Receives error-data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(method = RequestMethod.PUT, value = "/jserrors")
	public @ResponseBody String getJserrors(
			@ApiParam(name = "file", value = "A text to return") @RequestBody String errorData) {
		try {
			Gson gson = new GsonBuilder().create();
			ErrorInformation errori = gson.fromJson(errorData, ErrorInformation.class);
			errori.initError();
			LOGGER.error(errori.toString());
			errorList.add(errori);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return " - Request - ";
	}

	/**
	 * Rest endpoint returning a sorted error-list.
	 *
	 * @return a sorted error-list
	 */
	@ApiOperation(value = "Returns a sorted error-list")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/show/list")
	public String showList(Model model,
			@RequestParam(value = "max", required = false, defaultValue = "20") final int max) {

		Collections.sort(errorList);

		StringBuffer Liste = new StringBuffer("");
		Iterator<ErrorInformation> it = errorList.iterator();

		int i = 0;
		while (it.hasNext() & i < max) {
			i++;
			Liste = Liste.append(it.next().printShort());
		}
		model.addAttribute("listName", Liste.toString());
		return "showList";
	}

	/**
	 * freemarker table test.
	 *
	 * @return a freemarker template
	 */
	@ApiOperation(value = "Returns a status-message")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/show/table/browser")
	public String showTable(Model model) {

		// Calculate some Counters
		ErrorCount counter = new ErrorCount(errorList);
		
		// Set the model attributes for the table template
		model.addAttribute("countChromeAll", 			counter.getCountBrowser("Chrome")); 
		model.addAttribute("countFirefoxAll", 			counter.getCountBrowser("Firefox"));
		model.addAttribute("countIEAll", 				counter.getCountBrowser("IE"));
		model.addAttribute("countUnknownBrowserAll", 	counter.getCountBrowser("unknown Browser"));
		
		model.addAttribute("countChromeStatic", 		counter.getCountPageTypeForBrowser("static", "Chrome")); 
		model.addAttribute("countFirefoxStatic", 		counter.getCountPageTypeForBrowser("static", "Firefox"));
		model.addAttribute("countIEStatic", 			counter.getCountPageTypeForBrowser("static", "IE"));
		model.addAttribute("countUnknownBrowserStatic", counter.getCountPageTypeForBrowser("static", "unknown Browser"));
		
		model.addAttribute("countChromePdp", 			counter.getCountPageTypeForBrowser("pdp", "Chrome")); 
		model.addAttribute("countFirefoxPdp", 			counter.getCountPageTypeForBrowser("pdp", "Firefox"));
		model.addAttribute("countIEPdp", 				counter.getCountPageTypeForBrowser("pdp", "IE"));
		model.addAttribute("countUnknownBrowserPdp", 	counter.getCountPageTypeForBrowser("pdp", "unknown Browser"));
		
		model.addAttribute("countChromeSuche", 			counter.getCountPageTypeForBrowser("suche", "Chrome")); 
		model.addAttribute("countFirefoxSuche", 		counter.getCountPageTypeForBrowser("suche", "Firefox"));
		model.addAttribute("countIESuche", 				counter.getCountPageTypeForBrowser("suche", "IE"));
		model.addAttribute("countUnknownBrowserSuche", 	counter.getCountPageTypeForBrowser("suche", "unknown Browser"));
		
		model.addAttribute("countChromePop", 			counter.getCountPageTypeForBrowser("pop", "Chrome")); 
		model.addAttribute("countFirefoxPop", 			counter.getCountPageTypeForBrowser("pop", "Firefox"));
		model.addAttribute("countIEPop", 				counter.getCountPageTypeForBrowser("pop", "IE"));
		model.addAttribute("countUnknownBrowserPop",	counter.getCountPageTypeForBrowser("pop", "unknown Browser"));
		
		model.addAttribute("countChromeStart", 			counter.getCountPageTypeForBrowser("start", "Chrome")); 
		model.addAttribute("countFirefoxStart", 		counter.getCountPageTypeForBrowser("start", "Firefox"));
		model.addAttribute("countIEStart", 				counter.getCountPageTypeForBrowser("start", "IE"));
		model.addAttribute("countUnknownBrowserStart", 	counter.getCountPageTypeForBrowser("start", "unknown Browser"));
		
		// render the template
		return "showTableBrowser";
	} 
}