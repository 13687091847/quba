package com.liuhuangming.controller;

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
	
	@RequestMapping("addStrategy")
	public Message addStrategy(Strategy strategy,HttpSession session) {
		return strategyService.addStrategy(strategy,session);
	}
}
