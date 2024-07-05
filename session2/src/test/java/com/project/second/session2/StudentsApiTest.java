package com.project.second.session2;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentsApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testStudentsGetSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/111"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.stuId", Matchers.is(111)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.stuName", Matchers.is("esha")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.marks", Matchers.is(100)));
	}
	@Test
	public void testStudentsGetNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	@Test
	public void testStudentsGetListSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
	}

}
