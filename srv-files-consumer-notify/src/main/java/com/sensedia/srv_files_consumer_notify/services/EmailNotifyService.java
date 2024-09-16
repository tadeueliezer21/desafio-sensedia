package com.sensedia.srv_files_consumer_notify.services;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.sensedia.srv_files_consumer_notify.configurations.AWSConfigurationProperties;
import com.sensedia.srv_files_consumer_notify.interfaces.INotify;
import com.sensedia.srv_files_consumer_notify.payload.FileInfoPayload;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.services.ses.SesAsyncClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@RequiredArgsConstructor
@Service
@Slf4j
@EnableConfigurationProperties(AWSConfigurationProperties.class)
public class EmailNotifyService implements INotify {

	private final SesAsyncClient sesAsyncClient;

	private final DynamoDbTemplate dbTemplate;
	
	private final AWSConfigurationProperties properties;

	@Override
	public void execute(String key) {

		log.debug("trying notify user by email service [{}]", key);

		var result = dbTemplate.load(Key.builder().partitionValue(key).build(),
				FileInfoPayload.class);

		if (result == null) {
			log.info("no data founded for [{}]", key);
			return;
		}

		var request = SendEmailRequest.builder()
                .destination(Destination.builder()
                        .toAddresses(result.getUserEmail()) 
                        .build())
                .message(Message.builder()
                        .subject(Content.builder()
                                .data("Resultado processamento") 
                                .build())
                        .body(Body.builder()
                                .text(Content.builder()
                                        .data(result.toString()) 
                                        .build())
                                .build())
                        .build())
                .source(properties.getSesFrom())
                .build();

		sesAsyncClient.sendEmail(request);

		log.info("user has been notify by email service [{}]", key);
	}

}
