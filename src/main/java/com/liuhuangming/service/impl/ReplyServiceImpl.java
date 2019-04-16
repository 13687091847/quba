package com.liuhuangming.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.entity.Comment;
import com.liuhuangming.entity.Message;
import com.liuhuangming.entity.Reply;
import com.liuhuangming.entity.ReplyExample;
import com.liuhuangming.entity.ReplyExample.Criteria;
import com.liuhuangming.mapper.ReplyDAO;
import com.liuhuangming.service.CommentService;
import com.liuhuangming.service.MessageService;
import com.liuhuangming.service.ReplyService;
import com.liuhuangming.service.UserInfoService;
import com.sun.tools.doclets.internal.toolkit.MemberSummaryWriter;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyDAO replyDAO;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	CommentService commentService;
	@Autowired
	MessageService messageService;

	/**
	 * 添加回复
	 */
	@Override
	public int addReply(HttpSession session, Reply reply) {
		String account = (String) session.getAttribute("account");
		Message message = new Message();
		reply.setAccount(account);
		// 添加对应评论的回复数
		Comment comment = commentService.findByCommentId(session, reply.getCommentid());
		comment.setReplyNum(comment.getReplyNum() + 1);
		commentService.updateComment(comment);
		// 发送系统消息给游记作
		if(!account.equals(comment.getAccount())) {
			//自己回复自己不需要提醒
			message.setSendTo(comment.getAccount());
			message.setSendFrom("系统管理员");
			message.setContent(account + "刚刚回复了你的评论--" + reply.getContent() + "//@" + comment.getAccount() +" "+comment.getContent());
			message.setTitle("评论回复提醒");
			message.setStatus((byte) 0);
			// 调用消息发送函数
			messageService.sendMessage(message);
		}
		
		return replyDAO.insertSelective(reply);
	}

	/**
	 * 查找回复评论
	 */
	@Override
	public List<Reply> findByCommentId(HttpSession session, int commentId) {
		String account = (String) session.getAttribute("account");
		ReplyExample replyExample = new ReplyExample();
		Criteria criteria = replyExample.createCriteria();
		criteria.andCommentidEqualTo(commentId);
		replyExample.setOrderByClause("reply_time desc");
		List<Reply> replies = replyDAO.selectByExample(replyExample);
		for (Reply reply : replies) {
			reply.setUserInfo(userInfoService.getUserInfoByAccount(reply.getAccount()));
			// 查看该回复是否为当前登录用户所写
			if (account.equals(reply.getAccount())) {
				reply.setOwner(true);
			} else {
				reply.setOwner(false);
			}
		}
		return replies;
	}

	/**
	 * 通过id删除回复
	 */
	@Override
	public int deleteReplyById(HttpSession session, int id) {
		// 减少评论表中的回复数
		Reply reply = replyDAO.selectByPrimaryKey(id);
		Comment comment = commentService.findByCommentId(session, reply.getCommentid());
		comment.setReplyNum(comment.getReplyNum() - 1);
		commentService.updateComment(comment);
		return replyDAO.deleteByPrimaryKey(id);
	}

}
