package com.liuhuangming.mapper;

import com.liuhuangming.entity.CommentLike;
import com.liuhuangming.entity.CommentLikeExample;
import org.springframework.stereotype.Repository;

/**
 * CommentLikeDAO继承基类
 */
@Repository
public interface CommentLikeDAO extends MyBatisBaseDao<CommentLike, Integer, CommentLikeExample> {
}