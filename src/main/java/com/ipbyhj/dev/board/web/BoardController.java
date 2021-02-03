package com.ipbyhj.dev.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.common.Globals;

@RestController
public class BoardController {

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView getBaordPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {


		modelAndView.setViewName("dev/board/list");
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_BOARD);
		return modelAndView;
	}
}
