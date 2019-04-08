package com.liuhuangming.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import com.liuhuangming.bean.Mes;

import com.liuhuangming.entity.Comment;
import com.liuhuangming.service.CommentLikeService;
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
	@Autowired
	private CommentLikeService commentLikeService;
	/**
	 * 通过游记Id获取所有的评论信息
	 * @param strategyId
	 * @return
	 */
	@RequestMapping("findByStrategyId")
	public PageInfo<Comment> findByStrategyId(HttpSession session,String strategyId,@RequestParam(value="pageNum",defaultValue="1",required=false)Integer pageNum, 
			@RequestParam(value="pageSize",defaultValue="7",required=false)Integer pageSize){
		System.out.println("findByStrategyId=====================>");
		return commentService.findByStrategyId(strategyId,session,pageNum,pageSize);
	}
	
	/**
	 * 获取当前登录用户所有的评论信息
	 * @param session 
	 * @param pageNum 前台传来的页码
	 * @param pageSize 每页显示的数量
	 * @return
	 */
	@RequestMapping("findMyComments")
	public PageInfo<Comment> findMyComments(HttpSession session,@RequestParam(value="pageNum",defaultValue="1",required=false)Integer pageNum, 
			@RequestParam(value="pageSize",defaultValue="6",required=false)Integer pageSize){
		System.out.println("findMyComments=====================>"+pageNum);
		return commentService.findMyComments(session,pageNum,pageSize);
	}
	/**
	 * 根据用户账号用户用户的所有的评论
	 * @param account
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("findCommentByAccount")
	public PageInfo<Comment> findCommentByAccount(String account,@RequestParam(value="pageNum",defaultValue="1",required=false)Integer pageNum, 
			@RequestParam(value="pageSize",defaultValue="6",required=false)Integer pageSize){
		System.out.println("findCommentByAccount=====================>"+pageNum);
		return commentService.findCommentByAccount(account,pageNum,pageSize);
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
	/**
	 * 查询当前登录用户是否点赞该评论
	 * @param session
	 * @param commentId
	 * @return
	 */
	@RequestMapping("checkCommentLike")
	public int checkCommentLike(HttpSession session,int commentId){
		System.out.println("checkCommentLike=====================>");
		return commentLikeService.checkCommentLike(session,commentId);
	}
	/**
	 * 点赞该评论
	 * @param session
	 * @param commentId
	 * @return
	 */
	@RequestMapping("addCommentLike")
	public Mes addCommentLike(HttpSession session,int commentId){
		System.out.println("addCommentLike=====================>");
		return commentLikeService.addCommentLike(session,commentId);
	}
	
}
