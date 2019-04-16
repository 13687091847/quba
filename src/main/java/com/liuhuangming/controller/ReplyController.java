package com.liuhuangming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuhuangming.entity.Reply;
import com.liuhuangming.service.ReplyService;

@RestController
@RequestMapping("reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	/**
	 * 添加回复
	 * @param session
	 * @param reply
	 * @return
	 */
	@RequestMapping("addReply")
	public int addReply(HttpSession session,Reply reply) {
		System.out.println("addReply======================>");
		return replyService.addReply(session, reply);
	}
	/**
	 * 获取所有回复内容
	 * @param commentId
	 * @return
	 */
	@RequestMapping("findByCommentId")
	public List<Reply> findByCommentId(HttpSession session,int commentId) {
		System.out.println("findByCommentId======================>");
		return replyService.findByCommentId(session,commentId);
	}
	/**
	 * 删除回复
	 * @param replyId
	 * @return
	 */
	@RequestMapping("deleteReply")
	public int deleteReply(HttpSession session,int replyId) {
		System.out.println("deleteReply======================>");
		return replyService.deleteReplyById(session,replyId);
	}
}
