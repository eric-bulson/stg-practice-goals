package com.stg.practicegoals.dao;

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
public class PracticeReportDao {

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
					"from stg_career_stats.training_record tr "	+ 
					"join stg_career_stats.training_goals tg on tr.type = tg.type "	+ 
					"join stg_career_stats.practice p on tr.practice = p.id " + 
					"join stg_career_stats.training_type tt on tr.type = tt.id " + 
					"group by tr.practice, tr.type";
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
								new ProgressReport(rs.getLong("actual"), rs.getLong("expected"),
										rs.getLong("totalTimeInSeconds")));
					} else {
						HashMap<String, ProgressReport> newMap = new HashMap<>();
						newMap.put(rs.getString("typeName"), new ProgressReport(rs.getLong("actual"),
								rs.getLong("expected"), rs.getLong("totalTimeInSeconds")));
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
//		GoalsReport result = namedJdbcTemplate.execute(sql,
//				new PreparedStatementCallback<GoalsReport>() {
//
//					@Override
//					public GoalsReport doInPreparedStatement(PreparedStatement ps)
//							throws SQLException, DataAccessException {
//
//						Map<String, Map<String, ProgressReport>> goalsMap = new HashMap<>();
//
//						ResultSet rs = ps.getResultSet();
//						if (rs == null){
//							throw new RuntimeException("No results were returned.");
//						}
//						while (rs.next()) {
//							String practiceName = rs.getString("practiceName");
//							if (goalsMap.containsKey(practiceName)) {
//								goalsMap.get(practiceName).put(rs.getString("typeName"),
//										new ProgressReport(rs.getLong("actual"), rs.getLong("expected"),
//												rs.getLong("totalTimeInSeconds")));
//							} else {
//								HashMap<String, ProgressReport> newMap = new HashMap<>();
//								newMap.put(rs.getString("typeName"), new ProgressReport(rs.getLong("actual"),
//										rs.getLong("expected"), rs.getLong("totalTimeInSeconds")));
//								goalsMap.put(practiceName, newMap);
//							}
//						}
//
//						Map<String, PracticeReport> practiceReports = new HashMap<>();
//						for (String key : goalsMap.keySet()) {
//							practiceReports.put(key, new PracticeReport(goalsMap.get(key)));
//						}
//						GoalsReport result = new GoalsReport(practiceReports);
//						return result;
//					}
//				});
//
		return result;
	}
}
