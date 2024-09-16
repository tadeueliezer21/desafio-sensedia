package com.sensedia.srv_files_consumer_notify.payload;

import java.util.UUID;

import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@ToString
@DynamoDbBean
@Setter
public class FileInfoPayload {

	private UUID payloadKey;
	private String fileName;
	private String userEmail;
	private Long size;
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

	public String getFileStatus() {
		return fileStatus;
	}

}
