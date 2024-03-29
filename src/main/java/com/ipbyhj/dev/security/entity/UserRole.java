package com.ipbyhj.dev.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ipbyhj.dev.common.entity.Code;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 유저 권한 테이블 조회
 * choi.hak.jun
 * 2021.04.18
 */
@Entity
@Table(name = "user_role")
@Getter
@Setter
public class UserRole {

	@Id
	@Column(name = "user_role_id")
	@GeneratedValue
	private int userRoleId;

	@Column
	private String user_id;

	@Column
	private int user_role_code;

	@ManyToOne(fetch = FetchType.LAZY)
	//user_role 컬럼 이름 - 조인할 테이블 컬럼 이름
	@JoinColumn(name = "user_role_code" , referencedColumnName = "code",insertable = false, updatable = false)
	private Code userAuthoritiesCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	private UserEntity userAuthorities;

}
