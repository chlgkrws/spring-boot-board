package com.ipbyhj.dev.board.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ipbyhj.dev.board.entity.ReplyEntity;
import com.ipbyhj.dev.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JPAReplyService {

	private final ReplyRepository replyRepository;


	public List<ReplyEntity> findReplyListByBoardId(Integer boardId) {
		return replyRepository.findByBoardId(boardId);
	}
}
