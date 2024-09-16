package com.sensedia.srv_files_consumer_notify.configurations;

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

	@NotBlank(message = "Missing region")
	private String region;

	@NotBlank(message = "Missing sqsResource")
	private String sqsResource;
	
	@NotBlank(message = "Missing sesResource")
	private String sesResource;
	
	@NotBlank(message = "Missing dynamodb")
	private String dynamoDb;
	
	@NotBlank(message = "Missing sesFrom")
	private String sesFrom;

}