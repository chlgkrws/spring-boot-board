package com.ipbyhj.dev.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ipbyhj.dev.security.domain.CustomUserDetails;
import com.ipbyhj.dev.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	@EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"userRoleList","userRoleList.userAuthorities"})
	public Optional<UserEntity> findById(String email);

	public UserEntity findByUserId(String userId);

	public List<UserEntity> findByEmail(String email);

	public List<UserEntity> findByIdentity(String identity);

	public List<UserEntity> findByUserNameLike(String keyword);
}
