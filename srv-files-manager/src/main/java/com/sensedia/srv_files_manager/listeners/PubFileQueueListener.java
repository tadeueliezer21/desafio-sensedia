package com.sensedia.srv_files_manager.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sensedia.srv_files_manager.events.PubFileQueueEvent;
import com.sensedia.srv_files_manager.services.ProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class PubFileQueueListener {

	private final ProducerService fileProducerService;

	@Async
	@EventListener
	public void execute(PubFileQueueEvent event) {
		log.debug("trying execute file produce service");
		fileProducerService.execute(event.payload().getPayloadKey().toString());
		log.info("file produce service was executed with success");
	}

}
