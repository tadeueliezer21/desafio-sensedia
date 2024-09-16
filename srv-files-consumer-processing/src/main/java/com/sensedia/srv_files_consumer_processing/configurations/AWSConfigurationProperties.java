package com.sensedia.srv_files_consumer_processing.configurations;

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

	@NotBlank(message = "Missing file processing queue DQL")
	private String fileProcessingQueueDql;

	@NotBlank(message = "Missing file processing queue DQL name")
	private String fileProcessingQueueDqlName;

	@NotBlank(message = "Missing file processing queue name")
	private String fileProcessingQueueName;

	@NotBlank(message = "Missing dynamodb")
	private String dynamoDb;

	@NotBlank(message = "Missing region")
	private String region;

	@NotBlank(message = "Missing sqsResource")
	private String sqsResource;

	@NotBlank(message = "Missing snsResourceArn")
	private String snsResourceArn;

	@NotBlank(message = "Missing snsResource")
	private String snsResource;

}