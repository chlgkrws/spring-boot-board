package com.ipbyhj.dev.main.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 2021-02-06
 * User information value Object
 * @author cgw981
 *
 */
@Getter
@Setter
@ToString
public class UserDTO {
	private String userId;
	private String userPass;
	private String userName;
	private String sex;
	private String phone;
	private String email;
	private String identity;
	private String role;
	private String createBy;
	private Timestamp createTime;
	private String updateBy;
	private Timestamp updateTime;
	private String usesYn;
}
	/*
		CREATE TABLE `user` (
		  `user_id` varchar(100) COLLATE utf8_bin NOT NULL,
		  `user_pass` varchar(200) COLLATE utf8_bin NOT NULL,
		  `user_name` varchar(30) COLLATE utf8_bin NOT NULL,
		  `sex` varchar(5) COLLATE utf8_bin DEFAULT NULL,
		  `phone` varchar(30) COLLATE utf8_bin DEFAULT NULL,
		  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
		  `identity` varchar(60) COLLATE utf8_bin DEFAULT NULL,
		  `role` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT 'ROLE_USER',
		  `create_by` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT 'sys',
		  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
		  `update_by` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT 'sys',
		  `update_time` timestamp NOT NULL DEFAULT current_timestamp(),
		  `uses_yn` tinyint(4) NOT NULL DEFAULT 1,
		  `reserve1` varchar(300) COLLATE utf8_bin DEFAULT NULL,
		  `reserve2` varchar(300) COLLATE utf8_bin DEFAULT NULL,
		  `reserve3` varchar(300) COLLATE utf8_bin DEFAULT NULL,
		  `reserve4` varchar(300) COLLATE utf8_bin DEFAULT NULL,
		  `reserve5` varchar(300) COLLATE utf8_bin DEFAULT NULL,
		  PRIMARY KEY (`user_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='유저정보 테이블';

	 */

