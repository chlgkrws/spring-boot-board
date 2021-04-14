package com.ipbyhj.dev.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipbyhj.dev.security.entity.UserEntity;
import com.ipbyhj.dev.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 사용자 리스트 검색
	 * choi.hak.jun
	 */
	public List<UserEntity> findAll(){
		List<UserEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(e -> users.add(e));

		return users;
	}

	/**
	 * 사용자 검색
	 * choi.hak.jun
	 */
	public Optional<UserEntity> findById(String userId){
		Optional<UserEntity> user = userRepository.findById(userId);
		return user;
	}

	public UserEntity save(UserEntity user) {
		userRepository.save(user);
		return user;
	}

	/**
	 * 사용자 정보 업데이트
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

}
