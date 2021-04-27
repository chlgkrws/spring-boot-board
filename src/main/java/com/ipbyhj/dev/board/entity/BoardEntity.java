package com.ipbyhj.dev.board.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
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
public class BoardEntity {

	@Id
	@Column(name = "board_id")
	@GeneratedValue
	private Integer boardId;

	@Column
	private String title;

	@Column
	private String writerId;

	@Column
	private String writerName;

	@Column
	private String content;

	@Column
	private Integer viewCount;

	@Column
	private Integer likeCount;

	@Column
	private Integer code;

	@Column
	private String createBy;

	@Column
	private Byte useYn;

	@OneToMany(mappedBy = "boardLike", fetch = FetchType.LAZY)
	private Set<BoardLikeEntity> boardLikeSet = new HashSet<>();

	@Builder
	public BoardEntity(Integer boardId, String title, String writerId, String writerName, String content,
			Integer viewCount, Integer likeCount, Integer code, String createBy, Byte useYn) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.writerId = writerId;
		this.writerName = writerName;
		this.content = content;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.code = code;
		this.createBy = createBy;
		this.useYn = useYn;
	}



}
