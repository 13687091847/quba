package com.liuhuangming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuhuangming.entity.Comment;
import com.liuhuangming.service.CommentService;

/**
 * 评论控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	/**
	 * 通过游记Id获取所有的评论信息
	 * @param strategyId
	 * @return
	 */
	@RequestMapping("findByStrategyId")
	public List<Comment> findByStrategyId(String strategyId){
		System.out.println("findByStrategyId=====================>");
		return commentService.findByStrategyId(strategyId);
	}
	
	/**
	 * 通过游记Id获取所有的评论信息
	 * @param strategyId
	 * @return
	 */
	@RequestMapping("findByAccount")
	public List<Comment> findByAccount(HttpSession session){
		System.out.println("findByAccount=====================>");
		return commentService.findByAccount(session);
	}
	
	/**
	 * 添加评论
	 * @param session
	 * @param comment
	 * @return
	 */
	@RequestMapping("addComment")
	public int addComment(HttpSession session,Comment comment){
		System.out.println("addComment=====================>");
		return commentService.addComment(session,comment);
	}
	/**
	 * 根据评论Id删除评论
	 * @param commentId
	 * @return
	 */
	@RequestMapping("deleteComment")
	public int deleteComment(int commentId){
		System.out.println("deleteComment=====================>");
		return commentService.deleteComment(commentId);
	}
	
}
