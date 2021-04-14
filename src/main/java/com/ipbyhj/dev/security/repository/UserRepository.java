package com.ipbyhj.dev.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipbyhj.dev.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	public List<UserEntity> findByUserId(String userId);

	public List<UserEntity> findByEmail(String email);

	public List<UserEntity> findByIdentity(String identity);

	public List<UserEntity> findByUserNameLike(String keyword);
}
