package com.ipbyhj.dev.security.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipbyhj.dev.security.entity.UserEntity;

/**
 * 유저 관련 JPA Repository
 * choi.hak.jun
 * 2021.04.18
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	public Optional<UserEntity> findById(String email);

	@EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"userRoleList","userRoleList.userAuthorities", "userRoleList.userAuthoritiesCode"})
	public UserEntity findByUserId(String userId);

	public List<UserEntity> findByIdentity(String identity);

	public List<UserEntity> findByUserNameLike(String keyword);


	/**
	 * 계정이 활성화 된 유저 조회
	 */
	public boolean existsByUserIdAndUseYn(String email, Byte useYn);
}
