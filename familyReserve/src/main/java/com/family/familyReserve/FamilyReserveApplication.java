package com.family.familyReserve;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class FamilyReserveApplication {

	public static void main(String[] args) {
		System.out.println("Test setting breakpoint");
		SpringApplication.run(FamilyReserveApplication.class, args);
	}
	
	@Configuration
	public class DatabaseConfig {
	    @Bean
	    @Primary
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }
	}
}
