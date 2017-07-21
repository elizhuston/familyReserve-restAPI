package com.family.familyReserve;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
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
	
	@Bean
	public Docket swaggerSettings() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(metaData());
	}
	  private ApiInfo metaData() {
	        ApiInfo apiInfo = new ApiInfo(
	        		 "Family Reserve REST API",
	                  "REST API for Family Reserve ",
	                 "1.0",
	                 "Terms of service",
	                  "Elizabeth Huston and Tracey Rainey",
	                "Apache License Version 2.0",
	                 "https://www.apache.org/licenses/LICENSE-2.0"
	        );
	        return apiInfo;
	    }
	   
	   
	   public static final List<String> DEFAULT_INCLUDE_PATTERNS = Arrays.asList("/news/.*");
	   public static final String SWAGGER_GROUP = "mobile-api";
}
