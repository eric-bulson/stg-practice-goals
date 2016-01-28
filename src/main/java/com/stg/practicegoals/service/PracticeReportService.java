package com.stg.practicegoals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.practicegoals.dao.PracticeReportDao;
import com.stg.practicegoals.model.GoalsReport;

@Service
public class PracticeReportService {

	@Autowired
	private PracticeReportDao practiceReportDao;

	public GoalsReport getGoalsReport() {
		return practiceReportDao.getGoalsReport();
	}
}
