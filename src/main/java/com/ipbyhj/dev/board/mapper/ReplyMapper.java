package com.ipbyhj.dev.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ipbyhj.dev.board.dto.ReplyDTO;

@Mapper
@Repository
public interface ReplyMapper {

	/**
	 * 댓글 조회
	 * choi.hak.jun
	 */
	public Map<String, Object> selectReply(String boardId);

	/**
	 * 댓글 조회(페이지 첫 로딩시)
	 * choi.hak.jun
	 */
	public List<ReplyDTO> selectReplyList(@Param("boardId")String boardId);


	/**
	 * 댓글 삽입
	 * choi.hak.jun
	 */
	public int insertReply(Map<String, Object> params);
}
