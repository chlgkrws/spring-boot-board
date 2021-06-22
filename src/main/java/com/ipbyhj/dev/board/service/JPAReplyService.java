package com.ipbyhj.dev.board.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ipbyhj.dev.board.entity.ReplyEntity;
import com.ipbyhj.dev.board.repository.ReplyRepository;
import com.ipbyhj.dev.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JPAReplyService {

	private final ReplyRepository replyRepository;

	private final UserRepository userRepository;

	public List<ReplyEntity> findReplyListByBoardId(Integer boardId) {
		return replyRepository.findByBoardId(boardId);
	}

	public int saveReply(ReplyEntity replyEntity) {
		String writerName = userRepository.findByUserId(replyEntity.getWriterId()).getUserName();

		replyEntity.setWriterName(writerName);
		ReplyEntity savedReplyEntity = replyRepository.save(replyEntity);

		if(savedReplyEntity == null) {
			return 0;
		}

		return savedReplyEntity.getReplyId();
	}

	public int updateReply(ReplyEntity replyEntity) {
		ReplyEntity findReplyEntity = replyRepository.findById(replyEntity.getReplyId()).orElse(null);

		if(findReplyEntity == null) {
			return 0;
		}

		findReplyEntity.setContent(replyEntity.getContent());

		return replyRepository.save(findReplyEntity).getReplyId() != null ? 1 : 0;
	}

}
