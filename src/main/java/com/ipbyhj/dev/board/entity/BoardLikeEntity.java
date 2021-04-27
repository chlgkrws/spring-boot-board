package com.ipbyhj.dev.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ipbyhj.dev.security.entity.UserEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 좋아요 엔티티
 * choi.hak.jun
 * Start 2021.04.27
 */
@Entity
@Table(name = "board_like")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardLikeEntity {

	@Id
	@Column(name="board_like_id")
	@GeneratedValue
	private Integer boardLikeId;

	@Column(name = "board_id")
	private Integer boardId;

	@Column(name = "user_id")
	private String userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", referencedColumnName = "board_id", insertable = false, updatable = false)
	private BoardEntity boardLike;

}
