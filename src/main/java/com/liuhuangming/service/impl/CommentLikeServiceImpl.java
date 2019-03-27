package com.liuhuangming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuhuangming.entity.CommentLikeExample;
import com.liuhuangming.entity.CommentLikeExample.Criteria;
import com.liuhuangming.mapper.CommentLikeDAO;
import com.liuhuangming.service.CommentLikeService;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

	@Autowired
	CommentLikeDAO commentLikeDAO;
	
	/**
	 * 通过评论ID查询该评论点赞数量
	 */
	@Override
	public long countCommentLikeByCommentId(int commentId) {
		// TODO Auto-generated method stub
		CommentLikeExample commentLikeExample = new CommentLikeExample();
		Criteria criteria = commentLikeExample.createCriteria();
		criteria.andCommentIdEqualTo(commentId);
		return commentLikeDAO.countByExample(commentLikeExample);
	}

}
