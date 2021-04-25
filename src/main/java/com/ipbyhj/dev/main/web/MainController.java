package com.ipbyhj.dev.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.common.Globals;

@RestController
public class MainController {

	/**
	 * 메인 페이지
	 * @return
	 */
	@RequestMapping(value= "/" , method = RequestMethod.GET)
	public ModelAndView getHome(ModelAndView modelAndView, HttpServletRequest request, HttpSession session) {

		//개발 테스트 임시 세션 추가
		//session.setAttribute("userId", "chlgkrws");

		modelAndView.addObject("main", true);
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_MAIN);
		modelAndView.setViewName("dev/main/index");
		return modelAndView;
	}

	/**
	 * 자기 소개 페이지
	 * @return
	 */
	@RequestMapping(value = "/intoduce", method = RequestMethod.GET)
	public String getIntroduce(HttpServletRequest request) {

		return "dev/main/introduce";
	}

}
