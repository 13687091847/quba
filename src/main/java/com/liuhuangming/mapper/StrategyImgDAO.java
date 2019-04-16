package com.liuhuangming.mapper;

import com.liuhuangming.entity.StrategyImg;
import com.liuhuangming.entity.StrategyImgExample;
import org.springframework.stereotype.Repository;

/**
 * StrategyImgDAO继承基类
 */
@Repository
public interface StrategyImgDAO extends MyBatisBaseDao<StrategyImg, Integer, StrategyImgExample> {
}