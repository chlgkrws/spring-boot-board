package com.ipbyhj.dev.error;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipbyhj.dev.common.Globals;

@Controller
public class ExceptionHadlingController implements ErrorController {

	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

		if (status != null) { // HttpStatus와 비교해 페이지 분기를 나누기 위한 변수
			int statusCode = Integer.valueOf(status.toString()); // 로그로 상태값을 기록 및 출력
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				// 에러 페이지에 표시할 정보
				model.addAttribute("code", status.toString());
				model.addAttribute("msg", httpStatus.getReasonPhrase());
				model.addAttribute("timestamp", new Date());
				return Globals.ERROR_404_PAGE_PATH;
			}
			// 500 error
			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { 	// 서버에 대한 에러이기 때문에 사용자에게 정보를 제공하지 않는다.
				return Globals.ERROR_500_PAGE_PATH;
			}
		}

		return Globals.ERROR_DEFAULT_PAGE_PATH;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
