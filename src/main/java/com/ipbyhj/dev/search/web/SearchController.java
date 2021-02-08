package com.ipbyhj.dev.search.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SearchController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView getSearchResult(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

		/**
		 * 검색 키워드 코드 값으로 url 반환
		 */

		/**
		 * 검색 총 결과 내용(제목, 내용) 반환
		 */
		
		modelAndView.setViewName("dev/search/result");
		return modelAndView;
	}
}
