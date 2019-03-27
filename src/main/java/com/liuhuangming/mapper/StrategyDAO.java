package com.liuhuangming.mapper;

import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.StrategyExample;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * StrategyDAO继承基类
 */
@Repository
public interface StrategyDAO extends MyBatisBaseDao<Strategy, String, StrategyExample> {
	
	/**
	 * 用来查询带有（游记内容）长文字
	 * @param strategyExample
	 * @return
	 */
	List<Strategy> selectByExampleWithBLOBs(StrategyExample strategyExample);
}