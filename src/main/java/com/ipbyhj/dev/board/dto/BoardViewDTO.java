package com.ipbyhj.dev.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * 미사용
 * @author cgw981
 *
 */
public class BoardViewDTO {

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
