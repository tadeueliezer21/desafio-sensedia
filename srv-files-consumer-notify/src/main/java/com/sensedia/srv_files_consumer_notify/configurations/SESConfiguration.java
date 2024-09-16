package com.sensedia.srv_files_consumer_notify.configurations;

import java.net.URI;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesAsyncClient;

@Configuration
@EnableConfigurationProperties(AWSConfigurationProperties.class)
@RequiredArgsConstructor
public class SESConfiguration {

	private final AWSConfigurationProperties properties;

	@Bean
	public SesAsyncClient sesAsyncClient() {
		return SesAsyncClient.builder()
				.endpointOverride(URI.create(properties.getSesResource()))
				.region(Region.of(properties.getRegion())).build();
	}
}
