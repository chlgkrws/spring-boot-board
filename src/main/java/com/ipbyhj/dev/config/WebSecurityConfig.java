package com.ipbyhj.dev.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				//루트와 모든 요청에 대해서는 요청이 허용(추후 수정)
				.antMatchers("/", "*").permitAll()
				//.anyRequest().authenticated()
				.and()
			.formLogin()
				//로그인 폼 커스터마이징
				.loginPage("/sign-in")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
}
