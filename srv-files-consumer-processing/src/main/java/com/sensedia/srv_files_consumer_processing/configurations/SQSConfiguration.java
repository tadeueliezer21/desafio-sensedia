package com.sensedia.srv_files_consumer_processing.configurations;

import java.net.URI;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
@EnableConfigurationProperties(AWSConfigurationProperties.class)
@RequiredArgsConstructor
public class SQSConfiguration {

	private final AWSConfigurationProperties properties;

	@Bean
	public SqsAsyncClient sqsAsyncClient() {
		return SqsAsyncClient.builder()
				.endpointOverride(URI.create(properties.getSqsResource()))
				.region(Region.of(properties.getSqsResource())).build();
	}

}
