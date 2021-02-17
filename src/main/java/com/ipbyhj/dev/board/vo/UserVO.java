package com.ipbyhj.dev.board.vo;

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
public class UserVO {
	private String userSeq;
	private String userId;
	private String userPass;
	private String sex;
	private String phone;
	private String email;
	private String identity;
	private String role;
	private String createBy;
	private Timestamp createTime;
	private String updateBy;
	private Timestamp updateTime;
	private String wouldYouYn;
}
	/*
		CREATE TABLE `humble_project`.`user` (
	  `user_seq` INT NOT NULL AUTO_INCREMENT,
	  `user_id` VARCHAR(100) NOT NULL,
	  `user_pass` VARCHAR(200) NOT NULL,
	  `sex` VARCHAR(5) NULL,
	  `phone` VARCHAR(30) NULL,
	  `email` VARCHAR(100) NULL,
	  `identity` VARCHAR(60) NULL,
	  `role` VARCHAR(40) NOT NULL DEFAULT 'ROLE_USER',
	  `create_by` VARCHAR(100) NOT NULL DEFAULT 'sys',
	  `create_time` TIMESTAMP NOT NULL DEFAULT now(),
	  `update_by` VARCHAR(100) NOT NULL DEFAULT 'sys',
	  `update_time` TIMESTAMP NOT NULL DEFAULT now(),
	  `would_you_yn` TINYINT NOT NULL DEFAULT 1,
	  `reserve1` VARCHAR(300) NULL,
	  `reserve2` VARCHAR(300) NULL,
	  `reserve3` VARCHAR(300) NULL,
	  `reserve4` VARCHAR(300) NULL,
	  `reserve5` VARCHAR(300) NULL,
	  PRIMARY KEY (`user_seq`))
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COLLATE = utf8_bin
	COMMENT = '유저정보 테이블';
	 */