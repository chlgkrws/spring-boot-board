package com.ipbyhj.dev.security.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

	/**
	 * 로그인 페이지
	 * choi.hak.jun
	 * 2021.04.13
	 */
	@RequestMapping(value = {"/sign-in"}, method = RequestMethod.GET)
	public ModelAndView getLogin(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("dev/sign/sign-in");
		return modelAndView;
	}

	/**
	 * 회원가입 페이지
	 * choi.hak.jun
	 * 2021.04.13
	 */
	@RequestMapping(value= {"/sign-up"}, method = RequestMethod.GET)
	public ModelAndView getRegister(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("dev/sign/sign-up");
		return modelAndView;
	}
}
