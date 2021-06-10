package com.ipbyhj.dev.board.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ipbyhj.dev.board.entity.BoardEntity;
import com.ipbyhj.dev.board.entity.BoardLikeEntity;
import com.ipbyhj.dev.board.repository.BoardLikeRepository;
import com.ipbyhj.dev.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JPABoardService {

	private final BoardRepository boardRepository;

	private final BoardLikeRepository boardLikeRepository;

	public Optional<BoardEntity> findByBoardId(Integer boardId) {
		return boardRepository.findByBoardId(boardId);
	}

	//해당 게시물에 대한 유저의 좋아요 여부
	public boolean existsBoardLikeByUserId(Integer boardId, String userId){
		return boardLikeRepository.existsByBoardIdAndUserId(boardId, userId);
	}

	//게시물 좋아요
	public void saveBoardLike(BoardLikeEntity boardLikeEntity) {
		 boardLikeRepository.save(boardLikeEntity);
	}

	//게시물 좋아요 취소
	public void deleteByBoardIdAndUserId(Integer boardId, String userId) {
		boardLikeRepository.deleteByBoardIdAndUserId(boardId, userId);
	}

	//게시물 개수 조회
	public int countBoard(String code) {
		if(code.equals("")) {
			return (int) boardRepository.count();
		}
		return (int) boardRepository.countByCode(Integer.parseInt(code));
	}

	//게시물 페이징
	public Page<BoardEntity> pagingBoard(Integer PageElementSize, Integer pageSize, String code){
		//Pageable pageable = PageRequest.of(PageElementSize, pageSize, Sort.by(Sort.Direction.DESC, "board_id"));
		Pageable pageable = PageRequest.of(PageElementSize, pageSize, Sort.by(Sort.Direction.DESC, "boardId"));

		if(code.equals("")) {
			return boardRepository.findAll(pageable);
		}
		return boardRepository.findByCode(Integer.parseInt(code), pageable);
	}
}
