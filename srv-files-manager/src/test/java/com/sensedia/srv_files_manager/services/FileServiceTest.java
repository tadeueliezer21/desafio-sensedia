package com.sensedia.srv_files_manager.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.multipart.MultipartFile;

import com.sensedia.srv_files_manager.events.PubFileQueueEvent;
import com.sensedia.srv_files_manager.events.UploadFileEvent;
import com.sensedia.srv_files_manager.payload.FileInfoPayload;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;

class FileServiceTest {

	@Mock
	private ApplicationEventPublisher publisher;

	@Mock
	private DynamoDbTemplate dbTemplate;

	@Mock
	private MultipartFile file;

	@InjectMocks
	private FileService fileService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testProcess() throws IOException {

		String userEmail = "user@example.com";
		String fileName = "testFile.txt";
		String content = "Test content";
		long size = content.length();

		when(file.getOriginalFilename()).thenReturn(fileName);
		when(file.getSize()).thenReturn(size);
		when(file.getInputStream()).thenReturn(new ByteArrayInputStream(
				content.getBytes(StandardCharsets.UTF_8)));

		fileService.process(file, userEmail);

		verify(publisher).publishEvent(any(UploadFileEvent.class));
		verify(publisher).publishEvent(any(PubFileQueueEvent.class));

		verify(dbTemplate).save(any(FileInfoPayload.class));
	}

	@Test
	void testProcessWithIOException() throws IOException {

		String userEmail = "user@example.com";
		String fileName = "testFile.txt";

		when(file.getOriginalFilename()).thenReturn(fileName);
		when(file.getSize()).thenReturn(1024L);

		when(file.getInputStream())
				.thenThrow(new IOException("Failed to open file"));

		fileService.process(file, userEmail);

		verify(publisher).publishEvent(any(UploadFileEvent.class));
		verify(publisher).publishEvent(any(PubFileQueueEvent.class));

		verify(dbTemplate).save(argThat(payload -> "cant open this file"
				.equals(((FileInfoPayload) payload).getContent())));
	}
}
