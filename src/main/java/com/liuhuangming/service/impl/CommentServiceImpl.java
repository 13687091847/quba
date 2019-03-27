package com.liuhuangming.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.entity.Comment;
import com.liuhuangming.entity.CommentExample;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.CommentExample.Criteria;
import com.liuhuangming.mapper.CommentDAO;
import com.liuhuangming.service.CommentLikeService;
import com.liuhuangming.service.CommentService;
import com.liuhuangming.service.UserInfoService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	CommentLikeService commentLikeService;
	@Autowired
	UserInfoService userInfoService;

	/**
	 * 通过游记Id获取所有的评论信息
	 */
	@Override
	public List<Comment> findByStrategyId(String strategyId) {
		// TODO Auto-generated method stub
		CommentExample commentExample = new CommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andStrategyIdEqualTo(strategyId);
		criteria.andStatusEqualTo(true);
		List<Comment> comments = commentDAO.selectByExample(commentExample);
		if(comments.size() > 0) {
			for(Comment comment : comments) {
				UserInfo userInfo = userInfoService.getUserInfoByAccount(comment.getAccount());
				comment.setUserInfo(userInfo);
			}
		}
		return comments;
	}
	/**
	 * 通过用户账号获取当前登录用户的所有评论
	 */
	@Override
	public List<Comment> findByAccount(HttpSession session) {
		// TODO Auto-generated method stub
		String account = (String)session.getAttribute("account");
		if(account != null) {
			CommentExample commentExample = new CommentExample();
			Criteria criteria = commentExample.createCriteria();
			criteria.andStrategyIdEqualTo(account);
			criteria.andStatusEqualTo(true);
			List<Comment> comments = commentDAO.selectByExample(commentExample);
			UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
			if(comments.size() > 0) {
				for(Comment comment : comments) {
					comment.setUserInfo(userInfo);
				}
			}
			return comments;
		}
		return null;
	}
	/**
	 * 添加评论
	 */
	@Override
	public int addComment(HttpSession session, Comment comment) {
		// TODO Auto-generated method stub
		String account = (String)session.getAttribute("account");
		if(account != null) {
			comment.setAccount(account);
			comment.setStatus(true);
			//添加进数据库
			return commentDAO.insertSelective(comment);
		}
		return 0;
	}
	/**
	 * 根据评论ID删除评论
	 */
	@Override
	public int deleteComment(int commentId) {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		comment.setStatus(false);
		comment.setCommentId(commentId);
		return commentDAO.updateByPrimaryKeySelective(comment);
	}
	

}
