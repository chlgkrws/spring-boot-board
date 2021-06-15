package com.ipbyhj.dev.board.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Integer boardId;

	private String title;
	private String writerId;
	private String writerName;
	private String content;

	@Column(insertable=false)
	private Integer viewCount;

	@Column(insertable=false)
	private Integer likeCount;
	private Integer code;

	@Column(updatable=false)
	private String createBy;
	@Column(updatable=false)
	private String createTime;

	@Column(insertable=false)
	private String updateBy;
	@Column(insertable=false)
	private String updateTime;

	@Column(insertable=false)
	private Byte useYn;

	@OneToMany(mappedBy = "boardLike", fetch = FetchType.LAZY)
	private Set<BoardLikeEntity> boardLikeSet = new HashSet<>();


	@PreUpdate
	public void preUpdate() {
		this.useYn = this.useYn == null ? 1 : this.useYn;
	}


}
