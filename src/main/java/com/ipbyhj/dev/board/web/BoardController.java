package com.ipbyhj.dev.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.board.dto.BoardDTO;
import com.ipbyhj.dev.board.dto.ReplyDTO;
import com.ipbyhj.dev.board.entity.BoardEntity;
import com.ipbyhj.dev.board.entity.BoardLikeEntity;
import com.ipbyhj.dev.board.service.BoardService;
import com.ipbyhj.dev.board.service.JPABoardService;
import com.ipbyhj.dev.board.service.ReplyService;
import com.ipbyhj.dev.common.Globals;
import com.ipbyhj.dev.common.Page;

@RestController
public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	ReplyService replyService;

	@Autowired
	JPABoardService jpaBoardService;

	/**
	 * 게시물 리스트 조회
	 * choi.hak.jun
	 * Start 2021.02.10
	 */
	@RequestMapping(value = {"/board/{name}/{num}"}, method = RequestMethod.GET)
	public ModelAndView getBaordPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String name, @PathVariable Integer num ) {
		String code = Globals.BOARD_ALL;											//all : 4, community : 5, coding : 6  --> code값 매핑서 참조
		String selectedCategory = "all";											//카테고리 선택 시 버튼 진하게 만들기.


		if(name.equals("all")) {  													//board name로 가져오기
			code = Globals.BOARD_ALL;
		}else if(name.equals("community")) {
			code = Globals.BOARD_COMMUNITY;
			selectedCategory = "community";

		}else if(name.equals("coding")) {
			code = Globals.BOARD_CODING;
			selectedCategory = "coding";

		}

		Page page = new Page();														//페이지 네이션
		int boardCount = boardService.selectBoardCount(code);
		page.setNum(num);
		page.setCount(boardCount);

		List<BoardDTO> boardList = boardService.selectBoardList(code, page.getDisplayPost(), page.getPostNum());		//게시물 조회

		modelAndView.addObject("boardList", boardList);								//게시물 리스트
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_BOARD);				//네비바에서 board 진하게 만들기
		modelAndView.addObject("selectedCategory", selectedCategory);				//선택된 카테고리
		modelAndView.addObject("select", num);										//선택된 페이지
		modelAndView.addObject("page", page);										//페이지네이션 정보 값
		modelAndView.setViewName("dev/board/list");

		return modelAndView;
	}
	/**
	 * END 2021.02.24
	 */

	/**
	 * 게시물 자세히 보기
	 * choi.hak.jun
	 * Start 2021.02.24
	 */
	@RequestMapping(value = {"/board/{boardId}"}, method = RequestMethod.GET)
	public ModelAndView getBoardView(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable Integer boardId) {
		String category = "";

		//if쿠키가 같지 않으면 조회수 증가시키기.
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i < cookies.length; i++) {
				//쿠키가 이미 존재하면, 해당 쿠키 저장
				if(cookies[i].getName().equals("cookie"+boardId)) {
					viewCookie = cookies[i];
				}
			}
		}
		//쿠키가 존재하지 않으면 조회수 업데이트 후 쿠키 추가.
		if(viewCookie == null) {
			boardService.updateViewCount(boardId);
			Cookie newCookie = new Cookie("cookie"+boardId, "|"+boardId+"|");
			response.addCookie(newCookie);
		}

		//게시물조회
		BoardDTO board = boardService.selectView(boardId);

		//JPA 반영시 수정
		BoardEntity boardEntity = jpaBoardService.findByBoardId(boardId);
		Integer likeCount = boardEntity.getBoardLikeSet().size();

		//조회 계정에 대한 게시물 좋아요 여부 (1 - 이미 좋아요 누름, 0 - 아직 누르지 않음)
		String boardLikeFlag = "0";
		if(jpaBoardService.existsBoardLikeByUserId(boardId, (String)session.getAttribute("userId"))) {
			boardLikeFlag = "1";
		}

		//카테고리 설정
		if(board.getCode().equals(Globals.BOARD_COMMUNITY)) category = "커뮤니티";
		else if(board.getCode().equals(Globals.BOARD_CODING)) category ="코딩";


		//첫 페이지 댓글 조회
		List<ReplyDTO> reply = replyService.selectReplyList(boardId.toString());

		modelAndView.addObject("category", category);			//게시물 카테고리
		modelAndView.addObject("board", board);					//게시물 정보
		modelAndView.addObject("likeCount", likeCount);			//좋아요 수
		modelAndView.addObject("boardLike", boardLikeFlag);		//게시물 좋아요 여부
		modelAndView.addObject("reply",reply);					//댓글 정보
		modelAndView.addObject("replySize",reply.size());		//댓글 갯수
		modelAndView.setViewName("dev/board/view");
		return modelAndView;
	}
	/**
	 * END 2021.03.04
	 */

	/**
	 * 게시물 작성 페이지
	 * method : get
	 * Start 2021.03.28
	 * @throws Exception
	 */
	@RequestMapping(value = {"/mode/{mode}", "/mode/{mode}/{boardId}"}, method = RequestMethod.GET)
	public ModelAndView getCreateBoardPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String mode, @PathVariable(required = false) Integer boardId) throws Exception {

		//글 수정/작성 분기
		if(mode.equals("write")) {
			Map<String, String> board = new HashMap<>();
			//가짜 데이터 넣어주기(타임리프 에러 방지)
			board.put("title", "");
			board.put("content", "");
			board.put("boardId", "");
			board.put("writerId", "");
			board.put("writerName", "");
			modelAndView.addObject("board", board);
		}else if(mode.equals("modify")){
			BoardDTO board = boardService.selectView(boardId);
			modelAndView.addObject("board", board);
		}else {
			throw new Exception();
		}
		modelAndView.addObject("mode", mode);
		modelAndView.setViewName("dev/board/write");
		return modelAndView;
	}
	/**
	 * END 2021.03.29
	 */

	/**
	 * 게시물  작성
	 * method : post
	 * choi.hak.jun
	 * return 1 : 작성 ,  0 : 실패
	 * Start 2021.03.28
	 */
	@RequestMapping(value = {"/board/create"}, method = RequestMethod.POST)
	public int createBoard(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param) {

		int result = boardService.insertBoard(param);
		return result;
	}
	/**
	 * END 2021.03.29
	 */

	/**
	 * 게시물 삭제
	 * choi.hak.jun
	 * return 1 : 삭제 , 0 : 실패
	 * Start 2021.03.25
	 */
	@RequestMapping(value = {"/board/{boardId}"}, method = RequestMethod.DELETE)
	public int deleteBoard(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer boardId) {

		int result = boardService.deleteBoard(boardId);
		//삭제 후 홈으로
		return result;
	}
	/**
	 * END 2021.03.25
	 */

	/**
	 * 게시물 수정
	 * choi.hak.jun
	 * return 1 : 성공 , 0 : 실패
	 * Start 2021.03.25
	 */
	@RequestMapping(value = {"/board/{boardId}"}, method = RequestMethod.PUT)
	public int updateBoard(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer boardId, @RequestParam Map<String, Object> param) {

		int result = boardService.modifyBoard(param);

		return result;
	}
	/**
	 * END 2021.03.25
	 */

	/**
	 * 게시판 좋아요 버튼 클릭(추후 Board에 대한 JPA 적용 시 한 번에 적용)
	 * choi.hak.jun
	 * input 1 : 좋아요, 0 : 좋아요 취소
	 * return 1 : 성공, 0 : 취소
	 * Start 2021.04.27
	 */
	@RequestMapping(value = {"/like/{boardId}/{userId}/{like}"}, method = RequestMethod.POST)
	public int like(HttpServletRequest request, @PathVariable Integer boardId, @PathVariable String userId, @PathVariable String like) {
		BiFunction<String, String, Boolean> equals = (String::equals);

		//게시물 좋아요(에러있음 반복삽입 에러 있음)
		if(equals.apply(like, "1")) {
			try {
				BoardLikeEntity boardLikeEntity = BoardLikeEntity.builder()
															.boardId(boardId)
															.userId(userId)
															.build();
				jpaBoardService.saveBoardLike(boardLikeEntity);
				return 1;
			}catch (Exception e) {
				return 0;
			}
		}

		//게시물 좋아요 취소
		if(equals.apply(like, "0")) {
			try {
				jpaBoardService.deleteByBoardIdAndUserId(boardId, userId);
				return 1;
			}catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}
}
