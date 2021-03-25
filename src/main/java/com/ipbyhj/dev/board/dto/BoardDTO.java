package com.ipbyhj.dev.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDTO {

	private String boardId;
	private String title;
	private String writerId;
	private String writerName;
	private String content;
	private String viewCount;
	private String likeCount;
	private String code;
	private String createBy;
	private String createTime;
	private String updateTime;
	private String useYn;
}

/*
	CREATE TABLE `board` (
	`board_id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(300) COLLATE utf8_bin NOT NULL,
	`writer_id` varchar(100) COLLATE utf8_bin NOT NULL,
	`writer_name` varchar(30) COLLATE utf8_bin NOT NULL,
	`content` longtext COLLATE utf8_bin NOT NULL,
	`view_count` int(11) DEFAULT NULL,
	`like` int(11) DEFAULT NULL,
	`type` int(11) DEFAULT NULL,
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
	PRIMARY KEY (`board_id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='게시판 테이블';
*/