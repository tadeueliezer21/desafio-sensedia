package com.sensedia.srv_files_manager.events;

import org.springframework.web.multipart.MultipartFile;

public record UploadFileEvent (MultipartFile payload) {}
