package com.liuhuangming.service;

import java.util.List;

import com.liuhuangming.entity.Follow;


public interface FollowService {

	/**
	 * 获取当前登录对象的所关注的用户
	 * @param account
	 * @return
	 */
	List<Follow> getAll(String account);
}
