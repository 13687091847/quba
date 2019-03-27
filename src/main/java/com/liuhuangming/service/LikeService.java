package com.liuhuangming.service;

import javax.servlet.http.HttpSession;

import com.liuhuangming.bean.Message;

public interface LikeService {

	/**
	 * 根据strategyId查询当前登录用户是否点赞该游记
	 * @param strategyId
	 * @param session
	 * @return 返回1：说明用户已经点赞该游记；返回2：说明用户已经取消点赞；返回3：说明用户没有对该游记进行操作；返回-1：用户未登录
	 */
	int checkLike(String strategyId,HttpSession session);
	/**
	 * 添加点赞信息
	 * @param strategyId
	 * @param session
	 * @return 
	 */
	Message addLike(String strategyId,HttpSession session);
	/**
	 * 根据strategyId统计该游记的点赞数
	 * @param strategyId
	 * @return
	 */
	long countByStrategyId(String strategyId);
}
