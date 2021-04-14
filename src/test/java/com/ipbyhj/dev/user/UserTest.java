package com.ipbyhj.dev.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserTest {

	@Autowired
    MockMvc mockMvc;

	/**
	 * 게시물 조회(성공 케이스)
	 * boardId : 101,
	 */
	 @Test
	 public void signUpUser() throws Exception {
		 MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

		 info.add("userId", "insertUserTest");
		 info.add("userPass", "1234");
		 info.add("userName", "회학문");
		 info.add("sex", "?");
		 info.add("phone", "01028989222");
		 info.add("email", "cgw241@naver.com");
		 info.add("identity", "951009");

		 mockMvc.perform(post("/sign-up")
				 .params(info))
		 		 .andExpect(status().isOk())
	             .andDo(print());
	}
}
