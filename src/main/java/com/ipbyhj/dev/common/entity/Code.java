package com.ipbyhj.dev.common.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.ipbyhj.dev.security.entity.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 코드 값 정의
 * choi.hack.jun
 * 2021.04.22
 */
@Entity
@Table(name = "code")
@Getter
@Setter
public class Code {

	@Id
	@Column(name = "code")
	private int code;

	@Column(nullable = false)
	private String funcEn;

	//해당 변수가 Code Entity 소유가 아님을 알림.
	@OneToMany(mappedBy = "userAuthoritiesCode", fetch = FetchType.LAZY)
	private List<UserRole> userRoleList = new ArrayList<>();
}
