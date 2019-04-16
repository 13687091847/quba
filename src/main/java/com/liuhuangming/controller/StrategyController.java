package com.liuhuangming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.liuhuangming.bean.Mes;
import com.liuhuangming.bean.ResultBean;
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
	public Mes addStrategy(@RequestBody ResultBean resultBean,HttpSession session) {
		System.out.println("addStrategy================>");
		return strategyService.addStrategy(resultBean,session);
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
	public Mes deleteStrategy(String strategyId) {
		System.out.println("deleteStrategy================>");
		return strategyService.deleteStrategy(strategyId);
	}
	/**
	 * 通过用户账号获取对应的游记
	 * @param account
	 * @return
	 */
	@RequestMapping("getStrategyByAccount")
	public List<Strategy> getStrategyByAccount(String account) {
		System.out.println("getStrategyByAccount================>");
		return strategyService.getStrategyByAccount(account);
	}
	/**
	 * 通过游记id获取对应的游记
	 * @param strategyId
	 * @return
	 */
	@RequestMapping("getStrategyByStrategyId")
	public Strategy getStrategyByStrategyId(String strategyId) {
		System.out.println("getStrategyByStrategyId================>");
		return strategyService.selectByStrategyId(strategyId);
	}
	/**
	 * 获取所有的游记
	 * @return
	 */
	@RequestMapping("getAll")
	public PageInfo<Strategy> getAll(@RequestParam(value="pageNum",defaultValue="1",required=false)Integer pageNum, 
			@RequestParam(value="pageSize",defaultValue="5",required=false)Integer pageSize) {
		System.out.println("getAll================>");
		return strategyService.getAll(pageNum,pageSize);
	}
	/**
	 * 获取管理员推荐的游记
	 */
	@RequestMapping("getRecommendStrategy")
	public List<Strategy> getRecommendStrategy() {
		System.out.println("getRecommendStrategy================>");
		return strategyService.getRecommendStrategy();
	}
	/**
	 * 通过标题模糊查询游记
	 */
	@RequestMapping("getStrategyByTitle")
	public List<Strategy> getStrategyByTitle(String title) {
		System.out.println("getStrategyByTitle================>");
		return strategyService.getStrategyByTitle(title);
	}
	/**
	 * 添加浏览次数
	 * @param strategyId
	 * @return
	 */
	@RequestMapping("addBrowseVolume")
	public int addBrowseVolume(String strategyId) {
		System.out.println("addBrowseVolume================>");
		return strategyService.addBrowseVolume(strategyId);
	}
}
