package com.liuhuangming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liuhuangming.entity.Search;
import com.liuhuangming.service.SearchService;

@RestController
@RequestMapping("search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	/**
	 * 添加搜索记录
	 * @param search
	 * @return
	 */
	@RequestMapping("addSearch")
	public int addSearch(HttpSession session,String searchContent) {
		System.out.println("addSearch================>");
		return searchService.addSearch(session,searchContent);
	}
	/**
	 * 删除当前登录用户对应的搜索记录
	 * @param account
	 * @return
	 */
	@RequestMapping("deleteSearch")
	public int deleteSearchByAccount(HttpSession session) {
		System.out.println("deleteAllByAccount================>");
		return searchService.deleteAll(session);
	}
	/**
	 * 获取对应用户的搜索记录
	 * @param account
	 * @return
	 */
	@RequestMapping("getAll")
	public List<Search> getAll(HttpSession session) {
		System.out.println("getAll================>");
		return searchService.getSearchByAccount(session);
	}
}
