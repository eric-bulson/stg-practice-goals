package com.stg.practicegoals.microservices.practices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.practicegoals.dao.PracticeReportDao;
import com.stg.practicegoals.model.GoalsReport;
import com.stg.practicegoals.model.ProgressReport;

@Service
public class PracticeService {

	@Autowired
	private PracticeDao practiceDao;

	public GoalsReport getGoalsReport() {
		return practiceDao.getGoalsReport();
	}

	public Boolean postProgressReport(ProgressReport progressReport) {
		validateProgressReport(progressReport);
		return practiceDao.postProgressReport(progressReport);
	}

	private void validateProgressReport(ProgressReport progressReport) {
		// TODO Auto-generated method stub
		
	}

	public Map<Long, String> getPractices() {
		return practiceDao.getPractices();
	}
}
