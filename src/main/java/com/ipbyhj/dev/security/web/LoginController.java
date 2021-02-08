package com.ipbyhj.dev.security.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLogin(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("dev/login/login");
		return modelAndView;
	}
}
