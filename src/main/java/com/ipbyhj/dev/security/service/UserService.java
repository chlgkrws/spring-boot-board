package com.ipbyhj.dev.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipbyhj.dev.security.domain.CustomUserDetails;
import com.ipbyhj.dev.security.entity.UserEntity;
import com.ipbyhj.dev.security.repository.UserRepository;

/**
 * 유저 인증/가입/탈퇴 서비스
 * choi.hak.jun
 * 2021.04.18
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 패스워드 암호화 방식
	 */
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * 유저 리스트 검색
	 * choi.hak.jun
	 */
	public List<UserEntity> findAll(){
		List<UserEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(e -> users.add(e));

		return users;
	}

	/**
	 * 유저 검색(id)
	 * choi.hak.jun
	 * @param userId
	 */
	public Optional<UserEntity> findById(String userId) {
		Optional<UserEntity> user = userRepository.findById(userId);
		return user;
	}

	/**
	 * 유저 검색(시큐리티 전용)
	 * choi.hak.jun
	 * @param email
	 */
	public UserEntity findByEmail(String email) {
		UserEntity user =  userRepository.findByUserId(email);
		return user;
	}

	/**
	 * 유저 존재 여부
	 * choi.hak.jun
	 */
	public boolean chekcEmailDuplicate(String email) {
		return userRepository.existsById(email);
	}


	/**
	 * 회원 가입
	 * choi.hak.jun
	 */
	public UserEntity save(UserEntity user) throws UsernameNotFoundException {
		user.setUserPass(passwordEncoder.encode(user.getUserPass()));
		userRepository.save(user);
		return user;
	}

	/**
	 * 유저 정보 업데이트
	 * choi.hak.jun
	 */
	public void updateById(String userId, UserEntity user) {
		Optional<UserEntity> e = userRepository.findById(userId);

		if(e.isPresent()) {
			e.get().setUserId(user.getUserId());
			e.get().setEmail(user.getEmail());
			e.get().setUserName(user.getUserName());
			userRepository.save(user);
		}
 	}

	/**
	 * 로그인 권한 가져오기 테스트
	 * choi.hak.jun
	 */
	public UserEntity jpaJoinTest(String email) {
		return userRepository.findByUserId(email);
	}
}
