package com.ipbyhj.dev.board.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ipbyhj.dev.board.dto.BoardDTO;
import com.ipbyhj.dev.board.entity.BoardEntity;
import com.ipbyhj.dev.board.entity.BoardLikeEntity;
import com.ipbyhj.dev.board.repository.BoardLikeRepository;
import com.ipbyhj.dev.board.repository.BoardRepository;
import com.ipbyhj.dev.common.Globals;
import com.ipbyhj.dev.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JPABoardService {

	private final BoardRepository boardRepository;

	private final BoardLikeRepository boardLikeRepository;

	private final UserRepository userRepository;

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
	public int countBoard(Integer code) {
		if(code == null) {
			return (int) boardRepository.count();
		}
		return (int) boardRepository.countByCode(code);
	}

	//게시물 목록 조회
	public Page<BoardEntity> pagingBoard(Integer PageElementSize, Integer pageSize, Integer code){
		Pageable pageable = PageRequest.of(PageElementSize - 1, pageSize, Sort.by(Sort.Direction.DESC, "boardId"));
		byte useYn = 1;

		if(code == null) {
			return boardRepository.findAllByUseYn(useYn, pageable);
		}
		return boardRepository.findByCodeAndUseYn(code, useYn, pageable);
	}

	//조회수 증가
	public void increaseViewCount(Integer boardId) {
		Optional<BoardEntity> boardEntity = boardRepository.findByBoardId(boardId);

		boardEntity.get().setViewCount(boardEntity.get().getViewCount() + 1);
	}

	//게시물 작성
	public int saveBoard(BoardEntity boardEntity) {
		String userName = userRepository.findById(boardEntity.getWriterId())
										.get()
										.getUserName();
		boardEntity.setCreateBy(boardEntity.getWriterId());
		boardEntity.setWriterName(userName);

		boardRepository.save(boardEntity);
		boardRepository.flush();

		return boardEntity.getBoardId() != null ? 1 : 0;
	}

	//게시물 수정
	public int updateBoard(BoardEntity updatedEntity) {
		BoardEntity originEntity = boardRepository.findById(updatedEntity.getBoardId()).orElse(null);

		if(originEntity == null) return 0;

		originEntity.setTitle(updatedEntity.getTitle());
		originEntity.setContent(updatedEntity.getContent());
		originEntity.setCode(updatedEntity.getCode());
		originEntity.setUpdateBy(updatedEntity.getUpdateBy());
		originEntity.setUpdateTime(updatedEntity.getUpdateTime());

		boardRepository.save(originEntity);
		return 1;
	}

	//게시물 삭제
	public int deleteBoard(Integer boardId) {
		BoardEntity boardEntity = boardRepository.findByBoardId(boardId).orElse(null);
		if(boardEntity == null) return 0;

		boardEntity.setUseYn((byte) 0);

		boardRepository.save(boardEntity);

		return 1;
	}




	//카테고리 이름 추출하기
	public String getCategoryByBoard(BoardEntity board) {
		if(board.getCode().equals(Globals.BOARD_COMMUNITY)) {
			return  Globals.COMMUNITY;
		}
		else if(board.getCode().equals(Globals.BOARD_CODING)) {
			return Globals.CODING;
		}
		return Globals.ALL;
	}


	//쿠키에 따른 조회수 증가 처리
	public void updateViewCountByCookie(HttpServletRequest request, HttpServletResponse response, Integer boardId) {
		//쿠키가 같지 않으면 조회수 증가시키기.
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i < cookies.length; i++) {
				//쿠키가 이미 존재하면, 해당 쿠키 저장
				if(cookies[i].getName().equals("cookie"+boardId)) {
					viewCookie = cookies[i];
				}
			}
		}
		//쿠키가 존재하지 않으면 조회수 업데이트 후 쿠키 추가.
		if(viewCookie == null) {
			this.increaseViewCount(boardId);
			Cookie newCookie = new Cookie("cookie"+boardId, "|"+boardId+"|");
			response.addCookie(newCookie);
		}
	}

	public int setLike(Integer boardId,  String userId, String like) {
		BiFunction<String, String, Boolean> equals = (String::equals);

		//게시물 좋아요
		if(equals.apply(like, "1")) {
			try {
				BoardLikeEntity boardLikeEntity = BoardLikeEntity.builder()
															.boardId(boardId)
															.userId(userId)
															.build();
				this.saveBoardLike(boardLikeEntity);
				return 1;
			}catch (Exception e) {
				return 0;
			}
		}

		//게시물 좋아요 취소
		if(equals.apply(like, "0")) {
			try {
				this.deleteByBoardIdAndUserId(boardId, userId);
				return 1;
			}catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}


}
