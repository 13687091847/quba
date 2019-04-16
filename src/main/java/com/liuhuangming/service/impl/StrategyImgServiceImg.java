package com.liuhuangming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.entity.StrategyImg;
import com.liuhuangming.entity.StrategyImgExample;
import com.liuhuangming.entity.StrategyImgExample.Criteria;
import com.liuhuangming.mapper.StrategyImgDAO;
import com.liuhuangming.service.StrategyImgService;

@Service
public class StrategyImgServiceImg implements StrategyImgService {

	@Autowired
	StrategyImgDAO strategyImgDAO;
	/**
	 * 添加图片
	 */
	@Override
	public int addStrategyImg(List<StrategyImg> strategyImgs) {
		if(strategyImgs != null) {
			int count = 0;
			for(StrategyImg strategyImg : strategyImgs) {
				count += strategyImgDAO.insertSelective(strategyImg);
			}
			if(count == strategyImgs.size()) {
				return 1;
			}
		}
		return 0;
	}
	/**
	 * 通过游记ID查找对应的游记附图
	 */
	@Override
	public List<StrategyImg> findByStrategyId(String strategyId) {
		System.out.println(strategyId);
		StrategyImgExample strategyImgExample = new StrategyImgExample();
		Criteria criteria = strategyImgExample.createCriteria();
		criteria.andStrategyIdEqualTo(strategyId);
		return strategyImgDAO.selectByExample(strategyImgExample);
	}

}
