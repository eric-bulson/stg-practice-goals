package com.stg.practicegoals.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgressReport {

	private Long practiceId;
	private Long typeId;
	private Long actual;
	private Long expected;
	private Long timeSpent;
	private String notes;

	public ProgressReport(ProgressReport practiceReport) {
	}

	@JsonCreator
	public ProgressReport(@JsonProperty(value = "practiceId", required = true) Long practiceId,
			@JsonProperty(value = "typeId", required = true) Long typeId,
			@JsonProperty(value = "actual", required = false) Long actual,
			@JsonProperty(value = "expected", required = false) Long expected,
			@JsonProperty(value = "timeSpent", required = false) Long timeSpent,
			@JsonProperty(value = "notes", required = false) String notes) {
		this.practiceId = new Long(practiceId);
		this.typeId = new Long(typeId);
		this.actual = actual != null ? new Long(actual) : null;
		this.expected = expected != null ? new Long(expected) : null;
		this.timeSpent = timeSpent != null ?new Long(timeSpent):0L;
		this.notes = notes != null ? new String(notes) : "";
	}

	public Long getPracticeId() {
		return new Long(practiceId);
	}

	public Long getTypeId() {
		return new Long(typeId);
	}

	public Long getActual() {
		return new Long(actual);
	}

	public Long getExpected() {
		return new Long(expected);
	}

	public Long getTimeSpent() {
		return new Long(timeSpent);
	}

	public String getNotes() {
		return new String(notes);
	}
}
