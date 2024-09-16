package com.sensedia.srv_files_manager.configurations;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfiguration {

	@Bean
	public DynamoDbClient dynamoDbClient() {

		return DynamoDbClient
				.builder()
				.endpointOverride(URI.create("http://localhost:4566"))
				.region(Region.US_EAST_1)
				.build();

	}

}
