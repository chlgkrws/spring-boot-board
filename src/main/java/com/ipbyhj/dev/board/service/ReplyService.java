package com.ipbyhj.dev.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipbyhj.dev.board.dto.ReplyDTO;
import com.ipbyhj.dev.board.mapper.ReplyMapper;

@Service
@Transactional
public class ReplyService {

	@Autowired
	ReplyMapper replyMapper;

	/**
	 * 댓글 조회
	 * choi.hak.jun
	 */
	public Map<String, Object> selectReply(String boardId) {
		return replyMapper.selectReply(boardId);
	}

	/**
	 * 댓글 조회(페이지 첫 로딩시)
	 * choi.hak.jun
	 */
	public List<ReplyDTO> selectReplyList(String boardId){
		return replyMapper.selectReplyList(boardId);
	}

	/*
	 * 댓글 삽입
	 * choi.hak.jun
	 */
	public int insertReply(Map<String, Object> params) {
		return replyMapper.insertReply(params);
	}

}
