package com.sensedia.srv_files_consumer_notify.configurations;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sensedia.srv_files_consumer_notify.interfaces.INotify;
import com.sensedia.srv_files_consumer_notify.services.EmailNotifyService;
import com.sensedia.srv_files_consumer_notify.services.SmokeNotifyService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotifyServiceListConfig {

	private final EmailNotifyService emailNotifyService;
	private final SmokeNotifyService smokeNotifyService;

	public List<INotify> getServicesList() {
		return Arrays.asList(emailNotifyService, smokeNotifyService);
	}

}
