package com.sensedia.srv_files_consumer_processing.services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

import com.sensedia.srv_files_consumer_processing.annotations.SqsObservableAOP;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SqsObersableAOP {

	private final DQLProducerService dqlProducerService;

	@Around(value = "@annotation(sqsObservableAOP)")
	public void gerenciandoAspectosSqsConsumo(
			ProceedingJoinPoint proceedingJoinPoint,
			SqsObservableAOP observableAOP) {

		final var event = proceedingJoinPoint.getArgs()[0];

		try {
			log.info("message received by observable aop {} {}", event,
					observableAOP.dqlQueue());

			proceedingJoinPoint.proceed();

		} catch (Throwable throwable) {

			log.error("failed file processor service [{}]",
					throwable.getMessage());

			dqlProducerService.sendErrorToDQLQueue((Exception) throwable,
					(String) event, observableAOP.dqlQueue());

			throw new RuntimeException(throwable);
		}
	}
}
