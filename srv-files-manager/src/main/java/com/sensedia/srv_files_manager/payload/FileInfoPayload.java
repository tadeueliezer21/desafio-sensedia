package com.sensedia.srv_files_manager.payload;

import java.util.UUID;

import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Setter
public class FileInfoPayload {

	private UUID payloadKey;
	private String fileName;
	private String userEmail;
	private Long size;
	private String content;
	private Long lines;

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

	public Long getSize() {
		return size;
	}

	public String getContent() {
		return content;
	}

	public Long getLines() {
		return lines;
	}

	public String getFileName() {
		return fileName;
	}

}
