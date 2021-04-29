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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BoardTest {

	@Autowired
    MockMvc mockMvc;

	/**
	 * 게시물 조회(성공 케이스)
	 * boardId : 101,
	 */
	 @Test
	 public void selectBoard() throws Exception {

	    mockMvc.perform(get("/board/101"))
	            .andExpect(status().isOk())
	            .andDo(print());
	}


	/**
	 * 게시물 좋아요
	 * choi.hak.jun
	 * return : 1 - 성공, 0 - 실패
	 * 2021.04.29
	 */
	 @Test
	 public void likeBoard() throws Exception {

	    mockMvc.perform(post("/like/102/cgw981@gsitm.com/1"))
	            .andExpect(status().isOk())
	            .andDo(print());
	}


	 /**
	 * 게시물 좋아요 취소
	 * choi.hak.jun
	 * return : 1 - 성공, 0 - 실패
	 * 2021.04.29
	 */
	 @Test
	 public void likeCancelBoard() throws Exception {

	    mockMvc.perform(post("/like/102/cgw981@gsitm.com/0"))
	            .andExpect(status().isOk())
	            .andDo(print());
	}

}
