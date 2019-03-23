package com.liuhuangming.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.IDUtils;
import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.StrategyExample;
import com.liuhuangming.entity.StrategyExample.Criteria;
import com.liuhuangming.mapper.StrategyDAO;
import com.liuhuangming.service.StrategyService;

/**
 * 游记管理接口实现类
 * @author Administrator
 *
 */
@Service
public class StrategyServiceImpl implements StrategyService {
	@Autowired
	StrategyDAO strategyDAO;

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

}
