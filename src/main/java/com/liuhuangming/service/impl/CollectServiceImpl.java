package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Collection;
import com.liuhuangming.entity.CollectionExample;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.CollectionExample.Criteria;
import com.liuhuangming.mapper.CollectionDAO;
import com.liuhuangming.service.CollectService;
import com.liuhuangming.service.StrategyService;

@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	CollectionDAO collectionDAO;
	@Autowired
	StrategyService strategyService;
	/**
	 * 根据strategyId查询当前登录用户是否收藏该游记
	 */
	@Override
	public int checkCollect(String strategyId, HttpSession session) {
		// TODO Auto-generated method stub
		String account = (String)session.getAttribute("account");
		CollectionExample collectionExample = new CollectionExample();
		Criteria criteria = collectionExample.createCriteria();
		if(account != null) {
			criteria.andStrategyIdEqualTo(strategyId);
			List<Collection> collectList  = collectionDAO.selectByExample(collectionExample);
			if(collectList.size() != 0) {
				for(Collection collection : collectList) {
					if(account.equals(collection.getAccount())) {
						//说明用户存在收藏表中
						if(collection.getStatus() == true) {
							//说明用户已经收藏该游记
							return 1;
						}else {
							//说明用户存在，但是已经取消收藏
							return 2;
						}
					}else {
						//用户不存在收藏表中，之前从未对该游记进行任何操作
						return 3;
					}
				}
			}
		}
		return -1;
	}
	/**
	 * 添加/删除 收藏信息
	 */
	@Override
	public Mes addCollect(String strategyId, HttpSession session) {
		// TODO Auto-generated method stub
		Mes message = new Mes();
		Collection collection = new Collection();
		String account = (String)session.getAttribute("account");
		Strategy strategy = strategyService.selectByStrategyId(strategyId);
		int updateCollect = 0;
		int updateStrategy = 0;
		if(account != null) {
			//首先去数据库查询当前登录用户是否收藏该游记
			if( checkCollect(strategyId,session) == 1 ) {
				//已收藏，则对应取消收藏
				CollectionExample collectionExample = new CollectionExample();
				Criteria cancelCri = collectionExample.createCriteria();
				cancelCri.andAccountEqualTo(account);
				cancelCri.andStrategyIdEqualTo(strategyId);
				collection.setStatus(false);
				updateCollect = collectionDAO.updateByExampleSelective(collection, collectionExample);
				//相应的游记表中的点赞数量要减一
				strategy.setStrategyId(strategyId);
				//更新收藏数量
				strategy.setCollectNum(strategy.getCollectNum() - 1);
				updateStrategy = strategyService.updateStrategy(strategy);
				
			}else if( checkCollect(strategyId,session) == 2 ){
				//说明用户已经取消收藏，修改状态为收藏
				collection.setAccount(account);
				collection.setStrategyId(strategyId);
				collection.setStatus(true);
				CollectionExample collectionExample = new CollectionExample();
				Criteria updateCri = collectionExample.createCriteria();
				updateCri.andAccountEqualTo(account);
				updateCri.andStrategyIdEqualTo(strategyId);
				updateCollect = collectionDAO.updateByExampleSelective(collection, collectionExample);
				strategy.setCollectNum(( strategy.getCollectNum()  + 1));
				updateStrategy = strategyService.updateStrategy(strategy);
			}else {
				//说明用户首次收藏
				collection.setAccount(account);
				collection.setStrategyId(strategyId);
				collection.setStatus(true);
				//首先更新收藏表中的信息
				updateCollect = collectionDAO.insert(collection);
				//然后更新游记表中对应游记的收藏数量
				strategy.setCollectNum(( strategy.getCollectNum()  + 1));
				updateStrategy = strategyService.updateStrategy(strategy);
			}
			
			if(updateCollect > 0 && updateStrategy > 0) {
				message.setCode(200);
			}else {
				message.setCode(500);
			}
			
		}
		return message;
	}
	/**
	 * 根据strategyId统计该游记的收藏数
	 */
	@Override
	public long countByStrategyId(String strategyId) {
		// TODO Auto-generated method stub
		CollectionExample collectionExample = new CollectionExample();
		Criteria criteria = collectionExample.createCriteria();
		criteria.andStrategyIdEqualTo(strategyId);
		criteria.andStatusEqualTo(true);
		return collectionDAO.countByExample(collectionExample);
	}
	/**
	 * 获取当前登录用户收藏信息
	 */
	@Override
	public List<Strategy> findByAccount(HttpSession session) {
		// TODO Auto-generated method stub
		String account = (String)session.getAttribute("account");
		List<Strategy> strategies = new ArrayList<>();
		CollectionExample collectionExample = new CollectionExample();
		Criteria criteria = collectionExample.createCriteria();
		if(account != null) {
			criteria.andAccountEqualTo(account);
			criteria.andStatusEqualTo(true);
			List<Collection> collections = collectionDAO.selectByExample(collectionExample);
			for(Collection collection : collections) {
				//获取游记ID
				String strategyId = collection.getStrategyId();
				//将查询到的游记信息填入集合中
				strategies.add(strategyService.selectByStrategyId(strategyId));
			}
		}
		return strategies;
	}
	/**
	 * 根据游记Id删除收藏的游记
	 */
	@Override
	public int deleteByStrategyId(String strategyId) {
		// TODO Auto-generated method stub
		CollectionExample collectionExample = new CollectionExample();
		Criteria criteria = collectionExample.createCriteria();
		criteria.andStrategyIdEqualTo(strategyId);
		return collectionDAO.deleteByExample(collectionExample);
	}

}
