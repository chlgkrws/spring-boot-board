package com.ipbyhj.dev.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipbyhj.dev.board.dto.BoardDTO;
import com.ipbyhj.dev.board.mapper.BoardMapper;

@Service
@Transactional
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
	public int selectBoardCount(String code) {
		return boardMapper.selectBoardCount(code);
	}

	/**
	 * 게시물 보기
	 * choi.hak.jun
	 */
	public BoardDTO selectView(int boardId) {
		return boardMapper.selectView(boardId);
	}

	/**
	 * 조회수 증가
	 * choi.hak.jun
	 */
	public void updateViewCount(int boardId) {
		boardMapper.updateViewCount(boardId);
	}

	/**
	 * 게시물 삭제
	 * choi.hak.jun
	 */
	public int deleteBoard(int boardId) {
		return boardMapper.deleteBoard(boardId);
	}

	/**
	 * 게시물 수정
	 * choi.hak.jun
	 */
	public int modifyBoard(int boardId) {
		return boardMapper.modifyBoard(boardId);
	}

}
