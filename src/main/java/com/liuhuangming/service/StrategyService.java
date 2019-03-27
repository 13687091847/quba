package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.Strategy;

/**
 * 游记管理接口
 * @author Administrator
 *
 */
public interface StrategyService {
	
	/**
	 * 用户发表游记	
	 * @param strategy
	 * @param session
	 * @return
	 */
	Message addStrategy(Strategy strategy,HttpSession session);
	/**
	 * 查看用户该游记是否已经发送
	 * @param strategy
	 * @param session
	 * @return
	 */
	boolean checkStrategy(Strategy strategy,HttpSession session);
	/**
	 * 获取当前登录用户的所有游记
	 * @param session
	 * @return
	 */
	List<Strategy> getMyStrategy(HttpSession session);
	/**
	 * 根据游记ID删除游记
	 * @param strategyId
	 * @return
	 */
	Message deleteStrategy(String strategyId);
	/**
	 * 更新游记信息
	 * @param strategy
	 * @return
	 */
	int updateStrategy(Strategy strategy);
	/**
	 * 根据游记ID获取游记信息
	 * @param strtegyId
	 * @return
	 */
	Strategy selectByStrategyId(String strategyId);
} 
