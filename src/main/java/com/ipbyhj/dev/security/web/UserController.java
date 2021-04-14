package com.ipbyhj.dev.security.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.security.entity.UserEntity;
import com.ipbyhj.dev.security.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 로그인 페이지
	 * choi.hak.jun
	 * Start 2021.04.13
	 */
	@RequestMapping(value = {"/sign-in"}, method = RequestMethod.GET)
	public ModelAndView getSignIn(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("dev/sign/sign-in");
		return modelAndView;
	}

	/**
	 * 회원가입 페이지
	 * choi.hak.jun
	 * Start 2021.04.13
	 */
	@RequestMapping(value= {"/sign-up"}, method = RequestMethod.GET)
	public ModelAndView getSignUp(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("dev/sign/sign-up");
		return modelAndView;
	}

	/**
	 * 회원가입
	 * Start 2021.04.14
	 */
	@RequestMapping(value= {"/sign-up"}, method = RequestMethod.POST)
	public ModelAndView setSignUp(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> params) {

		UserEntity userEntity = UserEntity.builder()
				.userId((String)params.get("userId"))
				.userPass((String)params.get("userPass"))
				.userName((String)params.get("userName"))
				.sex((String)params.get("sex"))
				.phone((String)params.get("phone"))
				.email((String)params.get("email"))
				.identity((String)params.get("identity"))
				.build();

		userService.save(userEntity);

		System.out.println(new ResponseEntity<>(HttpStatus.OK).toString());

		modelAndView.setViewName("dev/main/index");
		return modelAndView;
	}
}
