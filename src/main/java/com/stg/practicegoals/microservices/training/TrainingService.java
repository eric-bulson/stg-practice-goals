package com.stg.practicegoals.microservices.training;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.practicegoals.dao.PracticeReportDao;
import com.stg.practicegoals.model.GoalsReport;
import com.stg.practicegoals.model.ProgressReport;

@Service
public class TrainingService {

	@Autowired
	private TrainingDao practiceDao;

	public Map<Long, String> getTrainingTypes() {
		return practiceDao.getTrainingTypes();
	}
}
