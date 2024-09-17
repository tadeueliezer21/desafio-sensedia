package com.sensedia.srv_files_manager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.sensedia.srv_files_manager.configurations.AWSConfigurationProperties;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

class UploadFileServiceTest {

	@Mock
	private AWSConfigurationProperties properties;

	@Mock
	private S3Client s3Client;

	@Mock
	private MultipartFile file;

	@InjectMocks
	private UploadFileService uploadFileService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testExecute() throws Exception {
		// Arrange
		String fileName = "testFile.txt";
		String contentType = "text/plain";
		byte[] content = "Test content".getBytes();

		when(file.getOriginalFilename()).thenReturn(fileName);
		when(file.getContentType()).thenReturn(contentType);
		when(file.getBytes()).thenReturn(content);

		when(properties.getBucketName()).thenReturn("test-bucket");

		uploadFileService.execute(file);

		verify(s3Client).putObject(
				argThat((PutObjectRequest request) -> "test-bucket".equals(
						request.bucket()) && fileName.equals(request.key())
						&& contentType.equals(request.contentType())),
				any(RequestBody.class));
	}

	@Test
	void testExecuteWithS3Exception() throws IOException {

		String fileName = "testFile.txt";
		String contentType = "text/plain";
		byte[] content = "Test content".getBytes();

		when(file.getOriginalFilename()).thenReturn(fileName);
		when(file.getContentType()).thenReturn(contentType);
		when(file.getBytes()).thenReturn(content);

		when(properties.getBucketName()).thenReturn("test-bucket");

		doThrow(S3Exception.builder().message("Fail").build()).when(s3Client)
				.putObject(any(PutObjectRequest.class), any(RequestBody.class));

		assertThrows(S3Exception.class, () -> uploadFileService.execute(file));

	}
	
	@Test
    void testExecuteWithIOException() throws IOException {

		String fileName = "testFile.txt";
        String contentType = "text/plain";

        when(file.getOriginalFilename()).thenReturn(fileName);
        when(file.getContentType()).thenReturn(contentType);
        when(file.getBytes()).thenThrow(new IOException("Failed to get file bytes"));

        when(properties.getBucketName()).thenReturn("test-bucket");

        try {
            uploadFileService.execute(file);
        } catch (RuntimeException ex) {
            assertEquals("Failed to get file bytes", ex.getMessage());
        }
    }
}