package com.sensedia.srv_files_consumer_processing.services;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.sensedia.srv_files_consumer_processing.configurations.AWSConfigurationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(AWSConfigurationProperties.class)
public class NotificationProducerService {

	private final AWSConfigurationProperties properties;

	private final SnsAsyncClient snsAsyncClient;

	public void execute(String email) {
		log.debug("trying send notification to [{}]", email);
		var request = PublishRequest.builder().message(email)
				.topicArn(properties.getSnsResourceArn()).build();

		snsAsyncClient.publish(request);

		log.info("notification has been send to [{}]", email);

	}

}
