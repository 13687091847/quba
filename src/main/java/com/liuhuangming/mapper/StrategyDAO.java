package com.liuhuangming.mapper;

import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.StrategyExample;
import org.springframework.stereotype.Repository;

/**
 * StrategyDAO继承基类
 */
@Repository
public interface StrategyDAO extends MyBatisBaseDao<Strategy, Integer, StrategyExample> {
}