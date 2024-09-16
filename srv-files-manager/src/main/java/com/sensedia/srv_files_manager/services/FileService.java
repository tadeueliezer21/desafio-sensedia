package com.sensedia.srv_files_manager.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sensedia.srv_files_manager.events.PubFileQueueEvent;
import com.sensedia.srv_files_manager.events.UploadFileEvent;
import com.sensedia.srv_files_manager.payload.FileInfoPayload;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

	private final ApplicationEventPublisher publisher;

	private final DynamoDbTemplate dbTemplate;

	public void process(final MultipartFile file, final String userEmail) {

		log.debug("trying execute file service :Params: File [{}] :: User [{}]",
				file, userEmail);

		publisher.publishEvent(new UploadFileEvent(file));
		
		var payload = new FileInfoPayload();

		payload.setUserEmail(userEmail);
		payload.setContent(byteAsString(file));
		payload.setSize(file.getSize());
		payload.setFileName(file.getOriginalFilename());
		payload.setFileStatus("PENDING");


		dbTemplate.save(payload);

		publisher.publishEvent(new PubFileQueueEvent(payload));

		log.info(
				"file service executed with success :Params: File [{}] :: User [{}]",
				file, userEmail);

	}

	private static String byteAsString(InputStreamSource inputStreamSource) {

		try {
			log.debug("trying read content file as string");
			return new String(inputStreamSource.getInputStream().readAllBytes(),
					StandardCharsets.UTF_8);

		} catch (IOException ex) {
			log.error("byteAsString fail [{}] ", ex);
			return "cant open this file";
		}
	}

}
