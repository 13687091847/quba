package com.liuhuangming.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.User;
import com.liuhuangming.entity.UserExample;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.UserExample.Criteria;
import com.liuhuangming.mapper.UserInfoMapper;
import com.liuhuangming.mapper.UserMapper;
import com.liuhuangming.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	/**
	  * 验证用户是否存在
	 */
	@Override
	public boolean checkUser(String account) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria cheCri = userExample.createCriteria();
		cheCri.andAccountEqualTo(account);
		long count = userMapper.countByExample(userExample);
		return count == 0;
	}
	/**
	  * 用户注册
	 */
	@Override
	public Message regist(User user, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		try {
			if(checkUser(user.getAccount())==false) {
				message.setCode(-102);
				message.setMessage("注册失败！");
			}else {
				int countA = userMapper.insert(user);
				UserInfo userInfo = new UserInfo(user.getAccount(),user.getEmail(),0);
				int countB = userInfoMapper.insert(userInfo);
				System.out.println("countA=====>" + countA + "  countB====>" + countB);
				if(countA > 0 && countB > 0) {
					message.setCode(200);
					message.setMessage("注册成功！");
					httpSession.setAttribute("account", user.getAccount());
				}else {
					message.setCode(-102);
					message.setMessage("注册失败！");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setCode(-102);
			message.setMessage("注册失败！");
		}
		return message;
	}
	/**
	  * 用户登录
	 */
	@Override
	public Message login(User user, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		if(checkUser(user.getAccount())==false) {
			UserExample userExample = new UserExample();
			Criteria logCri = userExample.createCriteria();
			logCri.andAccountEqualTo(user.getAccount());
			logCri.andPasswordEqualTo(user.getPassword());
			long count = userMapper.countByExample(userExample);
			if(count > 0) {
				message.setCode(200);
				message.setMessage("欢迎"+user.getAccount()+"登录！");
				httpSession.setAttribute("account", user.getAccount());
			}else {
				message.setCode(-102);
				message.setMessage("登录失败");
			}
		}else {
			message.setCode(-102);
			message.setMessage("登录失败");
		}
		return message;
	}
	/**
	  * 判断用户是否登录
	 */
	@Override
	public boolean isLogin(HttpSession httpSession) {
		// TODO Auto-generated method stub
		if(httpSession.getAttribute("account") != null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 获取当前登录用户的信息
	 */
	@Override
	public UserInfo getAll(HttpSession httpSession) {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		try {
			String account = (String)httpSession.getAttribute("account");
			userInfo = userInfoMapper.selectByAccount(account);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userInfo;
	}

}
