package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuhuangming.bean.IDUtils;
import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.StrategyExample;
import com.liuhuangming.entity.StrategyExample.Criteria;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.mapper.StrategyDAO;
import com.liuhuangming.service.CollectService;
import com.liuhuangming.service.LikeService;
import com.liuhuangming.service.StrategyService;
import com.liuhuangming.service.UserInfoService;
import com.sun.tools.javac.resources.compiler;

/**
 * 游记管理接口实现类
 * @author Administrator
 *
 */
@Service
public class StrategyServiceImpl implements StrategyService {
	@Autowired
	StrategyDAO strategyDAO;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	LikeService likeService;
	@Autowired
	CollectService collectService;
	/**
	 * 用户发表游记
	 */
	@Override
	public Message addStrategy(Strategy strategy, HttpSession session) {
		// TODO Auto-generated method stub
		Message message = new Message();
		String account = (String)session.getAttribute("account");
		if(account != null) {
			strategy.setAccount(account);
		}
		strategy.setStatus(true);
		strategy.setStrategyId(IDUtils.createID());
		strategy.setCollectNum(0);
		strategy.setLikeNum(0);
		strategy.setCommentNum(0);
		//首先查看是否为重复发送
		if(checkStrategy(strategy, session)) {
			message.setCode(500);
			message.setMessage("请勿重复发送");
			return message;
		}
		int resultCode = strategyDAO.insertSelective(strategy);
		if(resultCode > 0) {
			message.setCode(200);
			return message;
		}
		message.setCode(500);
		return message;
	}
	/**
	 * 检查游记是否重复发送
	 */
	@Override
	public boolean checkStrategy(Strategy strategy, HttpSession session) {
		// TODO Auto-generated method stub
		String account = (String)session.getAttribute("account");
		StrategyExample strategyExample = new StrategyExample();
		Criteria criteria = strategyExample.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andTitleEqualTo(strategy.getTitle());
		List<Strategy> listStrategy = strategyDAO.selectByExample(strategyExample);
		if(listStrategy.size() > 0) {
			for(Strategy item:listStrategy) {
				//时间不超过15分钟，则提示不能重复发送
				if( (item.getUploadDate().getTime() - (new Date()).getTime()) < 15 ) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 获取当前登录用户的所有游记
	 */
	@Override
	public List<Strategy> getMyStrategy(HttpSession session) {
		// TODO Auto-generated method stub
		String account = (String)session.getAttribute("account");
		List<Strategy> sList = new ArrayList<>();
		StrategyExample strategyExample = new StrategyExample();
		Criteria criteria = strategyExample.createCriteria();
		if(account != null) {
			criteria.andAccountEqualTo(account);
			//未删除状态
			criteria.andStatusEqualTo(true);
			sList = strategyDAO.selectByExampleWithBLOBs(strategyExample);
			for(Strategy strategy : sList) {
//				//获取点赞数
//				long likeNum = likeService.countByStrategyId(strategy.getStrategyId());
//				//获取收藏数
//				long collectNum = collectService.countByStrategyId(strategy.getStrategyId());
//				strategy.setLikeNum((int)(likeNum));
//				strategy.setCollectNum((int)(collectNum));
				//根据用户账号查询用户信息
				UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
				strategy.setUserInfo(userInfo);
			}
			return sList;
		}
		return null;
	}
	/**
	 * 根据游记ID删除游记
	 */
	@Override
	public Message deleteStrategy(String strategyId) {
		// TODO Auto-generated method stub
		Message message = new Message();
		Strategy strategy = new Strategy();
		strategy.setStrategyId(strategyId);
		//设定该游记为删除状态
		strategy.setStatus(false);
		//调用更新DAO方法
		int resultColde = strategyDAO.updateByPrimaryKeySelective(strategy);
		if(resultColde > 0) {
			message.setCode(200);
		}else {
			message.setCode(500);
		}
		return message;
	}
	/**
	 * 更新游记信息
	 */
	@Override
	public int updateStrategy(Strategy strategy) {
		// TODO Auto-generated method stub
		if(strategy.getStrategyId() != null) {
			return strategyDAO.updateByPrimaryKey(strategy);
		}
		return 0;
	}
	/**
	 * 根据游记ID获取游记信息
	 */
	@Override
	public Strategy selectByStrategyId(String strategyId) {
		// TODO Auto-generated method stub
		return strategyDAO.selectByPrimaryKey(strategyId);
	}

}
