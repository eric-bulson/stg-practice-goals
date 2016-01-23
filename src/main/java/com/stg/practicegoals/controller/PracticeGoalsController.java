package com.stg.practicegoals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.practicegoals.model.GoalsReport;
import com.stg.practicegoals.service.PracticeReportService;

@RestController
@RequestMapping("/api/v1")
public class PracticeGoalsController {

	@Autowired
	private PracticeReportService practiceReportService;

	@RequestMapping("/practiceresults")
	public GoalsReport getPracticeReport() {
		return practiceReportService.getGoalsReport();
	}

}
