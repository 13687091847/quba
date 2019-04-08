package com.liuhuangming.service;

import javax.servlet.http.HttpSession;
import com.github.pagehelper.PageInfo;
import com.liuhuangming.entity.Comment;

public interface CommentService {

	/**
	 * 通过游记Id获取所有的评论信息
	 * @param strategyId
	 * @param session
	 * @return
	 */
	PageInfo<Comment> findByStrategyId(String strategyId,HttpSession session,Integer pageNum,Integer pageSize);
	/**
	 * 获取当前登录用户的所有评论
	 * @param session
	 * @return
	 */
	PageInfo<Comment> findMyComments(HttpSession session,int pageNum, int pageSize);
	/**
	 * 根据用户账号获取用户所有的评论
	 * @param account
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<Comment> findCommentByAccount(String account,int pageNum, int pageSize);
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
	/**
	 * 更新评论信息
	 * @param comment
	 * @return
	 */
	int updateComment(Comment comment);
	/**
	 * 通过评论id查找对应评论
	 * @param commentId
	 * @return
	 */
	Comment findByCommentId(int commentId);
}
