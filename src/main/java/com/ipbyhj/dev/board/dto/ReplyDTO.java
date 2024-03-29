package com.ipbyhj.dev.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ReplyDTO {

	private String replyId;
	private String boardId;
	private String parentRplId;
	private String writerId;
	private String writerName;
	private String content;
	private String likeCount;
	private String createBy;
	private String createTime;
	private String useYn;

	@Builder
	public ReplyDTO(String replyId, String boardId, String parentRplId, String writerId, String writerName,
			String content, String likeCount, String createBy, String createTime, String useYn) {
		super();
		this.replyId = replyId;
		this.boardId = boardId;
		this.parentRplId = parentRplId;
		this.writerId = writerId;
		this.writerName = writerName;
		this.content = content;
		this.likeCount = likeCount;
		this.createBy = createBy;
		this.createTime = createTime;
		this.useYn = useYn;
	}

}
/*
	CREATE TABLE `reply` (
	`reply_id` int(11) NOT NULL,
	`board_id` int(11) NOT NULL,
	`parent_rpl_id` int(11) DEFAULT NULL,
	`writer_id` varchar(100) COLLATE utf8_bin NOT NULL,
	`writer_name` varchar(30) COLLATE utf8_bin NOT NULL,
	`content` varchar(300) COLLATE utf8_bin DEFAULT NULL,
	`like` int(11) DEFAULT NULL,
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
	PRIMARY KEY (`reply_id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='게시판 댓글 테이블\n';
*/