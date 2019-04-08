package com.liuhuangming.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Comment;
import com.liuhuangming.entity.CommentLike;
import com.liuhuangming.entity.CommentLikeExample;
import com.liuhuangming.entity.CommentLikeExample.Criteria;
import com.liuhuangming.mapper.CommentLikeDAO;
import com.liuhuangming.service.CommentLikeService;
import com.liuhuangming.service.CommentService;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

	@Autowired
	CommentLikeDAO commentLikeDAO;
	@Autowired
	CommentService commentService;
	/**
	 * 通过评论ID查询该评论点赞数量
	 */
	@Override
	public long countCommentLikeByCommentId(int commentId) {
		CommentLikeExample commentLikeExample = new CommentLikeExample();
		Criteria criteria = commentLikeExample.createCriteria();
		criteria.andCommentIdEqualTo(commentId);
		return commentLikeDAO.countByExample(commentLikeExample);
	}
	 /**
	  * 根据评论ID判断当前登录用户是否点赞该评论
	  */
	@Override
	public int checkCommentLike(HttpSession session, int commentId) {
		String account = (String)session.getAttribute("account");
		CommentLikeExample commentLikeExample = new CommentLikeExample();
		Criteria criteria = commentLikeExample.createCriteria();
		if(account != null) {
			criteria.andAccountEqualTo(account);
			criteria.andCommentIdEqualTo(commentId);
			List<CommentLike> commentLikes = commentLikeDAO.selectByExample(commentLikeExample);
			if(commentLikes != null) {
				//说明该用户存在点赞表中，且对该评论进行过操作
				for(CommentLike commentLike : commentLikes) {
					if(commentLike.getStatus() == true){
						//说明该用户点赞了该评论
						return 1;
					}else {
						//用户已经取消收藏
						return 2;
					}
				}
			}else {
				//该用户不存在点赞表，没有对该游记进行过点赞操作
				return 3;
			}
		}
		return -1;
	}
	/**
	 * 为评论添加点赞
	 */
	@Override
	public Mes addCommentLike(HttpSession session,int commentId) {
		Mes message = new Mes();
		CommentLike commentLike = new CommentLike();
		String account = (String)session.getAttribute("account");
		Comment comment = commentService.findByCommentId(commentId);
		int updateCommentLike = 0;
		int updateComment = 0;
		if(account != null) {
			//首先去数据库查询当前登录用户是否点赞该评论
			if( checkCommentLike(session,commentId) == 1 ) {
				//已点赞，则对应取消点赞
				CommentLikeExample commentLikeExample = new CommentLikeExample();
				Criteria criteria = commentLikeExample.createCriteria();
				criteria.andAccountEqualTo(account);
				criteria.andCommentIdEqualTo(commentId);
				commentLike.setStatus(false);
				updateCommentLike = commentLikeDAO.updateByExampleSelective(commentLike, commentLikeExample);
				//相应的评论表中的点赞数量要减一
				//更新点赞数量
				comment.setLikeNum(comment.getLikeNum() - 1);
				comment.setLike(false);
				updateComment = commentService.updateComment(comment);
				
			}else if( checkCommentLike(session,commentId) == 2 ){
				//说明用户已经取消点赞，修改状态为点赞
				commentLike.setStatus(true);
				CommentLikeExample commentLikeExample = new CommentLikeExample();
				Criteria criteria = commentLikeExample.createCriteria();
				criteria.andAccountEqualTo(account);
				criteria.andCommentIdEqualTo(commentId);
				updateCommentLike = commentLikeDAO.updateByExampleSelective(commentLike, commentLikeExample);
				comment.setLikeNum( comment.getLikeNum()  + 1);
				comment.setLike(true);
				updateComment = commentService.updateComment(comment);
			}else {
				//说明用户首次点赞
				commentLike.setAccount(account);
				commentLike.setCommentId(commentId);
				commentLike.setStatus(true);
				//首先更新点赞表中的信息
				updateCommentLike = commentLikeDAO.insert(commentLike);
				//然后更新评论表中对应评论的点赞数量
				comment.setLikeNum( comment.getLikeNum() + 1);
				comment.setLike(true);
				updateComment = commentService.updateComment(comment);
			}			
			if(updateCommentLike > 0 && updateComment > 0) {
				message.setCode(200);
			}else {
				message.setCode(500);
			}
			
		}
		return message;
	}

}
