package com.ipbyhj.dev.board.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.common.Globals;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class BoardController {

	/**
	 * 처음 게시판 리스트를 조회할 때 사용
	 */
	@RequestMapping(value = {"/board/{name}","/board/{name}/**"}, method = RequestMethod.GET)
	public ModelAndView getBaordPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response, @PathVariable String name) {

		//board name로 가져오기
		if(name.equals("all")) {

		}else if(name.equals("community")) {

		}else if(name.equals("coding")) {

		}

		modelAndView.setViewName("dev/board/list");
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_BOARD);
		return modelAndView;
	}

	//url로 요청할 때는 그 정보를, 비동기 통신으로 가져올 때는 다른 정보를 준다.
}
