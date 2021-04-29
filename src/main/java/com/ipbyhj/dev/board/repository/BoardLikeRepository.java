package com.ipbyhj.dev.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipbyhj.dev.board.entity.BoardLikeEntity;

/**
 * 게시물 좋아요 관련 JPA Repository
 * choi.hak.jun
 * 2021.04.27
 */
public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Integer>{
	/**
	 * 유저가 게시물을 눌렀는지 판단
	 */
	public boolean existsByBoardIdAndUserId(Integer boardId, String userId);

	/**
	 * 유저가 좋아요 누른 게시물을 반환
	 */
	public BoardLikeEntity findByBoardIdAndUserId(Integer boardId, String userId);

	/**
	 * 유저가 좋아요 누른 게시물 삭제
	 */
	public void deleteByBoardIdAndUserId(Integer boardId, String userId);
}
