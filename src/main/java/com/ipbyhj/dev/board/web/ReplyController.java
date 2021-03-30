package com.ipbyhj.dev.board.web;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	/**
	 * END 2021.03.08
	 */

	/**
	 * 댓글 삭제
	 * @return 삭제한 댓글 pk : 성공,  0 : 실패
	 * choi.hak.jun
	 * Start 2021.03.30
	 */
	@RequestMapping(value = "/reply/{replyId}", method = RequestMethod.DELETE)
	public int deleteReply(HttpServletRequest request, @PathVariable Integer replyId) {

		int result = replyService.deleteReply(replyId);


		return result != 0 ? replyId : 0;
	}

	/**
	 * 댓글 좋아요 증가
	 * choi.hak.jun
	 * Start 2021.03.30
	 */
	@RequestMapping(value = "/reply/sub/like", method = RequestMethod.POST)
	public int updateLike(HttpServletRequest request, @RequestParam Map<String, Object> param,
			HttpServletResponse response) {
		Integer replyId = Integer.parseInt((String) param.get("replyId"));
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		int result = 0;
		//if쿠키가 같지 않으면 조회수 증가시키기.
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("cookie"+replyId)) {
					viewCookie = cookies[i];
				}
			}
		}
		//쿠키가 존재하지 않으면 좋아요 업데이트 후 쿠키 추가
		if(viewCookie == null) {
			 replyService.updateLike(replyId);
			Cookie newCookie = new Cookie("cookie"+replyId, "|"+replyId+"|");
			response.addCookie(newCookie);
			System.out.println("add cookie");
		}


		return result != 0 ? replyId : 0;
	}
}
