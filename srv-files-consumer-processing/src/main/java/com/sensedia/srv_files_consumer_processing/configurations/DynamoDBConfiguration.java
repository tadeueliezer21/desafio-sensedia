package com.sensedia.srv_files_consumer_processing.configurations;

import java.net.URI;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@EnableConfigurationProperties(AWSConfigurationProperties.class)
@RequiredArgsConstructor
public class DynamoDBConfiguration {

	private final AWSConfigurationProperties properties;

	@Bean
	public DynamoDbClient dynamoDbClient() {

		return DynamoDbClient.builder()
				.endpointOverride(URI.create(properties.getDynamoDb()))
				.region(Region.of(properties.getRegion())).build();

	}

}
