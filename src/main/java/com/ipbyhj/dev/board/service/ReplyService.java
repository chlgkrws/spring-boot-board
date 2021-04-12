package com.ipbyhj.dev.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import org.apache.commons.lang.StringUtils;
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
	 * 댓글과 대댓글 사이의 순서를 정렬함
	 */
	public List<ReplyDTO> selectReplyList(String boardId){
		List<ReplyDTO> replyList = replyMapper.selectReplyList(boardId);
		BiPredicate<ReplyDTO, ReplyDTO> equals = (parent, child) -> {return parent.getReplyId().equals(child.getParentRplId());};

		//부모 댓글
		List<ReplyDTO> parentReply = new ArrayList<ReplyDTO>();
		//자식 댓글
		List<ReplyDTO> childReply = new ArrayList<ReplyDTO>();
		//정렬된 댓글 리스트
		List<ReplyDTO> resultReply = new ArrayList<ReplyDTO>();

		//부모,자식 나누기
		for(ReplyDTO reply : replyList) {
			if(StringUtils.isEmpty(reply.getParentRplId())) {			//null exception 처리
				parentReply.add(reply);
			}else {
				childReply.add(reply);
			}
		}

		//순서 정렬하기
		for(ReplyDTO parent : parentReply) {
			resultReply.add(parent);
			for(ReplyDTO child : childReply){
				if(equals.test(parent, child)){
					resultReply.add(child);
				}
			}
		}
		return resultReply;
	}

	/**
	 * 댓글 삽입
	 * choi.hak.jun
	 */
	// public int insertReply(Map<String, Object> params) {
	public int insertReply(ReplyDTO replyDTO) {
		return replyMapper.insertReply(replyDTO);
	}

	/**
	 * 댓글 수정
	 * choi.hak.jun
	 */
	public int updateReply(String replyId, String writerId, String content) {
		return replyMapper.updateReply(replyId, writerId, content);
	}

	/**
	 * 댓글 삭제
	 * choi.hak.jun
	 */
	public int deleteReply(int replyId) {
		return replyMapper.deleteReply(replyId);
	}

	/**
	 * 댓글 좋아요 증가
	 * choi.hak.jun
	 */
	public int updateLike(int replyId) {
		return replyMapper.updateLike(replyId);
	}

}
