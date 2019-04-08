package com.liuhuangming.mapper;

import com.liuhuangming.entity.Message;
import com.liuhuangming.entity.MessageExample;
import org.springframework.stereotype.Repository;

/**
 * MessageDAO继承基类
 */
@Repository
public interface MessageDAO extends MyBatisBaseDao<Message, Integer, MessageExample> {
}