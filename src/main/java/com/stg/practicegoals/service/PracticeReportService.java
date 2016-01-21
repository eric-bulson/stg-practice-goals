package com.stg.practicegoals.service;

import org.springframework.stereotype.Service;

import com.stg.practicegoals.dao.PracticeReportDao;
import com.stg.practicegoals.model.PracticeReport;

@Service
public class PracticeReportService {

	private PracticeReportDao practiceReportDao;

	public PracticeReport getPracticeReport() {
		return practiceReportDao.getPracticeReport();
	}
}
