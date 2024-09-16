package com.sensedia.srv_files_consumer_processing.configurations;

import java.net.URI;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;

@Configuration
@EnableConfigurationProperties(AWSConfigurationProperties.class)
@RequiredArgsConstructor
public class SNSConfiguration {

	private final AWSConfigurationProperties properties;

	@Bean
	public SnsAsyncClient snsAsyncClient() {

		return SnsAsyncClient.builder()
				.region(Region.of(properties.getRegion()))
				.endpointOverride(URI.create(properties.getSnsResource()))
				.build();
	}

}
