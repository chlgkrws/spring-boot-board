package com.ipbyhj.dev.config;


import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.ipbyhj.dev.common.Globals;
import com.ipbyhj.dev.security.handler.RememberMeSuccessHandler;
import com.ipbyhj.dev.security.handler.SignInFailureHandler;
import com.ipbyhj.dev.security.handler.SignInSuccessHandler;
import com.ipbyhj.dev.security.handler.SignOutSuccessHandler;

/**
 * 스프링 시큐리티 설정
 * choi.hak.jun
 * 2021.04.18
 * @EnableWebSecurity : springSecurityFilterChain 포함시킴(자바기반)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	DataSource dataSource;

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
				.antMatchers("/board/**").authenticated()
				.antMatchers("/search").access("hasAuthority('ROLE_ADMIN')")
				//.anyRequest().authenticated()
				.and()
			.formLogin()
				//로그인 폼 커스터마이징
				.loginPage(Globals.SECURITY_SIGN_IN_URL)
				//실제 뷰에서 요청(Post)보내는 url
				.loginProcessingUrl(Globals.SECURITY_SIGN_IN_URL)
				.usernameParameter("email")
				.passwordParameter("userPass")
				.successHandler(new SignInSuccessHandler())
				.failureHandler(new SignInFailureHandler())
				.and()
			.logout()
				.logoutUrl(Globals.SECURITY_SIGNOUT_URL)
				.logoutSuccessHandler(new SignOutSuccessHandler()).permitAll()
				.invalidateHttpSession(true)
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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	}

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


}
