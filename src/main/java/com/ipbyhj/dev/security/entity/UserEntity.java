package com.ipbyhj.dev.security.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

	@Id
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

	private Byte useYn;



	@OneToMany(mappedBy = "userAuthorities", fetch = FetchType.LAZY)
	private List<UserRole> userRoleList = new ArrayList<>();



//	@PrePersist
//	public void PrePersist() {
//	}

	@Builder
	public UserEntity(String userId, String userPass, String userName, String sex, String phone, String email,
			String identity, Byte useYn) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.identity = identity;
		this.useYn = useYn;
	}
}
