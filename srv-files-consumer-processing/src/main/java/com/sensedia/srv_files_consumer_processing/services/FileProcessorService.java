package com.sensedia.srv_files_consumer_processing.services;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.sensedia.srv_files_consumer_processing.events.SendNotificationEvent;
import com.sensedia.srv_files_consumer_processing.payload.FileInfoPayload;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileProcessorService {

	private final ApplicationEventPublisher publisher;
	private final DynamoDbTemplate dbTemplate;

	public void execute(String key) {

		log.debug("trying process file [{}]", key);

		var payload = dbTemplate.load(Key.builder().partitionValue(key).build(),
				FileInfoPayload.class);

		Long lines = payload.getContent().lines().count();

		payload.setLines(lines);
		payload.setFileStatus("PROCESSED");

		dbTemplate.save(payload);

		publisher.publishEvent(
				new SendNotificationEvent(payload.getUserEmail()));

	}

}
