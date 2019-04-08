package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Collection;
import com.liuhuangming.entity.Strategy;

public interface CollectService {

	/**
	 * 根据strategyId查询当前登录用户是否收藏该游记
	 * @param strategyId
	 * @param session
	 * @return 返回1：说明用户已经收藏该游记；返回2：说明用户已经取消收藏；返回3：说明用户没有对该游记进行操作；返回-1：用户未登录
	 */
	int checkCollect(String strategyId,HttpSession session);
	/**
	 * 添加收藏信息到收藏表
	 * @param strategyId
	 * @param session
	 * @return
	 */
	Mes addCollect(String strategyId,HttpSession session);
	/**
	 * 根据strategyId统计该游记的收藏数
	 * @param strategyId
	 * @return
	 */
	long countByStrategyId(String strategyId);
	/**
	 * 获取当前登录用户的收藏信息
	 * @param session
	 * @return
	 */
	List<Strategy> findByAccount(HttpSession session);
	/**
	 * 根据游记ID删除收藏的对应的游记（一般是收藏表中对应的游记被作者删除）
	 * @param strategyId
	 * @return
	 */
	int deleteByStrategyId(String strategyId);
}
