package com.ipbyhj.dev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ipbyhj.dev.security.handler.LoginSuccessHandler;

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

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				//루트와 모든 요청에 대해서는 요청이 허용(추후 수정)
				.antMatchers("/","**/**").permitAll()
				//무한루프 방지.antMatchers("/sign-in").permitAll()
				.antMatchers("/board/**").authenticated()
				//.anyRequest().authenticated()
				.and()
			.formLogin()
				//로그인 폼 커스터마이징
				.loginPage("/sign-in")
				//실제 뷰에서 요청(Post)보내는 url
				.loginProcessingUrl("/sign-in")
				.failureUrl("/")
				.usernameParameter("email")
				.passwordParameter("userPass")
				.successHandler(new LoginSuccessHandler())
				.and()
			.logout()
				.permitAll();
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
}
