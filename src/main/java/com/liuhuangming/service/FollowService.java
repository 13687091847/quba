package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.entity.Follow;


public interface FollowService {

	/**
	 * 根据用户名获取用户关注的人
	 * @param account
	 * @return
	 */
	List<Follow> getAllByAccount(String account);
	/**
	 * 根据用户名获取用户的粉丝
	 * @param account
	 * @return
	 */
	List<Follow> getAllByFollowedUser(String account);
	/**
	 * 当前登录用户关注其他用户
	 * @param followedUser 要关注的用户账号
	 * @return 
	 */
	long followOther(String followedUser,HttpSession session);
	/**
	 * 查询这前前面参数指代的用户之前是否关注过后面的用户（也就是查询第一个用户关注第二个用户是否存在于关注表）
	 * @param account
	 * @param followedUser
	 * @return
	 */
	int checkIsExist(String account,String followedUser);
}
