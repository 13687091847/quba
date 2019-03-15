package com.liuhuangming.mapper;

import com.liuhuangming.entity.Comment;
import com.liuhuangming.entity.CommentExample;
import org.springframework.stereotype.Repository;

/**
 * CommentDAO继承基类
 */
@Repository
public interface CommentDAO extends MyBatisBaseDao<Comment, Integer, CommentExample> {
}