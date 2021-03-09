package com.ipbyhj.dev.board.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReplyMapper {

	/**
	 * 댓글 조회
	 * choi.hak.jun
	 */
	public Map<String, Object> selectReply(String boardId);

	/**
	 * 댓글 삽입
	 * choi.hak.jun
	 */
	public int insertReply(Map<String, Object> params);
}
