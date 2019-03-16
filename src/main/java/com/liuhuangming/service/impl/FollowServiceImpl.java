package com.liuhuangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.entity.Follow;
import com.liuhuangming.entity.FollowExample;
import com.liuhuangming.entity.FollowExample.Criteria;
import com.liuhuangming.mapper.FollowDAO;
import com.liuhuangming.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDAO followDAO;

	@Override
	public List<Follow> getAll(String account) {
		// TODO Auto-generated method stub
		FollowExample followExample = new FollowExample();
		Criteria getCri = followExample.createCriteria();
		getCri.andAccountEqualTo(account);
		getCri.andStatusEqualTo(true);
		List<Follow> resultList = followDAO.selectByExample(followExample);
		return resultList;
	}
	
	
}
