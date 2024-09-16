package com.sensedia.srv_files_manager.services;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.sensedia.srv_files_manager.configurations.AWSConfigurationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableConfigurationProperties(AWSConfigurationProperties.class)
public class ProducerService {
	
	private final AWSConfigurationProperties properties;

	private final SqsAsyncClient sqsClient;

	public void execute(String fileKey) {

		log.debug("trying pub message on queue [{}]", fileKey);
		var request = SendMessageRequest.builder()
				.messageBody(fileKey)
				.queueUrl(properties.getFileProcessingQueue()).build();

		sqsClient.sendMessage(request);
		
		log.info("message was published with success [{}]", fileKey);

	}

}
