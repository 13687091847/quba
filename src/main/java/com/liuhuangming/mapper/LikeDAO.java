package com.liuhuangming.mapper;

import com.liuhuangming.entity.Like;
import com.liuhuangming.entity.LikeExample;
import org.springframework.stereotype.Repository;

/**
 * LikeDAO继承基类
 */
@Repository
public interface LikeDAO extends MyBatisBaseDao<Like, Integer, LikeExample> {
}