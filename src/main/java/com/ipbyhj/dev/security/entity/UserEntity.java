package com.ipbyhj.dev.security.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity(name="user")
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_pass")
	private String userPass;

	@Column(name = "user_name")
	private String userName;

	private String sex;
	private String phone;
	private String email;
	private String identity;

	@Column(name = "role")
	@ColumnDefault("ROLE_USER")
	private String role;
	private String useYn;

	@Builder
	public UserEntity(String userId, String userPass, String userName, String sex, String phone, String email,
			String identity, String role, String useYn) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.identity = identity;
		this.role = role;
		this.useYn = useYn;
	}
}
