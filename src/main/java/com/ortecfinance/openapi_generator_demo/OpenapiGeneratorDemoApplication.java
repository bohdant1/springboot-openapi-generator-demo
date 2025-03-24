package com.ortecfinance.openapi_generator_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenapiGeneratorDemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(OpenapiGeneratorDemoApplication.class);

	public static void main(String[] args) {
		logger.info("Starting OpenAPI Generator Demo Application");
		SpringApplication.run(OpenapiGeneratorDemoApplication.class, args);
		logger.info("OpenAPI Generator Demo Application started successfully");
	}

}
