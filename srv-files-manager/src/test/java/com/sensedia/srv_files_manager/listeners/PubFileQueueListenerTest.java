package com.sensedia.srv_files_manager.listeners;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sensedia.srv_files_manager.events.PubFileQueueEvent;
import com.sensedia.srv_files_manager.payload.FileInfoPayload;
import com.sensedia.srv_files_manager.services.ProducerService;

import lombok.SneakyThrows;

class PubFileQueueListenerTest {

    @Mock
    private ProducerService producerService;

    @InjectMocks
    private PubFileQueueListener pubFileQueueListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @SneakyThrows
    void testExecute() {
    	
        PubFileQueueEvent event = new PubFileQueueEvent(new FileInfoPayload());
        doNothing().when(producerService).execute(event.payload().getPayloadKey().toString());

        pubFileQueueListener.execute(event);

        verify(producerService).execute(event.payload().getPayloadKey().toString());
    }

}
