package com.ipbyhj.dev.util;

import com.ipbyhj.dev.common.Globals;

public class GlobalsUtils {

	/**
	 * 게시판 목록 코드값 가져오기
	 */
	public static Integer getCodeValue(String name) {
		if(name.equals("all")) {  													//board name로 가져오기
			return Globals.BOARD_ALL;
		}else if(name.equals("community")) {
			return Globals.BOARD_COMMUNITY;
		}else if(name.equals("coding")) {
			return Globals.BOARD_CODING;
		}
		return null;
	}

	/**
	 * 게시판 목록 이름 가져오기
	 */
	public static String getCodeName(String name) {
		if(name.equals("community")) {
			return "community";
		}else if(name.equals("coding")) {
			return "coding";
		}

		return "all";
	}

}
