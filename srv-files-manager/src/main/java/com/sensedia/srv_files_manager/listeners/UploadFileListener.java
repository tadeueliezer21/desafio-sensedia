package com.sensedia.srv_files_manager.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sensedia.srv_files_manager.events.UploadFileEvent;
import com.sensedia.srv_files_manager.services.UploadFileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class UploadFileListener {

	private final UploadFileService uploadFileService;

	@Async
	@EventListener
	public void listen(UploadFileEvent event) {
		log.debug("trying execute service s3 upload");
		uploadFileService.execute(event.payload());
		log.info("upload was executed with success");
	}

}
