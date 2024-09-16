package com.sensedia.srv_files_manager.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "aws.configuration")
public class AWSConfigurationProperties {

	@NotBlank(message = "Missing file processing queue")
	private String fileProcessingQueue;

	@NotBlank(message = "Missing bucket name")
	private String bucketName;

	@NotBlank(message = "Missing dynamodb")
	private String dynamoDb;

	@NotBlank(message = "Missing region")
	private String region;
	
	@NotBlank(message = "Missing s3Resource")
	private String s3Resource;
	
	@NotBlank(message = "Missing sqsResource")
	private String sqsResource;

}