package com.ipbyhj.dev.security.web;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

//	/**
//	 * 로그인
//	 * choi.hak.jun
//	 *  return 1 - 성공, 0 - 실패
//	 * Start 2021.04.18
//	 */
//	@RequestMapping(value = {"/sign-in"}, method = RequestMethod.POST)
//	public int setSignIn(HttpServletRequest request, HttpServletResponse response,
//			Map<String, Object> params) {
//		String email = (String) params.get("email");
//		String userPass = (String) params.get("userPass");
//		System.out.println(email + userPass+" zzz");
//		try {
//			Optional<UserEntity> user = userService.findById(email);
//
//			String password = user.get().getUserPass();
//
//			//비밀번호가 일치하면 1(성공) 반환
//			if(password.equals(userPass)) {
//				return 1;
//			}
//
//		}catch (Exception e) {
//			//유저 없음
//			return 0;
//		}
//		return 0;
//	}

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
	 * choi.hak.jun
	 * return 1 - 성공, 0 - 실패
	 * Start 2021.04.14
	 */
	@RequestMapping(value= {"/sign-up"}, method = RequestMethod.POST)
	public int setSignUp(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> params) {
		String email = (String) params.get("email");

		//이미 있는 계정인지 체크
		boolean isExistUser = userService.chekcEmailDuplicate(email);

		if(!isExistUser) {
			UserEntity userEntity = UserEntity.builder()
					.userId  (email)
					.userName((String)params.get("userName"))
					.userPass((String)params.get("userPass"))
					.identity((String)params.get("identity"))
					.phone	 ((String)params.get("phone"))
					.sex	 ((String)params.get("sex"))
					.email	 (email)
					.build();
			userService.save(userEntity);
			return 1;
		}

		return 0;
	}

	/**
	 * 회원가입(email) 페이지
	 * Start 2021-04-17
	 */
	@RequestMapping(value= {"/sign-up-email"}, method = RequestMethod.GET)
	public ModelAndView getSignUpEmail(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("dev/sign/sign-up-by-email");
		return modelAndView;
	}

	/**
	 * 회원가입(email)
	 * return 1 - 성공, 0 - 실패
	 * Start 2021.04.17
	 */
	@RequestMapping(value= {"/sign-up-email"}, method = RequestMethod.POST)
	public int setSignUpEmail(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		return 1;
	}


}
