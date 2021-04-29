package com.ipbyhj.dev.board.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.ipbyhj.dev.board.entity.BoardEntity;

/**
 * 게시물 JPA Repository
 * choi.hak.jun
 * 2021.04.27
 */
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

	//한 개의 게시물 가져오기
	@EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"boardLikeSet","boardLikeSet.boardLike"})
	public BoardEntity findByBoardId(Integer boardId);
}
