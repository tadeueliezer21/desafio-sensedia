package com.sensedia.srv_files_manager.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sensedia.srv_files_manager.configurations.AWSConfigurationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(AWSConfigurationProperties.class)
public class UploadFileService {

	private final AWSConfigurationProperties properties;

	private final S3Client s3Client;
	
	public void execute(MultipartFile file) {

		try {
			log.debug("trying upload file to s3");

			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.bucket(properties.getBucketName())
					.contentType(file.getContentType())
					.key(file.getOriginalFilename()).build();

			Path pathFile = Files.write(
					Paths.get(".", file.getOriginalFilename()),
					file.getBytes());

			s3Client.putObject(putObjectRequest, RequestBody.fromFile(pathFile));
			log.info("file was uploaded to s3 [{}]", putObjectRequest.key());

		} catch (S3Exception e) {
			log.error("Error uploading object: [{}]",
					e.awsErrorDetails().errorMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
