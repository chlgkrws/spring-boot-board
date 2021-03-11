package com.ipbyhj.dev.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	 * @return 0 : 삽입 실패,  1 : 삽입 성공
	 * choi.hak.jun
	 * Start 2021.03.08
	 */
	@RequestMapping(value = "/reply/{boardId}", method = RequestMethod.POST )
	public int insertReply(HttpServletRequest request, @RequestParam Map<String, Object> param){
//		Map<String, Object> params = new HashMap<String, Object>();
//		System.out.println(request.getParameter("boardId"));
		System.out.println(param.toString());
		System.out.println(param.keySet());
		System.out.println(param.get("boardId"));
//		params.put("boardId"  ,		request.getParameter("boardId"));
//		params.put("writerId",		request.getParameter("userId"));
//		params.put("cotent", 		request.getParameter("content"));

		int ret = replyService.insertReply(param);
		return ret;
	}
}
