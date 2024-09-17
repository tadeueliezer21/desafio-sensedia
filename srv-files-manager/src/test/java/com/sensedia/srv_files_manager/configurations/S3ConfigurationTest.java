package com.sensedia.srv_files_manager.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import software.amazon.awssdk.services.s3.S3Client;

@ExtendWith(MockitoExtension.class)
class S3ConfigurationTest {

	@Mock
	private AWSConfigurationProperties awsConfigurationProperties;

	private S3Configuration s3Configuration;

	@BeforeEach
	void setUp() {

		s3Configuration = new S3Configuration(awsConfigurationProperties);

		when(awsConfigurationProperties.getS3Resource())
				.thenReturn("http://localhost:8000");
		when(awsConfigurationProperties.getRegion()).thenReturn("us-west-2");
	}

	@Test
	void testDynamoDbClientCreation() {
		S3Client s3Client = s3Configuration.s3Client();
		assertNotNull(s3Client);
	}
}
