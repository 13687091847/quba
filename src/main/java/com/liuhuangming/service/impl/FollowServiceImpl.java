package com.liuhuangming.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * 当前登录用户关注其他用户
	 */
	@Override
	public long followOther(String followedUser, HttpSession session) {
		String account = (String) session.getAttribute("account");
		Follow follow = new Follow();
		long resultCode = 0;
		// 先判断记录是否存在
		int followId = checkIsExist(account, followedUser);
		if (followId != 0) {
			// 存在，则直接修改状态值即可
			follow.setStatus(true);
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
	public int checkIsExist(String account, String followedUser) {
		FollowExample followExample = new FollowExample();
		Criteria criteria = followExample.createCriteria();
		if (account != null) {
			criteria.andAccountEqualTo(account);
			List<Follow> follows = followDAO.selectByExample(followExample);
			for (Follow follow : follows) {
				if (follow.getFollowedUser().equals(followedUser)) {
					// 说明之前存在,返回该关注记录的ID
					return follow.getId();
				}
			}
		}
		return 0;
	}

}
