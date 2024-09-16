package com.sensedia.srv_files_consumer_notify.consumer;

import org.springframework.stereotype.Component;

import com.sensedia.srv_files_consumer_notify.services.NotifyService;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotifyConsumer {
	
	private final NotifyService notifyService;

	@SqsListener("${aws.configuration.send-notification-queue-name}")
	public void listen(String message) {

		log.debug("trying notify user [{}]", message);
		notifyService.execute(message);
		log.info("user [{}] was notified", message);

	}

}
