package com.stg.practicegoals.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GoalsReport {
	private Map<String, PracticeReport> goals;

	@JsonCreator
	public GoalsReport(@JsonProperty(value="goals", required=true) Map<String, PracticeReport> goals){
		this.goals = new HashMap<>(goals);
	}
	
	public Map<String, PracticeReport> getGoals(){
		return new HashMap<>(goals);
	}
	
}
