package com.sensedia.srv_files_manager.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ExtendWith(MockitoExtension.class)
class DynamoDBConfigurationTest {

    @Mock
    private AWSConfigurationProperties awsConfigurationProperties;

    private DynamoDBConfiguration dynamoDBConfiguration;

    @BeforeEach
    void setUp() {

    	dynamoDBConfiguration = new DynamoDBConfiguration(awsConfigurationProperties);

        when(awsConfigurationProperties.getDynamoDb()).thenReturn("http://localhost:8000");
        when(awsConfigurationProperties.getRegion()).thenReturn("us-west-2");
    }

    @Test
    void testDynamoDbClientCreation() {
        DynamoDbClient dynamoDbClient = dynamoDBConfiguration.dynamoDbClient();
        assertNotNull(dynamoDbClient);
    }
}