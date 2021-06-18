package com.ipbyhj.dev.board.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipbyhj.dev.board.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

	//한 개의 게시물 가져오기
	@EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"boardLikeSet","boardLikeSet.boardLike"})
	public Optional<BoardEntity> findByBoardId(Integer boardId);


	//게시물 개수
	@Query("SELECT count(b) FROM board b WHERE code = :code")
	public long countByCode(@Param("code") Integer code);

	//게시물 리스트 페이징(all)
	public Page<BoardEntity> findAllByUseYn(byte useYn, Pageable pageable);

	//게시물 리스트 페이징(code)
	public Page<BoardEntity> findByCodeAndUseYn(Integer code, byte useYn, Pageable pageable);


}
