package com.liuhuangming.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Follow;
import com.liuhuangming.entity.FollowExample;
import com.liuhuangming.entity.FollowExample.Criteria;
import com.liuhuangming.mapper.FollowDAO;
import com.liuhuangming.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDAO followDAO;

	/**
	 * 根据用户名获取用户关注的人
	 */
	@Override
	public List<Follow> getAllByAccount(String account) {
		FollowExample followExample = new FollowExample();
		Criteria getCri = followExample.createCriteria();
		getCri.andAccountEqualTo(account);
		getCri.andStatusEqualTo(true);
		List<Follow> resultList = followDAO.selectByExample(followExample);
		return resultList;
	}

	/**
	 * 根据用户名获取用户的粉丝
	 */
	@Override
	public List<Follow> getAllByFollowedUser(String account) {
		FollowExample followExample = new FollowExample();
		Criteria getCri = followExample.createCriteria();
		getCri.andFollowedUserEqualTo(account);
		getCri.andStatusEqualTo(true);
		return followDAO.selectByExample(followExample);
	}

	/**
	 * 当前登录用户关注或取消关注其他用户
	 */
	@Override
	public long followOther(String followedUser, HttpSession session) {
		String account = (String) session.getAttribute("account");
		Follow follow = new Follow();
		long resultCode = 0;
		// 先判断记录是否存在
		Mes mes = checkIsExist(account, followedUser);
		int followId = (int)mes.getCode();
		if (followId != 0) {
			// 存在，则直接修改状态值即可
			if(mes.getMessage().equals("已关注")) {
				//状态为关注状态，所以现在是取消关注
				follow.setStatus(false);
			}else {
				//状态是取消关注状态，现在是改为关注状态
				follow.setStatus(true);
			}
			follow.setId(followId);
			resultCode = followDAO.updateByPrimaryKeySelective(follow);
		} else {
			// 不存在，则插入一条新的数据
			follow.setAccount(account);
			follow.setFollowedUser(followedUser);
			follow.setStatus(true);
			resultCode = followDAO.insertSelective(follow);
		}
		
		return resultCode;
	}

	/**
	 * 查询这前前面参数指代的用户之前是否关注过后面的用户（也就是查询第一个用户关注第二个用户是否存在于关注表）
	 */
	@Override
	public Mes checkIsExist(String account, String followedUser) {
		Mes mes = new Mes();
		FollowExample followExample = new FollowExample();
		Criteria criteria = followExample.createCriteria();
		if (account != null) {
			criteria.andAccountEqualTo(account);
			List<Follow> follows = followDAO.selectByExample(followExample);
			for (Follow follow : follows) {
				if (follow.getFollowedUser().equals(followedUser)) {
					// 说明之前存在,但是不知道是否是关注状态
					if(follow.getStatus() == true) {
						//是关注状态
						mes.setMessage("已关注");
						mes.setCode(follow.getId());
					}else {
						//是取消状态
						mes.setMessage("取消关注");
						mes.setCode(follow.getId());
					}
					return mes;
				}
			}
		}
		mes.setCode(0);
		mes.setMessage("记录不存在");
		return mes;
	}
	
	
}
