package com.ipbyhj.dev.board.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.board.dto.BoardDTO;
import com.ipbyhj.dev.board.dto.ReplyDTO;
import com.ipbyhj.dev.board.entity.BoardEntity;
import com.ipbyhj.dev.board.entity.BoardLikeEntity;
import com.ipbyhj.dev.board.entity.ReplyEntity;
import com.ipbyhj.dev.board.service.BoardService;
import com.ipbyhj.dev.board.service.JPABoardService;
import com.ipbyhj.dev.board.service.JPAReplyService;
import com.ipbyhj.dev.board.service.ReplyService;
import com.ipbyhj.dev.common.Globals;
import com.ipbyhj.dev.common.Page;
import com.ipbyhj.dev.util.GlobalsUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final ReplyService replyService;

	private final JPABoardService jpaBoardService;

	private final JPAReplyService jpaReplyService;


	/**
	 * 게시물 리스트 조회
	 * choi.hak.jun
	 * Start 2021.02.10
	 */
	@GetMapping("/board/{name}/{num}")
	public ModelAndView getBaordPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String name, @PathVariable Integer num ) {
		Integer code = GlobalsUtils.getCodeValue(name);							//all : 4, community : 5, coding : 6  --> code값 매핑서 참조
		String selectedCategory = GlobalsUtils.getCodeName(name);				//카테고리 선택 시 버튼 진하게 만들기.

		Page page = new Page();													//페이지 네이션
		int boardCount = jpaBoardService.countBoard(code);
		page.setNum(num);
		page.setCount(boardCount);

		List<BoardEntity> boardList = jpaBoardService.pagingBoard(num, Globals.PAGING_SIZE, code)
				.stream()
				.collect(Collectors.toList());

		modelAndView.addObject("boardList", boardList);								//게시물 리스트
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_BOARD);				//네비바에서 board 진하게 만들기
		modelAndView.addObject("selectedCategory", selectedCategory);				//선택된 카테고리
		modelAndView.addObject("select", num);										//선택된 페이지
		modelAndView.addObject("page", page);										//페이지네이션 정보 값
		modelAndView.setViewName("dev/board/list");

		return modelAndView;
	}

	/**
	 * 게시물 자세히 보기
	 * choi.hak.jun
	 * Start 2021.02.24
	 */
	@GetMapping("/board/{boardId}")
	public ModelAndView getBoardView(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable Integer boardId) {
		String category = "";

		//쿠키에 따른 view카운트 증가
		jpaBoardService.updateViewCountByCookie(request, response, boardId);

		//게시물조회
		BoardEntity board = jpaBoardService.findByBoardId(boardId).orElse(null);
		Integer likeCount = board.getBoardLikeSet().size();

		//조회 계정에 대한 게시물 좋아요 여부 (1 - 이미 좋아요 누름, 0 - 아직 누르지 않음)
		String boardLikeFlag = "0";
		if(jpaBoardService.existsBoardLikeByUserId(boardId, (String)session.getAttribute("userId"))) {
			boardLikeFlag = "1";
		}

		//카테고리 설정
		category = jpaBoardService.getCategoryByBoard(board);

		//첫 페이지 댓글 조회
		List<ReplyEntity> reply = jpaReplyService.findReplyListByBoardId(boardId);

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
	 * 게시물 작성 페이지
	 * method : get
	 * Start 2021.03.28
	 */
	@GetMapping(value = {"/mode/{mode}", "/mode/{mode}/{boardId}"})
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
			BoardEntity board = jpaBoardService.findByBoardId(boardId).orElse(null);
			modelAndView.addObject("board", board);
		}else {
			throw new Exception();
		}
		modelAndView.addObject("mode", mode);
		modelAndView.setViewName("dev/board/write");
		return modelAndView;
	}

	/**
	 * 게시물  작성
	 * method : post
	 * choi.hak.jun
	 * return 1 : 작성 ,  0 : 실패
	 * Start 2021.03.28
	 */
	@PostMapping("/board/create")
	public int createBoard(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param,
			@ModelAttribute BoardEntity boardEntity) {

		int result = jpaBoardService.saveBoard(boardEntity);
		//int result = boardService.insertBoard(param);
		return result;
	}

	/**
	 * 게시물 삭제
	 * choi.hak.jun
	 * return 1 : 삭제 , 0 : 실패
	 * Start 2021.03.25
	 */
	@DeleteMapping("/board/{boardId}")
	public int deleteBoard(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer boardId) {

		int result = jpaBoardService.deleteBoard(boardId);
		//삭제 후 홈으로
		return result;
	}

	/**
	 * 게시물 수정
	 * choi.hak.jun
	 * return 1 : 성공 , 0 : 실패
	 * Start 2021.03.25
	 */
	@PutMapping("/board/{boardId}")
	public int updateBoard(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer boardId, @RequestParam Map<String, Object> param, @ModelAttribute BoardEntity boardEntity) {
		String updateBy = (String) request.getSession().getAttribute("userId");

		boardEntity.setUpdateBy(updateBy);
		boardEntity.setUpdateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		int result = jpaBoardService.updateBoard(boardEntity);

		return result;
	}


	/**
	 * 게시판 좋아요 버튼 클릭
	 * choi.hak.jun
	 * input 1 : 좋아요, 0 : 좋아요 취소
	 * return 1 : 성공, 0 : 취소
	 * Start 2021.04.27
	 */
	@PostMapping(value = {"/like/{boardId}/{userId}/{like}"})
	public int like(HttpServletRequest request, @PathVariable Integer boardId, @PathVariable String userId, @PathVariable String like) {

		return jpaBoardService.setLike(boardId, userId, like);
	}

}
