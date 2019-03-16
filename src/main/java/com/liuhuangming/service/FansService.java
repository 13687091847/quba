package com.liuhuangming.service;

import java.util.List;

import com.liuhuangming.entity.Fans;


public interface FansService {

	/**
	 * 获取当前登录对象的粉丝用户
	 * @param account
	 * @return
	 */
	List<Fans> getAll(String account);
}
