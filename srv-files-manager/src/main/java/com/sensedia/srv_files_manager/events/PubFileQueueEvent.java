package com.sensedia.srv_files_manager.events;

import com.sensedia.srv_files_manager.payload.FileInfoPayload;

public record PubFileQueueEvent(FileInfoPayload payload) {

}
