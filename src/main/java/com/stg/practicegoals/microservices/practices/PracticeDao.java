package com.stg.practicegoals.microservices.practices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stg.practicegoals.model.GoalsReport;
import com.stg.practicegoals.model.PracticeReport;
import com.stg.practicegoals.model.ProgressReport;

@Repository
public class PracticeDao {

	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public GoalsReport getGoalsReport() {
		String sql = "select " + 
					"tr.type, " + 
					"tt.type typeName, " + 
					"tr.practice, "	+ 
					"p.name practiceName, " + 
					"count(*) actual, " + 
					"sum(time_spent_in_seconds) totalTimeInSeconds, " + 
					"tg.goal expected " + 
					"from training_record tr "	+ 
					"join training_goals tg on tr.type = tg.type "	+ 
					"join practice p on tr.practice = p.id " + 
					"join training_type tt on tr.type = tt.id " + 
					"group by tr.practice, tr.type, tt.type, p.name, tg.goal";
		GoalsReport result = namedJdbcTemplate.query(sql, new ResultSetExtractor<GoalsReport>() {

			@Override
			public GoalsReport extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, Map<String, ProgressReport>> goalsMap = new HashMap<>();

				if (rs == null){
					throw new RuntimeException("No results were returned.");
				}
				while (rs.next()) {
					String practiceName = rs.getString("practiceName");
					if (goalsMap.containsKey(practiceName)) {
						goalsMap.get(practiceName).put(rs.getString("typeName"),
								new ProgressReport(rs.getLong("practice"),rs.getLong("type"),rs.getLong("actual"), rs.getLong("expected"),rs.getLong("totalTimeInSeconds"), null));
					} else {
						HashMap<String, ProgressReport> newMap = new HashMap<>();
						newMap.put(rs.getString("typeName"), new ProgressReport(rs.getLong("practice"), rs.getLong("type"),rs.getLong("actual"),
								rs.getLong("expected"), rs.getLong("totalTimeInSeconds"), null));
						goalsMap.put(practiceName, newMap);
					}
				}

				Map<String, PracticeReport> practiceReports = new HashMap<>();
				for (String key : goalsMap.keySet()) {
					practiceReports.put(key, new PracticeReport(goalsMap.get(key)));
				}
				GoalsReport result = new GoalsReport(practiceReports);
				return result;
			}
		});
		return result;
	}

	public Boolean postProgressReport(ProgressReport progressReport) {
		String sql = "INSERT INTO training_record " +
				"(type, " +
				"practice, " +
				"notes, " +
				"time_spent_in_seconds) " +
				"VALUES " +
				"(:type, " +
				":practice, " +
				":notes, " +
				":time_spent_in_seconds)";
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", progressReport.getTypeId());
		parameters.put("practice", progressReport.getPracticeId());
		parameters.put("notes", progressReport.getNotes());
		parameters.put("time_spent_in_seconds", progressReport.getTimeSpent());
		
		int numberRowesAffected = namedJdbcTemplate.update(sql, parameters);
		
		return numberRowesAffected == 1;
	}

	public Map<Long, String> getPractices() {
		String sql = 
			"SELECT practice.id, " +
			"    practice.name " +
			"FROM practice";
		
		return namedJdbcTemplate.query(sql, new ResultSetExtractor<Map<Long, String>>(){

			@Override
			public Map<Long, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Long, String> result = new HashMap<>();

				while(rs.next()){
					result.put(rs.getLong("id"), rs.getString("name"));
				}
				return result;
			}
			
		});
	}

}
