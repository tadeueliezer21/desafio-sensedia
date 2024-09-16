package com.sensedia.srv_files_consumer_notify.consumer;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensedia.srv_files_consumer_notify.services.NotifyService;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotifyConsumer {

	private final NotifyService notifyService;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@SqsListener("${aws.configuration.send-notification-queue-name}")
	public void listen(String message)
			throws JsonMappingException, JsonProcessingException {
		
		var map = objectMapper.readValue(message, new TypeReference<Map<String, String>>() {});

		log.debug("trying notify user [{}]", map.get("Message"));
		
		notifyService.execute(map.get("Message"));
		log.info("user [{}] was notified", map.get("Message"));

	}

}
