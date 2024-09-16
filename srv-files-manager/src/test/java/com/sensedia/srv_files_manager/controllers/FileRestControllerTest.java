package com.sensedia.srv_files_manager.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sensedia.srv_files_manager.services.FileService;

@WebMvcTest(FileRestController.class)
class FileRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileService fileService;

	@InjectMocks
	private FileRestController fileRestController;

	@Test
	public void testPost() throws Exception {

		MockitoAnnotations.openMocks(this);

		MockMultipartFile file = new MockMultipartFile("file", "testfile.txt",
				MediaType.TEXT_PLAIN_VALUE, "This is a test file".getBytes());

		mockMvc.perform(multipart("/files").file(file)
				.param("userEmail", "testuser@example.com")
				.contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
				.andExpect(status().isNoContent());

		doNothing().when(fileService).process(any(), any());

		verify(fileService).process(file, "testuser@example.com");
	}

	@Test
	public void testPost_withIncorrectContentType() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/files")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userEmail\":\"testuser@example.com\"}"))
				.andExpect(status().isUnsupportedMediaType());
	}

}
