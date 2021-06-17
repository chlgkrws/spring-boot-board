package com.ipbyhj.dev.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.ipbyhj.dev.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

/**
 * TODO 추후 웹서버를 분리 시 사용
 * Jwt 인증 필터(Jwt로 인증 작업을 진행)
 * @author cgw981
 * 2021.05.12
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{

	private final JwtTokenProvider jwtTokenProvider;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 헤더에서 JWT를 받아옴
		String token = jwtTokenProvider.resolveToken((HttpServletRequest)request);
		// 유효한 토큰인지 확인
		if(token != null && jwtTokenProvider.validToken(token)) {
			//토큰으로부터 UserDetails를 받아옴
			Authentication authentication = jwtTokenProvider.getAuthentication(token);

			//SecurityContext안에 있는 Context에 Authentication 객체 삽입
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
}
