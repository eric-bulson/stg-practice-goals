package com.stg.practicegoals.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PracticeReport {
	Map<String, ProgressReport> reports;
	

	@JsonCreator
	public PracticeReport(@JsonProperty(value="reports", required = true) Map<String, ProgressReport> reports){
		this.reports = new HashMap<>(reports);
	}

	public Map<String, ProgressReport> getReports() {
		return new HashMap<>(reports);
	}
}
