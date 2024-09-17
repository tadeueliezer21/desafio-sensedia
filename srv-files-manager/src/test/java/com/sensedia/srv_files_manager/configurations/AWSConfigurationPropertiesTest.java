package com.sensedia.srv_files_manager.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
class AWSConfigurationPropertiesTest {

    private AWSConfigurationProperties awsConfigurationProperties;
    private Validator validator;

    @BeforeEach
    void setUp() {
        awsConfigurationProperties = new AWSConfigurationProperties();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsAreValid_thenNoViolations() {
        awsConfigurationProperties.setFileProcessingQueue("fileProcessingQueue");
        awsConfigurationProperties.setBucketName("bucketName");
        awsConfigurationProperties.setDynamoDb("dynamoDb");
        awsConfigurationProperties.setRegion("region");
        awsConfigurationProperties.setS3Resource("s3Resource");
        awsConfigurationProperties.setSqsResource("sqsResource");

        var violations = validator.validate(awsConfigurationProperties);
        assertEquals(0, violations.size(), "There should be no validation errors");
    }

    @Test
    void whenFieldsAreBlank_thenViolationsShouldOccur() {
        awsConfigurationProperties.setFileProcessingQueue("");
        awsConfigurationProperties.setBucketName("");
        awsConfigurationProperties.setDynamoDb("");
        awsConfigurationProperties.setRegion("");
        awsConfigurationProperties.setS3Resource("");
        awsConfigurationProperties.setSqsResource("");

        var violations = validator.validate(awsConfigurationProperties);
        assertEquals(6, violations.size(), "There should be 6 validation errors");
    }

    @Test
    void whenSomeFieldsAreBlank_thenThrowException() {
        awsConfigurationProperties.setFileProcessingQueue("validQueue");
        awsConfigurationProperties.setBucketName("validBucket");
        awsConfigurationProperties.setDynamoDb("");
        awsConfigurationProperties.setRegion("");
        awsConfigurationProperties.setS3Resource("validS3");
        awsConfigurationProperties.setSqsResource("");

        var violations = validator.validate(awsConfigurationProperties);
        assertEquals(3, violations.size(), "There should be 3 validation errors");
    }
}