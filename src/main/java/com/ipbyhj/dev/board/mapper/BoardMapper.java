package com.ipbyhj.dev.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ipbyhj.dev.board.dto.BoardDTO;

@Mapper
@Repository
public interface BoardMapper {

	/**
	 * 게시판 조회
	 * choi.hak.jun
	 */
	public List<BoardDTO> selectBoardList(@Param("code") String code);
}