package com.ipbyhj.dev.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	@RequestMapping(value= "/" , method = RequestMethod.GET)
	public ModelAndView getHome(ModelAndView modelAndView, HttpServletRequest request, HttpSession session) {

		modelAndView.addObject("boldType", "home");
		modelAndView.setViewName("dev/main/index");
		return modelAndView;
	}
}
