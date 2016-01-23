package com.stg.practicegoals.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgressReport {

	private Long actual;
	private Long expected;
	private Long timeSpent;
	
	@JsonCreator
	public ProgressReport(ProgressReport practiceReport){
		
	}
	
	public ProgressReport(@JsonProperty(value="actual", required=true) Long actual, 
			@JsonProperty(value="expected", required=true) Long expected,
			@JsonProperty(value="timeSpent", required=true) Long timeSpent) {
		this.actual = new Long(actual);
		this.expected = new Long(expected);
		this.timeSpent = new Long(timeSpent);
	}
	
	public Long getActual() {
		return new Long(actual);
	}
	public Long getExpected() {
		return new Long(expected);
	}
	public Long getTimeSpent(){
		return new Long(timeSpent);
	}
}
