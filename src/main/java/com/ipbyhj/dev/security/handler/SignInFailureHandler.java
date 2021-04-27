package com.ipbyhj.dev.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ipbyhj.dev.common.Globals;

import lombok.extern.log4j.Log4j2;

/**
 * 시큐리티 로그인 실패 핸들러
 * choi.hak.jun
 * 2021.04.25
 */
@Log4j2
public class SignInFailureHandler implements AuthenticationFailureHandler{

	/**
	 * 에러메세지와 함께 sign-in 페이지로 이동
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		request.setAttribute("errMsg", Globals.SECURITY_ERROR_MSG);

		/**
		 * 에러 부분 수정하기 2021.04.27
		 */
		request.getRequestDispatcher(Globals.SECURITY_SIGNIN_FAILURE_URL).forward(request, response);
	}
}