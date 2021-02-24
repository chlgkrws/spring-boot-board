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
	 * 게시물 조회
	 * choi.hak.jun
	 */
	public List<BoardDTO> selectBoardList(@Param("code") String code, int displayPost, int postNum);

	/**
	 * 게시물 갯수
	 * choi.hak.jun
	 */
	public int selectBoardCount(String code);

	/**
	 * 게시물 보기
	 * choi.hak.jun
	 */
	public BoardDTO selectView(int boardId);
}
