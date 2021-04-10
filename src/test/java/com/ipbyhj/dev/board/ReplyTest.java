package com.ipbyhj.dev.board;

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
public class ReplyTest {

	 @Autowired
	    MockMvc mockMvc;

	 /**
     * 댓글 삽입 테스트(테스트 케이스)
     * boardId : 1,
     * return : 1 - 성공, 0 - 실패
     * @throws Exception
     */
	@Test
	public void hello() throws Exception {
		MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

		info.add("boardId", "1");
		info.add("writerId", "chlgkrws");
		info.add("content", "테스트 코드 작성");

	    mockMvc.perform(post("/reply/1")
	    		.content("{\"boardId\":\"1\",\"writerId\":\"chlgkrws\",\"content\":\"테스트 코드 작성\"}"))
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"))
	            .andDo(print());
	}

	 /**
	 * 댓글 삽입 테스트(성공 케이스)
	 * boardId : 1,
	 * return : 1 - 성공, 0 - 실패
	 * @throws Exception
	 */
	 @Test
	 public void hello2() throws Exception {
		MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

		info.add("boardId", "1");
		info.add("writerId", "chlgkrws");
		info.add("content", "테스트 코드 작성2");

	    mockMvc.perform(post("/reply/1")
	    		.params(info))
	            .andExpect(status().isOk())
	            .andExpect(content().string("chlgkrws"))
	            .andDo(print());
	}
}
