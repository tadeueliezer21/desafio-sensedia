package com.sensedia.srv_files_manager.listeners;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.sensedia.srv_files_manager.events.UploadFileEvent;
import com.sensedia.srv_files_manager.services.UploadFileService;

class UploadFileListenerTest {

    @Mock
    private UploadFileService uploadFileService;

    @InjectMocks
    private UploadFileListener uploadFileListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListen() {

    	UploadFileEvent event = new UploadFileEvent(multiPartfile());
        doNothing().when(uploadFileService).execute(event.payload());

        uploadFileListener.listen(event);

        verify(uploadFileService).execute(event.payload());
    }

	private MultipartFile multiPartfile() {
		return new MultipartFile() {
			
			@Override
			public void transferTo(File dest)
					throws IOException, IllegalStateException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public long getSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getOriginalFilename() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public byte[] getBytes() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}