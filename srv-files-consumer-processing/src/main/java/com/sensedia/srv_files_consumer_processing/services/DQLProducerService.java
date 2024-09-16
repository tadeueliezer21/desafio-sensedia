package com.sensedia.srv_files_consumer_processing.services;

import org.springframework.stereotype.Service;

import com.sensedia.srv_files_consumer_processing.configurations.AWSConfigurationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class DQLProducerService {

	private final AWSConfigurationProperties properties;

	private final SqsAsyncClient sqsClient;

	public void sendErrorToDQLQueue(Exception exception, String payload,
			String queue) {

		log.debug("trying send error to dlq");

		var request = SendMessageRequest.builder()
				.messageBody(
						"[ " + payload + " :: " + exception.getMessage() + "]")
				.queueUrl(properties.getFileProcessingQueueDql()).build();

		sqsClient.sendMessage(request);

		log.info("error has send to dlq");

	}

}
