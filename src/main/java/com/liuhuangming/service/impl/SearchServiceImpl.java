package com.liuhuangming.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.entity.Search;
import com.liuhuangming.entity.SearchExample;
import com.liuhuangming.entity.SearchExample.Criteria;
import com.liuhuangming.mapper.SearchDAO;
import com.liuhuangming.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	SearchDAO searchDAO;
	/**
	 * 添加搜索记录
	 */
	@Override
	public int addSearch(HttpSession session,String searchContent) {
		String account = (String)session.getAttribute("account");
		Search search = new Search();
		//首先通过用户账号查找搜索记录
		List<Search> searchs = getSearchByAccount(session);
		if(searchs != null) {
			for (Search search2 : searchs) {
				if(search2.getContent().equals(searchContent)) {
					return 0;
				}
			}
		}
		search.setAccount(account);
		search.setContent(searchContent);
		return searchDAO.insertSelective(search);
		
	}
	/**
	 * 通过用户名获取所有的搜索信息
	 */
	@Override
	public List<Search> getSearchByAccount(HttpSession session) {
		String account = (String)session.getAttribute("account");
		SearchExample searchExample = new SearchExample();
		Criteria criteria = searchExample.createCriteria();
		criteria.andAccountEqualTo(account);
		searchExample.setOrderByClause("search_time desc");
		return searchDAO.selectByExample(searchExample);
	}
	/**
	 * 根据用户名清理对应的搜索记录
	 */
	@Override
	public int deleteAll(HttpSession session) {
		String account = (String)session.getAttribute("account");
		SearchExample searchExample = new SearchExample();
		Criteria criteria = searchExample.createCriteria();
		if(account != null) {
			criteria.andAccountEqualTo(account);
		}
		return searchDAO.deleteByExample(searchExample);
	}

}
