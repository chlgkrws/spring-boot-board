package com.ipbyhj.dev.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.common.Globals;

@RestController
public class MainController {

	@GetMapping("/")
	public ModelAndView getHome(ModelAndView modelAndView, HttpServletRequest request, HttpSession session) {

		modelAndView.addObject("main", true);
		modelAndView.addObject("boldType", Globals.BOLD_TYPE_MAIN);
		modelAndView.setViewName("dev/main/index");
		return modelAndView;
	}
}
