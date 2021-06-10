package com.ipbyhj.dev.board.web;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipbyhj.dev.board.dto.ReplyDTO;
import com.ipbyhj.dev.board.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;

	/**
	 * 댓글 조회
	 * choi.hak.jun
	 * Start 2021.03.08
	 */
	@GetMapping("/reply/{boardId}")
	public Map<String, Object> selectReply(HttpServletRequest request, @PathVariable String boardId){

		Map<String, Object> ret = replyService.selectReply(boardId);
		return ret;
	}

	/**
	 * 댓글 삽입
	 * @return 삽입한 댓글 pk
	 * choi.hak.jun
	 * Start 2021.03.08
	 */
	@PostMapping("/reply/{boardId}")
	public String insertReply(HttpServletRequest request, @RequestParam Map<String, Object> param){

		ReplyDTO reply = ReplyDTO.builder()
				.boardId((String)param.get("boardId"))
				.writerId((String)param.get("writerId"))
				.content((String)param.get("content"))
				.parentRplId((String)param.getOrDefault("parentRplId", null))
				.build();

		int ret = replyService.insertReply(reply);
		return ret != 0 ? reply.getReplyId() : "0" ;
	}

	/**
	 * 댓글 수정
	 * return 1 : 성공, 0 : 실패
	 * choi.hak.jun
	 * Start 2021.04.06
	 */
	@PutMapping("/reply/{reply}")
	public int updateReply(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		String replyId = (String) param.get("replyId");
		String writerId = (String) param.get("writerId");
		String content = (String) param.get("content");
		return replyService.updateReply(replyId, writerId, content);
	}

	/**
	 * 댓글 삭제
	 * @return 삭제한 댓글 pk : 성공,  0 : 실패
	 * choi.hak.jun
	 * Start 2021.03.30
	 */
	@DeleteMapping("/reply/{replyId}")
	public int deleteReply(HttpServletRequest request, @PathVariable Integer replyId) {

		int result = replyService.deleteReply(replyId);

		return result != 0 ? replyId : 0;
	}

	/**
	 * 댓글 좋아요 증가
	 * choi.hak.jun
	 * Start 2021.03.30
	 */
	@PostMapping("/reply/sub/like")
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
			result = replyService.updateLike(replyId);
			Cookie newCookie = new Cookie("cookie"+replyId, "|"+replyId+"|");
			response.addCookie(newCookie);
		}

		return result != 0 ? replyId : 0;
	}
}
