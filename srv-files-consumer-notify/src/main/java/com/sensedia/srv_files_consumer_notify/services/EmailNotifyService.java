package com.sensedia.srv_files_consumer_notify.services;

import org.springframework.stereotype.Service;

import com.sensedia.srv_files_consumer_notify.interfaces.INotify;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailNotifyService implements INotify {

	@Override
	public void execute(String userEmail) {
		log.debug("trying notify user by email service [{}]", userEmail);
		log.info("user has been notify by email service [{}]", userEmail);
	}

}
