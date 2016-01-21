package com.stg.practicegoals.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stg.practicegoals.model.PracticeReport;

@Repository
public class PracticeReportDao {
	
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	public PracticeReport getPracticeReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
