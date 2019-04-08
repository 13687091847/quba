package com.liuhuangming.service;

import javax.servlet.http.HttpSession;

import com.liuhuangming.bean.Mes;

public interface CommentLikeService {

	/**
	 * 通过评论ID查询该评论有多少点赞
	 * @param commentId
	 * @return
	 */
	long countCommentLikeByCommentId(int commentId);
	/**
	 * 根据评论ID判断当前登录用户是否点赞该评论
	 * @param session
	 * @param commentId
	 * @return 1:说明该用户点赞了该评论,2:用户已经取消收藏,3:该用户不存在点赞表，没有对该游记进行过点赞操作
	 *  -1:未登录
	 */
	int checkCommentLike(HttpSession session,int commentId);
	/**
	 * 给评论添加点赞
	 * @param session
	 * @return
	 */
	Mes addCommentLike(HttpSession session,int commentId);
}
