package com.ipbyhj.dev.security.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipbyhj.dev.security.entity.UserEntity;
import com.ipbyhj.dev.security.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * 로그인 페이지
	 * choi.hak.jun
	 * Start 2021.04.13
	 */
	@GetMapping("/sign-in")
	public ModelAndView getSignIn(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		//스프링 시큐리티 인증 성공 시 이전 페이지로 이동 시키기 위한 세션
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referer);

		//AuthenticationFailureHandler 에러메세지 세션 값 동기화
		request.setAttribute("errMsg", request.getAttribute("errMsg"));


		modelAndView.setViewName("dev/sign/sign-in");
		return modelAndView;
	}

	/**
	 * 회원가입 페이지
	 * choi.hak.jun
	 * Start 2021.04.13
	 */
	@GetMapping("/sign-up")
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
	@PostMapping("/sign-up")
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
	 *
	 * Start 2021.04.17
	 */
	@GetMapping("/sign-up-email")
	public ModelAndView getSignUpEmail(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		modelAndView.setViewName("dev/sign/sign-up-by-email");
		return modelAndView;
	}

	/**
	 * 회원가입(email)
	 * choi.hak.jun
	 * return 1 - 성공, 0 - 실패
	 * Start 2021.04.17
	 */
	@PostMapping("/sign-up-email")
	public int setSignUpEmail(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		return 1;
	}
	/**
	 * End 2021.04.17
	 */


	/**
	 * 로그인 권한 가져오기 테스트
	 */
	@GetMapping(value = "/jpatest")
	public String jpaJoinTest(HttpServletRequest request) {
		System.out.println(userService.jpaJoinTest(("cgw981@naver.com" )).getUserRoleList().get(1).getUser_role_code());
		return "jpaTest";
	}


}
