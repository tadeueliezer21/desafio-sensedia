package com.sensedia.srv_files_manager.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.configuration")
public class AWSConfigurationProperties {

	private String fileProcessingQueue;
	private String bucketName;

	public String getBucketName() {
		return bucketName;
	}

	public String getFileProcessingQueue() {
		return fileProcessingQueue;
	}
	
}