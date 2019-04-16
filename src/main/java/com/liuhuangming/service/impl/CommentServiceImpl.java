package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuhuangming.bean.DataGrid;
import com.liuhuangming.bean.Mes;
import com.liuhuangming.bean.PageBean;
import com.liuhuangming.entity.Comment;
import com.liuhuangming.entity.CommentExample;
import com.liuhuangming.entity.Strategy;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.CommentExample.Criteria;
import com.liuhuangming.entity.Message;
import com.liuhuangming.mapper.CommentDAO;
import com.liuhuangming.service.CommentLikeService;
import com.liuhuangming.service.CommentService;
import com.liuhuangming.service.FollowService;
import com.liuhuangming.service.MessageService;
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
	@Autowired
	FollowService followService;
	@Autowired
	MessageService messageService;

	/**
	 * 通过游记Id获取所有的评论信息
	 */
	@Override
	public PageInfo<Comment> findByStrategyId(String strategyId, HttpSession session, Integer pageNum,
			Integer pageSize) {
		String account = (String) session.getAttribute("account");
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("comment_date desc");
		CommentExample commentExample = new CommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andStrategyIdEqualTo(strategyId);
		criteria.andStatusEqualTo(true);
		List<Comment> comments = commentDAO.selectByExample(commentExample);
		if (comments != null) {
			for (Comment comment : comments) {
				if (commentLikeService.checkCommentLike(session, comment.getCommentId()) == 1) {
					// 说明已点赞
					comment.setLike(true);
				} else {
					comment.setLike(false);
				}
				if (account.equals(comment.getAccount())) {
					// 说明该评论为当前登录用户所写
					comment.setOwner(true);
				} else {
					comment.setOwner(false);
				}
				// 查询当前登录用户是否关注该评论的发布者
				Mes mes = followService.checkIsExist(account, comment.getAccount());
				if (mes.getCode() != 0) {
					// 说明存在于关注表中
					if (mes.getMessage().equals("已关注")) {
						// 说明状态是已关注
						comment.setFollow(true);
					} else {
						// 说明状态是取消关注
						comment.setFollow(false);
					}
				} else {
					comment.setFollow(false);
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
	public PageInfo<Comment> findMyComments(HttpSession session, int pageNum, int pageSize) {
		String account = (String) session.getAttribute("account");
		return findCommentByAccount(account, pageNum, pageSize);
	}

	/**
	 * 添加评论
	 */
	@Override
	public int addComment(HttpSession session, Comment comment) {
		String account = (String) session.getAttribute("account");
		Message message = new Message();
		// 获取相应的评论的游记消息
		Strategy strategy = strategyService.selectByStrategyId(comment.getStrategyId());
		if (account != null) {
			comment.setAccount(account);
			comment.setStatus(true);
			// 发送系统消息给游记作
			if (!account.equals(strategy.getAccount())) {
				//自己评论自己不需要提醒
				message.setSendTo(strategy.getAccount());
				message.setSendFrom("系统管理员");
				message.setContent(account + "刚刚评论了你的游记--“" + strategy.getTitle() + "”--" + comment.getContent());
				message.setTitle("游记评论提醒");
				message.setStatus((byte) 0);
				// 调用消息发送函数
				messageService.sendMessage(message);
			}
			// 添加进数据库
			return commentDAO.insertSelective(comment);
		}
		return 0;
	}

	/**
	 * 根据评论ID删除评论
	 */
	@Override
	public int deleteComment(int commentId) {
		// 更新评论表中该评论的状态
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
		if (comment.getCommentId() != null) {
			return commentDAO.updateByPrimaryKeySelective(comment);
		}
		return 0;
	}

	/**
	 * 通过评论id查找对应评论
	 */
	@Override
	public Comment findByCommentId(HttpSession session, int commentId) {
		String account = (String) session.getAttribute("account");
		Comment comment = commentDAO.selectByPrimaryKey(commentId);
		// 查找对应的评论人的用户信息
		comment.setUserInfo(userInfoService.getUserInfoByAccount(comment.getAccount()));
		// 查询当前登录用户是否关注该评论的发布者
		Mes mes = followService.checkIsExist(account, comment.getAccount());
		if (mes.getCode() != 0) {
			// 说明存在于关注表中
			if (mes.getMessage().equals("已关注")) {
				// 说明状态是已关注
				comment.setFollow(true);
			} else {
				// 说明状态是取消关注
				comment.setFollow(false);
			}
		} else {
			comment.setFollow(false);
		}
		return comment;
	}

	/**
	 * 根据用户账号获取用户的所有评论信息
	 */
	@Override
	public PageInfo<Comment> findCommentByAccount(String account, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("comment_date desc");
		List<Comment> comments = new ArrayList<>();
		if (account != null) {
			CommentExample commentExample = new CommentExample();
			Criteria criteria = commentExample.createCriteria();
			criteria.andAccountEqualTo(account);
			criteria.andStatusEqualTo(true);
			comments = commentDAO.selectByExample(commentExample);
			UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
			if (comments != null) {
				for (Comment comment : comments) {
					// 获取该评论对用的游记信息
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
