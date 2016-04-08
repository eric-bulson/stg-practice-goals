package com.stg.practicegoals.microservices.practices;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The accounts web-application. This class has two uses:
 * <ol>
 * <li>Provide configuration and setup for {@link PracticeServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link PracticeServer} instead.
 * 
 */
@SpringBootApplication
public class PracticeWebApplication {

	protected Logger logger = Logger.getLogger(PracticeWebApplication.class
			.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PracticeWebApplication.class, args);
	}

	/**
	 * Creates an in-memory "practice" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.addScript("classpath:db/migrations/V1__stg_career_stats_practice.sql")
		.addScript("classpath:db/migrations/V2__stg_career_stats_training_type.sql")
		.addScript("classpath:db/migrations/V3__stg_career_stats_training_goals.sql")
		.addScript("classpath:db/migrations/V4__stg_career_stats_training_record.sql")
		.build();
		
		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> practices = jdbcTemplate
				.queryForList("SELECT id FROM practice");
		logger.info("System has " + practices.size() + " practices");

		return dataSource;
	}
}
