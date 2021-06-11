package com.ipbyhj.dev.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.ipbyhj.dev.common.Globals;
import com.ipbyhj.dev.security.filter.JwtAuthenticationFilter;
import com.ipbyhj.dev.security.handler.RememberMeSuccessHandler;
import com.ipbyhj.dev.security.handler.SignInFailureHandler;
import com.ipbyhj.dev.security.handler.SignInSuccessHandler;
import com.ipbyhj.dev.security.handler.SignOutSuccessHandler;
import com.ipbyhj.dev.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

/**
 * 스프링 시큐리티 설정
 * choi.hak.jun
 * 2021.04.18
 * @EnableWebSecurity : springSecurityFilterChain 포함시킴(자바기반)
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// 유저 정보를 DB에서 가져오는 UserDetailsService
	private final UserDetailsService userDetailsService;

	// 자동로그인 기능 지원
	private final DataSource dataSource;

	// TODO 추후 웹서버를 분리 시 사용 JWT 토큰 생성
	private final JwtTokenProvider jwtTokenProvider;

	// 패스워드 인코더
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				//루트와 모든 요청에 대해서는 요청이 허용(추후 수정)
				.antMatchers("/").permitAll()
				.antMatchers("/sign-in","/sign-up").permitAll()
				//H2데이터베이스 실습
				.antMatchers("/h2-console").permitAll()
				.antMatchers("/board/**").authenticated()								//인가
				.antMatchers("/search").access("hasAuthority('ROLE_ADMIN, ROLE_USER')")	//권한
				.and()
				// TODO 추후 웹서버 분리시 사용 JwtAutenticationFilter를 UsernamePasswordAuthenticationFilter 이전에 동작하게 만듬.
				//.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.formLogin()
				//로그인 폼 커스터마이징
				.loginPage(Globals.SECURITY_SIGN_IN_URL)										//실제 로그인 폼 요청 url (get)
				//실제 뷰에서 요청(Post)보내는 url
				.loginProcessingUrl(Globals.SECURITY_SIGN_IN_URL)								//실제 로그인 요청 url (post)
				.usernameParameter("email")														//화면 아이디에 해당하는 name값
				.passwordParameter("userPass")													//화면 비밀번호에 해당하는 name값
				.successHandler(new SignInSuccessHandler())										//로그인 성공 핸들러
				.failureHandler(new SignInFailureHandler())										//로그인 실패 핸들러
				.and()
			.logout()
				.logoutUrl(Globals.SECURITY_SIGNOUT_URL)										//로그아웃 요청 url (post)
				.logoutSuccessHandler(new SignOutSuccessHandler()).permitAll()					//로그아웃 성공 핸들러
				.invalidateHttpSession(true)													//로그아웃에 따른세션 비활성화
				.and()
			//자동 로그인 기능
			.rememberMe()
				.key("HashRememberMeKey")
				.rememberMeCookieName("remember-me")
				.rememberMeServices(new RememberMeSuccessHandler("HashRememberMeKey", userDetailsService))
				.tokenValiditySeconds(60000)
				.tokenRepository(getJDBCRepository())
				.rememberMeParameter("remember-me");

	}

	/**
	 * 사용자 패스워드를 다룰 때 BCrypt암호화 위한 설정
	 * choi.hak.jun
	 * 2021.05.12
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	/**
	 * 패스워드 암호화
	 * choi.hak.jun
	 * 2021.05.02
	 */
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}

	/**
	 * 자동로그인을 위한 jdbcTokenRepository
	 * choi.hak.jun
	 * 2021.05.02
	 */
	private PersistentTokenRepository getJDBCRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

	/**
	 * TODO 추후 웹서버를 분리 시 사용
	 * SpringSecurity에서 사용되는 인증객체를 Bean으로 등록할 때 사용(현재 미사용)
	 * choi.hak.jun
	 * 2021.05.12
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


}
