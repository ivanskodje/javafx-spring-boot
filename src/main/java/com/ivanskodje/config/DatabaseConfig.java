package com.ivanskodje.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig
{
	/**
	 * Load the properties set in the application.yml relevant to DataSource.
	 *
	 * @return DataSourceProperties from application.yml
	 */
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties dataSourceProperties()
	{
		return new DataSourceProperties();
	}

	/**
	 * We load the DataSourceProperties and build it.
	 * This will allow us to make adjustments if necessary.
	 *
	 * @return Built & Initialized DataSource
	 */
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource()
	{
		return dataSourceProperties().initializeDataSourceBuilder().build();
	}

	/**
	 * Example of setting up DataSource using code
	 */
//	@Bean
//	@ConfigurationProperties("spring.datasource")
//	public DataSource repositoryDataSource()
//	{
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.url("jdbc:h2:file:~/secondary_db");
//		dataSourceBuilder.username("username");
//		dataSourceBuilder.password("");
//		dataSource = dataSourceBuilder.build();
//		return dataSource;
//	}
}
