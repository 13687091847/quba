package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.entity.Comment;

public interface CommentService {

	/**
	 * 通过游记Id获取所有的评论信息
	 * @param strategyId
	 * @param session
	 * @return
	 */
	List<Comment> findByStrategyId(String strategyId);
	/**
	 * 通过用户账号获取当前登录用户的所有评论
	 * @param session
	 * @return
	 */
	List<Comment> findByAccount(HttpSession session);
	/**
	 * 添加评论
	 * @param session
	 * @param comment
	 * @return
	 */
	int addComment(HttpSession session,Comment comment);
	/**
	 * 根据评论Id删除评论
	 * @param commentId
	 * @return
	 */
	int deleteComment(int commentId);
}
