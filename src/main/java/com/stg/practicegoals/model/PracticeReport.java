package com.stg.practicegoals.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PracticeReport {

	private Long actual;
	private Long expected;
	
	@JsonCreator
	public PracticeReport(PracticeReport practiceReport){
		
	}
	
	public PracticeReport(@JsonProperty(value="actual", required=true) Long actual, 
			@JsonProperty(value="expected", required=true) Long expected) {
		this.actual = new Long(actual);
		this.expected = new Long(expected);
	}
	
	public Long getActual() {
		return new Long(actual);
	}
	public Long getExpected() {
		return new Long(expected);
	}
}
