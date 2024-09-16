package com.sensedia.srv_files_consumer_processing.payload;

import java.util.UUID;

import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Setter
public class FileInfoPayload {

	private UUID payloadKey;
	private String userEmail;
	private String content;
	private Long lines;
	private String fileStatus;

	public FileInfoPayload() {
		this.payloadKey = UUID.randomUUID();
	}

	@DynamoDbPartitionKey
	public UUID getPayloadKey() {
		return payloadKey;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getContent() {
		return content;
	}

	public Long getLines() {
		return lines;
	}

	public String getFileStatus() {
		return fileStatus;
	}

}
