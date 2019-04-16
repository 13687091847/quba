package com.liuhuangming.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.CollectionExample;
import com.liuhuangming.entity.Like;
import com.liuhuangming.entity.LikeExample;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.LikeExample.Criteria;
import com.liuhuangming.entity.Message;
import com.liuhuangming.mapper.LikeDAO;
import com.liuhuangming.service.LikeService;
import com.liuhuangming.service.MessageService;
import com.liuhuangming.service.StrategyService;
import com.sun.mail.handlers.message_rfc822;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	LikeDAO likeDAO;
	@Autowired
	StrategyService strategyService;
	@Autowired
	MessageService messageService;
	/**
	 * 根据strategyId查询当前登录用户是否点赞该游记
	 */
	@Override
	public int checkLike(String strategyId, HttpSession session) {
		String account = (String)session.getAttribute("account");
		LikeExample likeExample = new LikeExample();
		Criteria criteria = likeExample.createCriteria();
		if(account != null) {
			criteria.andStrategyIdEqualTo(strategyId);
			List<Like> likeList  = likeDAO.selectByExample(likeExample);
			if(likeList != null) {
				for(Like like : likeList) {
					if(account.equals(like.getAccount())) {
						//说明用户存在点赞表中
						if(like.getStatus() == true) {
							//说明用户已经点赞该游记
							return 1;
						}else {
							//说明用户存在，但是已经取消点赞
							return 2;
						}
					}else {
						//用户不存在点赞表中，之前从未对该游记进行任何操作
						return 3;
					}
				}
			}
		}
		return -1;
	}
	/**
	 * 添加/删除 点赞信息
	 */
	@Override
	public Mes addLike(String strategyId, HttpSession session) {
		Mes mes = new Mes();
		//定义一个消息对象
		Message message = new Message();
		Like like = new Like();
		String account = (String)session.getAttribute("account");
		Strategy strategy = strategyService.selectByStrategyId(strategyId);
		//获取游记作者账号
		String authorAccount = strategy.getAccount();
		int updateLike = 0;
		int updateStrategy = 0;
		if(account != null) {
			//首先去数据库查询当前登录用户是否点赞该游记
			if( checkLike(strategyId,session) == 1 ) {
				//已点赞，则对应取消点赞
				LikeExample likeExample = new LikeExample();
				Criteria cancelCri = likeExample.createCriteria();
				cancelCri.andAccountEqualTo(account);
				cancelCri.andStrategyIdEqualTo(strategyId);
				like.setStatus(false);
				updateLike = likeDAO.updateByExampleSelective(like, likeExample);
				//相应的游记表中的点赞数量要减一
				strategy.setStrategyId(strategyId);
				//更新点赞数量
				strategy.setLikeNum(strategy.getLikeNum() - 1);
				updateStrategy = strategyService.updateStrategy(strategy);
				
			}else if( checkLike(strategyId,session) == 2 ){
				//说明用户已经取消点赞，修改状态为点赞
				like.setAccount(account);
				like.setStrategyId(strategyId);
				like.setStatus(true);
				LikeExample likeExample = new LikeExample();
				Criteria updateCri = likeExample.createCriteria();
				updateCri.andAccountEqualTo(account);
				updateCri.andStrategyIdEqualTo(strategyId);
				updateLike = likeDAO.updateByExampleSelective(like, likeExample);
				strategy.setLikeNum( strategy.getLikeNum()  + 1);
				updateStrategy = strategyService.updateStrategy(strategy);
				//发送系统消息给游记作者
				if(!account.equals(authorAccount)) {
					//自己给自己点赞不用发消息
					message.setSendTo(authorAccount);
					message.setSendFrom("系统管理员");
					message.setContent(account+"刚刚点赞了你的游记--“"+strategy.getTitle()+"”");
					message.setTitle("游记点赞提醒");
					message.setStatus((byte)0);
					//调用消息发送函数
					messageService.sendMessage(message);
				}
			}else {
				//说明用户首次点赞
				like.setAccount(account);
				like.setStrategyId(strategyId);
				like.setStatus(true);
				//首先更新点赞表中的信息
				updateLike = likeDAO.insert(like);
				//然后更新游记表中对应游记的点赞数量
				strategy.setLikeNum( strategy.getLikeNum()  + 1);
				updateStrategy = strategyService.updateStrategy(strategy);
				//发送系统消息给游记作者
				if(!account.equals(authorAccount)) {
					//自己给自己点赞不用发消息
					message.setSendTo(authorAccount);
					message.setSendFrom("系统管理员");
					message.setContent(account+"刚刚点赞了你的游记--“"+strategy.getTitle()+"”");
					message.setTitle("游记点赞提醒");
					message.setStatus((byte)0);
					//调用消息发送函数
					messageService.sendMessage(message);
				}
			}
			
			if(updateLike > 0 && updateStrategy > 0) {
				mes.setCode(200);
			}else {
				mes.setCode(500);
			}
			
		}
		return mes;
	}
	/**
	 * 根据strategyId统计该游记的点赞数
	 */
	@Override
	public long countByStrategyId(String strategyId) {
		// TODO Auto-generated method stub
		LikeExample likeExample = new LikeExample();
		Criteria criteria = likeExample.createCriteria();
		criteria.andStrategyIdEqualTo(strategyId);
		criteria.andStatusEqualTo(true);
		return likeDAO.countByExample(likeExample);
	}

}
