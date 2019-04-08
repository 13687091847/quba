package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuhuangming.bean.DataGrid;
import com.liuhuangming.bean.PageBean;
import com.liuhuangming.entity.Comment;
import com.liuhuangming.entity.CommentExample;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.CommentExample.Criteria;
import com.liuhuangming.mapper.CommentDAO;
import com.liuhuangming.service.CommentLikeService;
import com.liuhuangming.service.CommentService;
import com.liuhuangming.service.StrategyService;
import com.liuhuangming.service.UserInfoService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	CommentLikeService commentLikeService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	StrategyService strategyService;

	/**
	 * 通过游记Id获取所有的评论信息
	 */
	@Override
	public PageInfo<Comment> findByStrategyId(String strategyId,HttpSession session,Integer pageNum,Integer pageSize) {
		String account = (String)session.getAttribute("account");
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("comment_date desc");
		CommentExample commentExample = new CommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andStrategyIdEqualTo(strategyId);
		criteria.andStatusEqualTo(true);
		List<Comment> comments = commentDAO.selectByExample(commentExample);
		if(comments != null) {
			for(Comment comment : comments) {
				if( commentLikeService.checkCommentLike(session, comment.getCommentId()) == 1 ) {
					//说明已点赞
					comment.setLike(true);
				}else {
					comment.setLike(false);
				}
				if(account.equals(comment.getAccount())) {
					//说明该评论为当前登录用户所写
					comment.setOwner(true);
				}else {
					comment.setOwner(false);
				}
				UserInfo userInfo = userInfoService.getUserInfoByAccount(comment.getAccount());
				comment.setUserInfo(userInfo);
			}
		}
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(comments);
		return pageInfo;
	}
	/**
	 * 通过用户账号获取当前登录用户的所有评论
	 */
	@Override
	public PageInfo<Comment> findMyComments(HttpSession session,int pageNum, int pageSize) {
		String account = (String)session.getAttribute("account");
		return findCommentByAccount(account,pageNum,pageSize);
	}
	/**
	 * 添加评论
	 */
	@Override
	public int addComment(HttpSession session, Comment comment) {
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
		//更新评论表中该评论的状态
		Comment comment = new Comment();
		comment.setStatus(false);
		comment.setCommentId(commentId);
		return commentDAO.updateByPrimaryKeySelective(comment);
	}
	
	/**
	 * 更新评论信息
	 */
	@Override
	public int updateComment(Comment comment) {
		if(comment.getCommentId() != null) {
			return commentDAO.updateByPrimaryKeySelective(comment);
		}
		return 0;
	}
	/**
	 * 通过评论id查找对应评论
	 */
	@Override
	public Comment findByCommentId(int commentId) {
		return commentDAO.selectByPrimaryKey(commentId);
	}
	/**
	 * 根据用户账号获取用户的所有评论信息
	 */
	@Override
	public PageInfo<Comment> findCommentByAccount(String account, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("comment_date desc");
		List<Comment> comments = new ArrayList<>();
		if(account != null) {
			CommentExample commentExample = new CommentExample();
			Criteria criteria = commentExample.createCriteria();
			criteria.andAccountEqualTo(account);
			criteria.andStatusEqualTo(true);
			comments = commentDAO.selectByExample(commentExample);
			UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
			if(comments != null) {
				for(Comment comment : comments) {
					//获取该评论对用的游记信息
					Strategy strategy = strategyService.selectByStrategyId(comment.getStrategyId());
					comment.setStrategy(strategy);
					comment.setOwner(true);
					comment.setUserInfo(userInfo);
				}
			}
			PageInfo<Comment> pageInfo = new PageInfo<Comment>(comments);
			return pageInfo;
		}
		return null;
	}
}
