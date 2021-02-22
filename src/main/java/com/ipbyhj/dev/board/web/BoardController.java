package com.ipbyhj.dev.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.board.dto.BoardDTO;
import com.ipbyhj.dev.board.service.BoardService;
import com.ipbyhj.dev.common.Globals;
import com.ipbyhj.dev.common.Page;

@RestController
public class BoardController {

	@Autowired
	BoardService boardService;

	/**
	 * 게시판 리스트를 조회할 때 사용
	 * choi.hak.jun
	 * Start 2021-02-10
	 */
	@RequestMapping(value = {"/board/{name}/{num}","/board/{name}/**"}, method = RequestMethod.GET)
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
		page.setNum(num);
		page.setCount(boardService.selectBoardCount());

		List<BoardDTO> boardList = boardService.selectBoardList(code, page.getDisplayPost(), page.getPostNum());

		modelAndView.addObject("boardList", boardList);								//게시물 리스트
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_BOARD);				//네비바에서 board 진하게 만들기
		modelAndView.addObject("selectedCategory", selectedCategory);				//선택된 카테고리
		modelAndView.addObject("select", num);										//선택된 페이지
		modelAndView.addObject("page", page);										//페이지네이션 정보 값
		modelAndView.setViewName("dev/board/list");

		return modelAndView;
	}
	/**
	 * END 2021-03-04
	 */

	//url로 요청할 때는 그 정보를, 비동기 통신으로 가져올 때는 다른 정보를 준다.
}
