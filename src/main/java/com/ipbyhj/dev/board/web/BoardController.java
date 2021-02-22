package com.ipbyhj.dev.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.board.dto.BoardDTO;
import com.ipbyhj.dev.board.service.BoardService;
import com.ipbyhj.dev.common.Globals;

@RestController
public class BoardController {

	@Autowired
	BoardService boardService;

	/**
	 * 게시판 리스트를 조회할 때 사용
	 */
	@RequestMapping(value = {"/board/{name}","/board/{name}/**"}, method = RequestMethod.GET)
	public ModelAndView getBaordPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response, @PathVariable String name) {
		String code = Globals.BOARD_ALL;	//all : 4, community : 5, coding : 6  --> code값 매핑서 참조

		//board name로 가져오기
		if(name.equals("all")) {
			code = Globals.BOARD_ALL;

		}else if(name.equals("community")) {
			code = Globals.BOARD_COMMUNITY;

		}else if(name.equals("coding")) {
			code = Globals.BOARD_CODING;

		}
		
		List<BoardDTO> boardList = boardService.selectBoardList(code);

		modelAndView.addObject("boardList", boardList);
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_BOARD);

		modelAndView.setViewName("dev/board/list");

		return modelAndView;
	}

	//url로 요청할 때는 그 정보를, 비동기 통신으로 가져올 때는 다른 정보를 준다.
}
