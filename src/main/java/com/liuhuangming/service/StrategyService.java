package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.liuhuangming.bean.Mes;
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
	Mes addStrategy(Strategy strategy,HttpSession session);
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
	Mes deleteStrategy(String strategyId);
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
	/**
	 * 统计对应用户发表的游记数量
	 * @param account
	 * @return
	 */
	long countByAccount(String account);
	/**
	 * 通过用户账号获取其对应的游记
	 * @param account
	 * @return
	 */
	List<Strategy> getStrategyByAccount(String account);
	/**
	 * 获取所有的游记
	 * @return
	 */
	PageInfo<Strategy> getAll(Integer pageNum,Integer pageSize);
	/**
	 * 获取管理员推荐的游记
	 * @return
	 */
	List<Strategy> getRecommendStrategy();
	/**
	 * 根据标题模糊查询游记
	 * @param title
	 * @return
	 */
	List<Strategy> getStrategyByTitle(String title);
	/**
	 * 增加浏览次数
	 * @param strategyId
	 * @return
	 */
	int addBrowseVolume(String strategyId);
} 
