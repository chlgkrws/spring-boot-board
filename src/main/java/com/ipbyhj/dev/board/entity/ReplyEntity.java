package com.ipbyhj.dev.board.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ipbyhj.dev.common.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "reply")
public class ReplyEntity extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "reply_id")
	private Integer replyId;

	private Integer boardId;
	private Integer parentRplId;
	private String writerId;
	private String writerName;
	private String content;
	private String likeCount;
	private String useYn;
}
