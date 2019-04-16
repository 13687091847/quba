package com.liuhuangming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuhuangming.entity.Strategy;
import com.liuhuangming.service.StrategyService;
import com.liuhuangming.service.UserInfoService;

/**
 * 处理用户分享内容
 * @author LHM
 *
 */
@Controller
public class ShareController {
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("share")
	public String share(String strategyId,Model model) {
		System.err.println(strategyId);
		Strategy strategy = strategyService.selectByStrategyId(strategyId);
		if(strategy != null) {
			strategy.setUserInfo(userInfoService.getUserInfoByAccount(strategy.getAccount()));
			strategy.setContent(strategy.getContent().replaceAll("\n","<br>"));
			model.addAttribute("strategy",strategy);
		}
		return "share";
	}
}
