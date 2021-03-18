package com.ipbyhj.dev.board.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipbyhj.dev.board.dto.ReplyDTO;
import com.ipbyhj.dev.board.service.ReplyService;

@RestController
public class ReplyController {

	@Autowired
	ReplyService replyService;

	/**
	 * 댓글 조회
	 * choi.hak.jun
	 * Start 2021.03.08
	 */
	@RequestMapping(value = "/reply/{boardId}", method = RequestMethod.GET)
	public Map<String, Object> selectReply(HttpServletRequest request, @PathVariable String boardId){

		Map<String, Object> ret = replyService.selectReply(boardId);
		return ret;
	}
	/**
	 * END 2021.03.08
	 */

	/**
	 * 댓글 삽입
	 * @return 삽입한 댓글 pk
	 * choi.hak.jun
	 * Start 2021.03.08
	 */
	@RequestMapping(value = "/reply/{boardId}", method = RequestMethod.POST )
	public String insertReply(HttpServletRequest request, @RequestParam Map<String, Object> param){


		ReplyDTO reply = new ReplyDTO();
		reply.setBoardId((String)param.get("boardId"));
		reply.setWriterId((String)param.get("writerId"));
		reply.setContent((String)param.get("content"));
		int ret = replyService.insertReply(reply);
		return reply.getReplyId();
	}
}
