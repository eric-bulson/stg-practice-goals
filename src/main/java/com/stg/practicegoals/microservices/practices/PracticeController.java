package com.stg.practicegoals.microservices.practices;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stg.practicegoals.model.GoalsReport;
import com.stg.practicegoals.model.ProgressReport;

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

	@RequestMapping(value = "/practices/goalsreport", method = RequestMethod.GET)
	public GoalsReport getPracticeReport() {
		return practiceService.getGoalsReport();
	}
	
	@RequestMapping(value = "/practices", method = RequestMethod.GET)
	public Map<Long, String> getPractices() {
		return practiceService.getPractices();
	}
	
	@RequestMapping(value = "/practices/progressreport", method = RequestMethod.POST)
	public Boolean postProgressReport(@RequestBody ProgressReport progressReport){
		return practiceService.postProgressReport(progressReport);
	}
}
