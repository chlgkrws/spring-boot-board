package com.ipbyhj.dev.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ipbyhj.dev.common.Globals;
import com.ipbyhj.dev.security.domain.CustomUserDetails;
import com.ipbyhj.dev.security.service.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * 시큐리티 로그인 성공 핸들러
 * choi.hak.jun
 * 2021.04.25
 */
@Log4j2
public class SignInSuccessHandler  implements AuthenticationSuccessHandler{

	@Autowired
	UserService userService;

	/**
	 * 로그인 성공 시 호출되는 메서드
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String userId = request.getParameter("email");

		//아이디와 이름을 세션에 추가
		session.setAttribute("userId", userId);
		session.setAttribute("userName", ((CustomUserDetails)authentication.getPrincipal()).getSpringUserName());	//

		//UserDetails
		log.info("authentication getPrincipal = " +authentication.getPrincipal());

		//시큐리티로 인증하기 전 페이지로 이동 (prevPage - userController와 연동)
		if(session != null) {
			String redirectUrl = (String) session.getAttribute("prevPage");
			if(redirectUrl != null && !redirectUrl.equals(Globals.SECURITY_SIGN_IN_URL)) {
				session.removeAttribute("prevPage");
				response.sendRedirect(redirectUrl);
				return;
			}
		}

		//로그인 성공 시 홈 화면으로 이동
		response.sendRedirect(Globals.SECURITY_SIGNIN_SUCCESS_URL);
	}
}