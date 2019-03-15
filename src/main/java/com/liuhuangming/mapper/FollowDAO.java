package com.liuhuangming.mapper;

import com.liuhuangming.entity.Follow;
import com.liuhuangming.entity.FollowExample;
import com.sun.tools.javac.util.List;

import org.springframework.stereotype.Repository;

/**
 * FollowDAO继承基类
 */
@Repository
public interface FollowDAO extends MyBatisBaseDao<Follow, Integer, FollowExample> {
	
	//通过account查找当前用户的关注人
	List<Follow> selectByAccount(String account);
}