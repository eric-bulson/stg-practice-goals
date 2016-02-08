package com.stg.practicegoals.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stg.practicegoals.model.GoalsReport;
import com.stg.practicegoals.model.ProgressReport;
import com.stg.practicegoals.service.PracticeReportService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PracticeGoalsController {

	@Autowired
	private PracticeReportService practiceReportService;

	@RequestMapping(value = "/practiceresults", method = RequestMethod.GET)
	public GoalsReport getPracticeReport() {
		return practiceReportService.getGoalsReport();
	}

	@RequestMapping(value = "/someotherapi/practices", method = RequestMethod.GET)
	public Map<Long, String> getPractices(){
		return practiceReportService.getPractices();
	}
	
	@RequestMapping(value = "/someotherapi/trainingtypes", method = RequestMethod.GET)
	public Map<Long, String> getTrainingTypes(){
		return practiceReportService.getTrainingTypes();
	}
	
	
	@RequestMapping(value = "/someotherapi/goal", method = RequestMethod.POST)
	public Boolean postProgressReport(@RequestBody ProgressReport progressReport){
		return practiceReportService.postProgressReport(progressReport);
	}
}
