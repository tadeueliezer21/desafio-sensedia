package com.sensedia.srv_files_manager.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileInfoPayloadTest {

    private FileInfoPayload fileInfoPayload;

    @BeforeEach
    void setUp() {
        fileInfoPayload = new FileInfoPayload();
    }

    @Test
    void testPayloadKeyInitialization() {
        assertNotNull(fileInfoPayload.getPayloadKey(), "payloadKey should not be null");
    }

    @Test
    void testSetAndGetMethods() {

    	UUID newPayloadKey = UUID.randomUUID();
        String fileName = "testFile.txt";
        String userEmail = "user@example.com";
        Long size = 1024L;
        String content = "Test content";
        Long lines = 100L;
        String fileStatus = "UPLOADED";

        fileInfoPayload.setPayloadKey(newPayloadKey);
        fileInfoPayload.setFileName(fileName);
        fileInfoPayload.setUserEmail(userEmail);
        fileInfoPayload.setSize(size);
        fileInfoPayload.setContent(content);
        fileInfoPayload.setLines(lines);
        fileInfoPayload.setFileStatus(fileStatus);

        assertEquals(newPayloadKey, fileInfoPayload.getPayloadKey());
        assertEquals(fileName, fileInfoPayload.getFileName());
        assertEquals(userEmail, fileInfoPayload.getUserEmail());
        assertEquals(size, fileInfoPayload.getSize());
        assertEquals(content, fileInfoPayload.getContent());
        assertEquals(lines, fileInfoPayload.getLines());
        assertEquals(fileStatus, fileInfoPayload.getFileStatus());
    }
}