package com.stg.practicegoals.microservices.training;

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
public class TrainingController {

	protected Logger logger = Logger.getLogger(TrainingController.class
			.getName());
	@Autowired
	private TrainingService trainingService;
	
	@RequestMapping(value = "/trainingtypes", method = RequestMethod.GET)
	public Map<Long, String> getTrainingTypes() {
		return trainingService.getTrainingTypes();
	}
}
