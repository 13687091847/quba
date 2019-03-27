package com.liuhuangming.service;


public interface CommentLikeService {

	/**
	 * 通过评论ID查询该评论有多少点赞
	 * @param commentId
	 * @return
	 */
	long countCommentLikeByCommentId(int commentId);
}
