package com.liuhuangming.mapper;

import com.liuhuangming.entity.Follow;
import com.liuhuangming.entity.FollowExample;
import org.springframework.stereotype.Repository;

/**
 * FollowDAO继承基类
 */
@Repository
public interface FollowDAO extends MyBatisBaseDao<Follow, Integer, FollowExample> {
}