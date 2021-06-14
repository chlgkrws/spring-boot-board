package com.ipbyhj.dev.common;

public class Globals {

	/**
	 * Global Server url
	 */
	public static final String LOCAL_SERVER_URL = "http://localhost:8080";
	public static final String API_SERVER_URL = "http://localhost:";
	public static final String BOS_SERVER_URL = "http://localhost:";

	/**
	 * header bold
	 * Header에서 화면 전환시 글씨 굵기 지정
	 */
	public static final String BOLD_TYPE_BOARD = "board";
	public static final String BOLD_TYPE_MAIN = "main";

	/**
	 * Date or Time
	 */
	public static final String MONTH_FORMAT = "yyyy-MM";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Media path
	 */
	public static final String MEDIA_ROOT_PATH = "";
	public static final String WEB_FILE_PATH = "";
	public static final String VIDEO_ROOT_URL = "";

	/**
	 * Error Page Path
	 */
	public static final String ERROR_404_PAGE_PATH = "error/404";
	public static final String ERROR_500_PAGE_PATH = "error/500";
	public static final String ERROR_DEFAULT_PAGE_PATH = "error/default";

	/**
	 * Code value
	 */
	public static final Integer BOARD_ALL = null;
	public static final Integer BOARD_COMMUNITY = 5;
	public static final Integer BOARD_CODING = 6;
	public static final String MAN = "7";
	public static final String WOMAN = "8";

	/**
	 * category
	 */
	public static final String ALL = "전체";
	public static final String COMMUNITY = "커뮤니티";
	public static final String CODING = "코딩";


	/**
	 * URL Mapping
	 */
	public static final String HOME = "/";

	/**
	 * Security
	 */
	public static final String SECURITY_ERROR_MSG = "아이디와 비밀번호를 확인해주세요.";
	public static final String SECURITY_SIGN_IN_URL ="/sign-in";
	public static final String SECURITY_SIGNIN_SUCCESS_URL = "/";
	public static final String SECURITY_SIGNIN_FAILURE_URL = "/sign-in";
	public static final String SECURITY_SIGNOUT_URL = "/sign-out";
	public static final String SECURITY_SIGNOUT_SUCCESS_URL = "/";

	/**
	 * Board
	 */
	public static final Integer PAGING_SIZE = 7;
}
