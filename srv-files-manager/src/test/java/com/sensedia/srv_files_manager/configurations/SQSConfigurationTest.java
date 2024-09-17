package com.sensedia.srv_files_manager.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@ExtendWith(MockitoExtension.class)
class SQSConfigurationTest {

	@Mock
	private AWSConfigurationProperties awsConfigurationProperties;

	private SQSConfiguration sqsConfiguration;

	@BeforeEach
	void setUp() {

		sqsConfiguration = new SQSConfiguration(awsConfigurationProperties);

		when(awsConfigurationProperties.getSqsResource())
				.thenReturn("http://localhost:8000");
		when(awsConfigurationProperties.getRegion()).thenReturn("us-west-2");
	}

	@Test
	void testDynamoDbClientCreation() {
		SqsAsyncClient sqsAsync = sqsConfiguration.sqsAsyncClient();
		assertNotNull(sqsAsync);
	}
}