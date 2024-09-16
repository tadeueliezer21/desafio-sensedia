package com.sensedia.srv_files_manager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sensedia.srv_files_manager.services.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileRestController {

	private final FileService fileService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> post(@RequestParam MultipartFile file,
			@RequestPart String userEmail) {

		log.debug("trying start process for user [{}]", userEmail);

		fileService.process(file, userEmail);
		log.info("request was executed with success");

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
