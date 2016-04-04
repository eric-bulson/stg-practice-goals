package com.stg.practicegoals.microservices.training;

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
public class TrainingDao {

	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Map<Long, String> getTrainingTypes() {
		String sql = 
			"SELECT training_type.id, " +
			"    training_type.type " +
			"FROM training_type";

		return namedJdbcTemplate.query(sql, new ResultSetExtractor<Map<Long, String>>() {

			@Override
			public Map<Long, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Long, String> result = new HashMap<>();

				while(rs.next()){
					result.put(rs.getLong("id"), rs.getString("type"));
				}
				return result;
			}
		});
	}

}
