package com.ipbyhj.dev.board.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 엔티티
 * choi.hak.jun
 * Start 2021.04.27
 */
@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardEntity {

	@Id
	@Column(name = "board_id")
	@GeneratedValue
	private Integer boardId;

	private String title;
	private String writerId;
	private String writerName;
	private String content;
	private Integer viewCount;
	private Integer likeCount;
	private Integer code;
	private String createBy;

	private String createTime;
	private String updateTime;
	private Byte useYn;

	@OneToMany(mappedBy = "boardLike", fetch = FetchType.LAZY)
	private Set<BoardLikeEntity> boardLikeSet = new HashSet<>();
}
