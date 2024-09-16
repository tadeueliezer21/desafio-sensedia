package com.sensedia.srv_files_manager.configurations;

import java.net.URI;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@EnableConfigurationProperties(AWSConfigurationProperties.class)
@RequiredArgsConstructor
public class S3Configuration {

	private final AWSConfigurationProperties properties;

	@Bean
	public S3Client s3Client() {

		return S3Client.builder()
				.overrideConfiguration(
						ClientOverrideConfiguration.builder().build())
				.endpointOverride(URI.create(properties.getS3Resource()))
				.forcePathStyle(true).region(Region.of(properties.getRegion()))
				.build();
	}

}
