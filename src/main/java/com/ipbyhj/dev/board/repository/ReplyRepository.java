package com.ipbyhj.dev.board.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipbyhj.dev.board.entity.ReplyEntity;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer>{

	@Query("SELECT r FROM reply r WHERE board_id = :boardId ")
	public List<ReplyEntity> findByBoardId(@Param("boardId") Integer boardId);

}
