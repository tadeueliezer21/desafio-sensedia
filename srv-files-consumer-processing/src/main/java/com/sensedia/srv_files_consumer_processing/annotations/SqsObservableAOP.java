package com.sensedia.srv_files_consumer_processing.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.sensedia.srv_files_consumer_processing.services.SqsObersableAOP;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Import(SqsObersableAOP.class)
public @interface SqsObservableAOP {

	String dqlQueue();
	
}
