package com.stg.practicegoals.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.practicegoals.dao.PracticeReportDao;
import com.stg.practicegoals.model.GoalsReport;
import com.stg.practicegoals.model.ProgressReport;

@Service
public class PracticeReportService {

	@Autowired
	private PracticeReportDao practiceReportDao;

	public GoalsReport getGoalsReport() {
		return practiceReportDao.getGoalsReport();
	}

	public Boolean postProgressReport(ProgressReport progressReport) {
		validateProgressReport(progressReport);
		return practiceReportDao.postProgressReport(progressReport);
	}

	private void validateProgressReport(ProgressReport progressReport) {
		// TODO Auto-generated method stub
		
	}

	public Map<Long, String> getPractices() {
		return practiceReportDao.getPractices();
	}
	
	public Map<Long, String> getTrainingTypes() {
		return practiceReportDao.getTrainingTypes();
	}
}
