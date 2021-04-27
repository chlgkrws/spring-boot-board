package com.ipbyhj.dev.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipbyhj.dev.board.entity.BoardLikeEntity;

/**
 * 게시물 좋아요 관련 JPA Repository
 * choi.hak.jun
 * 2021.04.27
 */
public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Integer>{
	public boolean existsByBoardIdAndUserId(Integer boardId, String userId);
}
