package com.ipbyhj.dev.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserDTO {

	private String userId;
	private String userPass;
	private String userName;
	private String sex;
	private String phone;
	private String email;
	private String identity;
	private String role;
	private String useYn;

	@Builder
	public UserDTO(String userId, String userPass, String userName, String sex, String phone, String email,
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
