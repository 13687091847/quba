package com.liuhuangming.mapper;

import com.liuhuangming.entity.Reply;
import com.liuhuangming.entity.ReplyExample;
import org.springframework.stereotype.Repository;

/**
 * ReplyDAO继承基类
 */
@Repository
public interface ReplyDAO extends MyBatisBaseDao<Reply, Integer, ReplyExample> {
}