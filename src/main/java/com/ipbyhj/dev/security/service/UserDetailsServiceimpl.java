package com.ipbyhj.dev.security.service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ipbyhj.dev.security.domain.CustomUserDetails;
import com.ipbyhj.dev.security.entity.UserEntity;
import com.ipbyhj.dev.security.entity.UserRole;
import com.ipbyhj.dev.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

/**
 * UserDetailsService 구현체
 * choi.hak.jun
 * 2021.04.18
 *
 * 실제 파라미터로 들어온 아이디/비밀번호랑 loadUserByUsername 메서드에서 반환하는 아이디/비밀번호와 비교하기 위해 쓰임
 * 해당 구현체를 선언하면 스트링 부트 실행 초기 시큐리티 비밀번호를 할당받지 못함.
 */
@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserDetailsServiceimpl implements UserDetailsService{

	private final UserRepository userRepository;

	/**
	 * loadUserByUsername
	 * 실제 시큐리티에서 유저 Id 값은 DB에서 해당 유저의 정보를 가져오기 위함으로 사용되고, 시큐리티에서 비교하는 것은 암호화된 비밀번호다.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//useYn = 1인 유저가 있는지 체크
		if(!userRepository.existsByUserIdAndUseYn(username, (byte) 1)) {
			return null;
		}
		log.info("Start loadUserByUsername findByUserId");
		UserEntity userFromDB = userRepository.findByUserId(username);
		log.info("userName : "+userFromDB.getUserName());
		CustomUserDetails userToSecurity = CustomUserDetails.builder()
											.userId( 	userFromDB.getEmail())
											.userPass(	userFromDB.getUserPass())
											.name(  	userFromDB.getUserName())
											.useYn( 	userFromDB.getUseYn())
											.authorities(getAuthorities(userFromDB))
											.build();
		log.info("End loadUserByUsername findByUserId");
		return userToSecurity;
	}

	/**
	 * 권한 받아오는 부분
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(UserEntity userEntity) {
		String[] userRoles =  convert(userEntity.getUserRoleList());
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}

	/**
	 * 실제 권한 매핑 함수
	 */
    public String[] convert(List<UserRole> list)
    {
        String[] arrayOfString = new String[list.size()];

        int index = 0;
        for (UserRole userRole : list) {
            arrayOfString[index++] = userRole.getUserAuthoritiesCode().getFuncEn();
        }
        return arrayOfString;
    }

}
