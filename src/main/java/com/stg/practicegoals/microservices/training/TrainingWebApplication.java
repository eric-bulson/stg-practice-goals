package com.stg.practicegoals.microservices.training;

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
 * <li>Provide configuration and setup for {@link TrainingServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link TrainingServer} instead.
 * 
 */
@SpringBootApplication
public class TrainingWebApplication {

	protected Logger logger = Logger.getLogger(TrainingWebApplication.class
			.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TrainingWebApplication.class, args);
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
				.addScript("classpath:db/migrations/V2__stg_career_stats_training_type.sql")
				.build();
		
//.addScript("classpath:db/migrations/V1__stg_career_stats_practice.sql")
				//.build();
//		.addScript("classpath:db/migrations/V2__stg_career_stats_training_goals.sql")
//		.addScript("classpath:db/migrations/V2__stg_career_stats_training_record.sql")
		
		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> trainTypes = jdbcTemplate
				.queryForList("SELECT id FROM training_type");
		logger.info("System has " + trainTypes.size() + " training types");

		return dataSource;
	}
}
