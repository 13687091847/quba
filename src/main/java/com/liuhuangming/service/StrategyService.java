package com.liuhuangming.service;

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
}
