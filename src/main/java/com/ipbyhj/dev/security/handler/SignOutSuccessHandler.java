package com.ipbyhj.dev.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.ipbyhj.dev.common.Globals;

/**
 * 시큐리티 로그아웃 핸들러
 * choi.hak.jun
 * 2021.04.26
 */
public class SignOutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();

		//로그인 시 설정했던 세션 삭제
		session.removeAttribute("userId");
		session.removeAttribute("userName");

		response.sendRedirect(Globals.SECURITY_SIGNOUT_SUCCESS_URL);
	}

}