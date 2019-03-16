package com.liuhuangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.entity.Fans;
import com.liuhuangming.entity.FansExample;
import com.liuhuangming.entity.FansExample.Criteria;
import com.liuhuangming.mapper.FansDAO;
import com.liuhuangming.service.FansService;


@Service
public class FansServiceImpl implements FansService {

	@Autowired
	private FansDAO fansDAO;

	@Override
	public List<Fans> getAll(String account) {
		// TODO Auto-generated method stub
		FansExample fansExample = new FansExample();
		Criteria getCri = fansExample.createCriteria();
		getCri.andAccountEqualTo(account);
		getCri.andStatusEqualTo(true);
		List<Fans> resultList = fansDAO.selectByExample(fansExample);
		return resultList;
	}
}
