package com.project.second.session2;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
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
	public void testStudentsGetListSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
	}



	@Test
	public void testStudentGetSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/123"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.stuId", Matchers.is(123)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.stuName", Matchers.is("esha")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.marks", Matchers.is(90)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(22)));
	}

	@Test
	public void testStudentGetNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/456"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Order(1)
	@Test
	public void testStudentsPostSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\"stuId\":\"125\",\"stuName\":\"Ayesha Ashfaq\",\"marks\":\"100\",\"age\":22}\"}")
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.with(SecurityMockMvcRequestPostProcessors.user("reporter")
								.authorities(new SimpleGrantedAuthority("reporter"))))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.stuId", Matchers.is(125)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.stuName", Matchers.is("Ayesha Ashfaq")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.marks", Matchers.is(100)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(22)));
	}

}