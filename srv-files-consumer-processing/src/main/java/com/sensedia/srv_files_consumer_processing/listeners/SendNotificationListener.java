package com.sensedia.srv_files_consumer_processing.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sensedia.srv_files_consumer_processing.events.SendNotificationEvent;
import com.sensedia.srv_files_consumer_processing.services.NotificationProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendNotificationListener {

	private final NotificationProducerService notificationProducerService;

	@Async
	@EventListener
	public void execute(SendNotificationEvent event) {

		log.debug("trying send notification [{}]", event);
		notificationProducerService.execute(event.userEmail());
		log.info("notificaiton was sended with success [{}]", event);

	}

}
