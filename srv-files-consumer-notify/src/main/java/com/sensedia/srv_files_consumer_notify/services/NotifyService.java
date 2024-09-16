package com.sensedia.srv_files_consumer_notify.services;

import org.springframework.stereotype.Service;

import com.sensedia.srv_files_consumer_notify.configurations.NotifyServiceListConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotifyService {

	private final NotifyServiceListConfig serviceListConfig;

	public void execute(String userEmail) {
		log.debug("trying notify users by service list");
		serviceListConfig.getServicesList().stream()
				.forEach(s -> s.execute(userEmail));
		log.info("users has been notify");
	}

}
