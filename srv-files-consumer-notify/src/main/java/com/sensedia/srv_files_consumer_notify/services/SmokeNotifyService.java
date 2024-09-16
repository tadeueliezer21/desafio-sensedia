package com.sensedia.srv_files_consumer_notify.services;

import org.springframework.stereotype.Service;

import com.sensedia.srv_files_consumer_notify.interfaces.INotify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmokeNotifyService implements INotify {

	@Override
	public void execute(String userEmail) {
		log.debug("trying notify user by smoke service [{}]", userEmail);
		log.info("user has been notify by smoke service [{}]", userEmail);
	}

}
