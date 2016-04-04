package com.stg.practicegoals.microservices.practices;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stg.practicegoals.model.GoalsReport;

/**
 * A RESTFul controller for accessing practice information.
 * 
 * @author Eric Bulson
 */
@RestController
public class PracticeController {

	protected Logger logger = Logger.getLogger(PracticeController.class
			.getName());
	@Autowired
	private PracticeService practiceService;

	@RequestMapping(value = "/practiceresults", method = RequestMethod.GET)
	public GoalsReport getPracticeReport() {
		return practiceService.getGoalsReport();
	}
	
	@RequestMapping(value = "/practices", method = RequestMethod.GET)
	public Map<Long, String> getPractices() {
		return practiceService.getPractices();
	}
}
