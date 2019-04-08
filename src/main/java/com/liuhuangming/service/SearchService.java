package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.entity.Search;

/**
 * 
 * @author LHM
 *
 */
public interface SearchService {

	/**
	 * 添加搜索记录
	 * @param search
	 * @return
	 */
	int addSearch(HttpSession session,String searcContent);
	/**
	 * 通过用户名获取所有的搜索信息
	 * @param account
	 * @return
	 */
	List<Search> getSearchByAccount(HttpSession session);
	/**
	 * 根据用户名清理对应的搜索记录
	 * @return
	 */
	int deleteAll(HttpSession session);
}
