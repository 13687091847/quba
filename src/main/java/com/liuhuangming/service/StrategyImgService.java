package com.liuhuangming.service;

import java.util.List;

import com.liuhuangming.entity.StrategyImg;

public interface StrategyImgService {

	/**
	 * 添加图片集合至数据库
	 * @param strategyImgs
	 * @return
	 */
	int addStrategyImg(List<StrategyImg> strategyImgs);
	/**
	 * 通过游记ID查找对应的游记附图
	 * @param strategyId
	 * @return
	 */
	List<StrategyImg> findByStrategyId(String strategyId);
}
