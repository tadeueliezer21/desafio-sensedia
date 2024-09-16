package com.sensedia.srv_files_manager.configurations;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class S3Configuration {
	
	@Bean
    public S3Client s3Client() {
		
        return S3Client.builder()
                .overrideConfiguration(ClientOverrideConfiguration.builder().build())
                .endpointOverride(URI.create("http://localhost:4566"))
                .forcePathStyle(true)
                .region(Region.US_EAST_1)
                .build();
    }

}
