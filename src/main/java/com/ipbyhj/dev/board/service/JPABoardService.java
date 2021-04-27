package com.ipbyhj.dev.board.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipbyhj.dev.board.entity.BoardEntity;
import com.ipbyhj.dev.board.entity.BoardLikeEntity;
import com.ipbyhj.dev.board.repository.BoardLikeRepository;
import com.ipbyhj.dev.board.repository.BoardRepository;

@Service
@Transactional
public class JPABoardService {

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	BoardLikeRepository boardLikeRepository;

	public BoardEntity findByBoardId(Integer boardId) {
		return boardRepository.findByBoardId(boardId);
	}

	//해당 게시물에 대한 유저의 좋아요 여부
	public boolean existsBoardLikeByUserId(Integer boardId, String userId){
		return boardLikeRepository.existsByBoardIdAndUserId(boardId, userId);
	}
}
