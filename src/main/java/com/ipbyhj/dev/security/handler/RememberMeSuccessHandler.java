package com.ipbyhj.dev.security.handler;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.Assert;

import com.ipbyhj.dev.security.domain.CustomUserDetails;
import com.ipbyhj.dev.security.service.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * 자동로그인 성공  핸들러
 * choi.hak.jun
 * 2021.05.03
 */
@Log4j2
public class RememberMeSuccessHandler extends TokenBasedRememberMeServices{

	@Autowired
	UserService userService;

	public RememberMeSuccessHandler(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
		log.info("RememberMeSuccessHandler Success");
	}

	/**
	 * Override TokenBasedRememberMeServices Method
	 */
	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
			HttpServletResponse response) {
		if (cookieTokens.length != 3) {
			throw new InvalidCookieException(
					"Cookie token did not contain 3" + " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
		}
		long tokenExpiryTime = getTokenExpiryTime(cookieTokens);
		if (isTokenExpired(tokenExpiryTime)) {
			throw new InvalidCookieException("Cookie token[1] has expired (expired on '" + new Date(tokenExpiryTime)
					+ "'; current time is '" + new Date() + "')");
		}
		// Check the user exists. Defer lookup until after expiry time checked, to
		// possibly avoid expensive database call.
		UserDetails userDetails = getUserDetailsService().loadUserByUsername(cookieTokens[0]);
		Assert.notNull(userDetails, () -> "UserDetailsService " + getUserDetailsService()
				+ " returned null for username " + cookieTokens[0] + ". " + "This is an interface contract violation");
		// Check signature of token matches remaining details. Must do this after user
		// lookup, as we need the DAO-derived password. If efficiency was a major issue,
		// just add in a UserCache implementation, but recall that this method is usually
		// only called once per HttpSession - if the token is valid, it will cause
		// SecurityContextHolder population, whilst if invalid, will cause the cookie to
		// be cancelled.
		String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, userDetails.getUsername(),
				userDetails.getPassword());
		if (!equals(expectedTokenSignature, cookieTokens[2])) {
			throw new InvalidCookieException("Cookie token[2] contained signature '" + cookieTokens[2]
					+ "' but expected '" + expectedTokenSignature + "'");
		}

		/**
		 * Remember-me 로그인 시 세션이 잡히지 않는 에러 해결 구문
		 * 아래와 같이 uesrDetails를 이용해서 이름을 가져온 뒤, session에 추가.
		 */
		//Custom RememberMe Action Start
		HttpSession session = request.getSession();

		String userId = userDetails.getUsername();
		String userName = ((CustomUserDetails) userDetails).getSpringUserName();

		log.info("Custom RememberMe process : " + userId);
		if(!org.apache.commons.lang3.StringUtils.isBlank(userId) && !org.apache.commons.lang3.StringUtils.isBlank(userName)) {
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userName);
		}
		//Custom RememberMe Action End

		return userDetails;
	}

	private long getTokenExpiryTime(String[] cookieTokens) {
		try {
			return new Long(cookieTokens[1]);
		}
		catch (NumberFormatException nfe) {
			throw new InvalidCookieException(
					"Cookie token[1] did not contain a valid number (contained '" + cookieTokens[1] + "')");
		}
	}

	/**
	 * Constant time comparison to prevent against timing attacks.
	 */
	private static boolean equals(String expected, String actual) {
		byte[] expectedBytes = bytesUtf8(expected);
		byte[] actualBytes = bytesUtf8(actual);
		return MessageDigest.isEqual(expectedBytes, actualBytes);
	}

	private static byte[] bytesUtf8(String s) {
		return (s != null) ? Utf8.encode(s) : null;
	}



}
