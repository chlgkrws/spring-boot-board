package com.ipbyhj.dev.security.domain;

import java.util.Collection;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 스프링 시큐리티가 로그인 인증에 사용하는 객체
 * choi.hak.jun
 * 2021.04.18
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@ToString
public class CustomUserDetails implements UserDetails{

	/**
	 * 스프링 시큐리티가 사용하는 유저객체
	 */
	private static final long serialVersionUID = 8009303909213367195L;

	private String userId;
    private String userPass;
    private String userName;
    private Byte useYn;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 계정이 갖고있는 권한 목록을 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return authorities;
    }

    /**
     * 계정의 비밀번호를 리턴
     */
    @Override
    public String getPassword() {
         return userPass;
    }

    /**
     * 계정의 이름을 리턴
     */
    @Override
    public String getUsername() {
         return userName;
    }

    /**
     * 계정이 만료되지 않았는지 리턴
     */
    @Override
    public boolean isAccountNonExpired() {
         return true;
    }

    /**
     * 계정이 잠겨있지 않았는지 리턴
     */
    @Override
    public boolean isAccountNonLocked() {
         return true;
    }

    /**
     * 비밀번호가 만료되지 않았는지 리턴
     */
    @Override
    public boolean isCredentialsNonExpired() {
         return true;
    }

    /**
     *  계정이 활성화(사용가능)인지 리턴
     */
    @Override
    public boolean isEnabled() {
         return useYn == 1 ? true : false;
    }

    public String getUserName() {
    	return userName;
    }
}
