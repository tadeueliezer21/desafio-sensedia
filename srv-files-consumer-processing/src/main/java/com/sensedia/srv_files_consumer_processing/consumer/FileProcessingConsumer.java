package com.sensedia.srv_files_consumer_processing.consumer;

import org.springframework.stereotype.Component;

import com.sensedia.srv_files_consumer_processing.annotations.SqsObservableAOP;
import com.sensedia.srv_files_consumer_processing.services.FileProcessorService;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class FileProcessingConsumer {

	private final FileProcessorService fileProcessorService;

	@SqsObservableAOP(dqlQueue = "${aws.configuration.file-processing-queue-dql-name}")
	@SqsListener("${aws.configuration.file-processing-queue-name}")
	public void listen(String key) {

		log.debug("trying process message [{}]", key);

		fileProcessorService.execute(key);

		log.info("message was processed with success [{}]", key);

	}

}
