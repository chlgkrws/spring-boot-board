package com.ipbyhj.dev.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipbyhj.dev.board.dto.BoardDTO;
import com.ipbyhj.dev.board.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;


	/**
	 * 테이블 조회
	 * choi.hak.jun
	 */
	public List<BoardDTO> selectBoardList (String code, int displayPost, int postNum){
		return boardMapper.selectBoardList(code, displayPost, postNum );
	}

	/**
	 * 게시물 갯수
	 * choi.hak.jun
	 */
	public int selectBoardCount() {
		return boardMapper.selectBoardCount();
	}


}
