package com.liuhuangming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.service.StrategyService;

/**
 * 游记管理控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("strategy")
public class StrategyController {

	@Autowired
	private StrategyService strategyService;
	
	/**
	 * 添加我的游记
	 * @param strategy 实体
	 * @param session
	 * @return
	 */
	@RequestMapping("addStrategy")
	public Message addStrategy(Strategy strategy,HttpSession session) {
		System.out.println("addStrategy================>");
		return strategyService.addStrategy(strategy,session);
	}
	/**
	 * 获取当前登录用户的游记信息
	 * @param session
	 * @return
	 */
	@RequestMapping("getMyStrategy")
	public List<Strategy> getMyStrategy(HttpSession session) {
		System.out.println("getMyStrategy================>");
		return strategyService.getMyStrategy(session);
	}
	/**
	 * 根据游记ID删除游记
	 * @param strategyId 游记ID
	 * @return
	 */
	@RequestMapping("deleteStrategy")
	public Message deleteStrategy(String strategyId) {
		System.out.println("deleteStrategy================>");
		return strategyService.deleteStrategy(strategyId);
	}
}
