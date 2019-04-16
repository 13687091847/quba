package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.entity.Reply;

public interface ReplyService {

	/**
	 * 添加回复
	 * @param session
	 * @param reply
	 * @return
	 */
	int addReply(HttpSession session,Reply reply);
	/**
	 * 通过评论ID查找回复的评论
	 * @param commentId
	 * @return
	 */
	List<Reply> findByCommentId(HttpSession session,int commentId);
	/**
	 * 通过id删除回复
	 * @param id
	 * @return
	 */
	int deleteReplyById(HttpSession session,int id);
}
