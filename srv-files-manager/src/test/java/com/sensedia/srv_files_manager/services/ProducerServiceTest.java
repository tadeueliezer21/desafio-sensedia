package com.sensedia.srv_files_manager.services;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sensedia.srv_files_manager.configurations.AWSConfigurationProperties;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

class ProducerServiceTest {

    @Mock
    private AWSConfigurationProperties properties;

    @Mock
    private SqsAsyncClient sqsClient;

    @InjectMocks
    private ProducerService producerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {

    	String fileKey = "test-file-key";
        String queueUrl = "http://sqs-test-queue-url";
        
        when(properties.getFileProcessingQueue()).thenReturn(queueUrl);

        producerService.execute(fileKey);

        verify(sqsClient).sendMessage(argThat((SendMessageRequest request) -> 
            request.messageBody().equals(fileKey) && 
            request.queueUrl().equals(queueUrl)
        ));
    }
}
