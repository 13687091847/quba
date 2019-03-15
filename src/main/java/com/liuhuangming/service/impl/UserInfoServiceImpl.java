package com.liuhuangming.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.Follow;
import com.liuhuangming.entity.FollowExample;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.UserInfoExample;
import com.liuhuangming.entity.UserInfoExample.Criteria;
import com.liuhuangming.mapper.FansDAO;
import com.liuhuangming.mapper.FollowDAO;
import com.liuhuangming.mapper.UserInfoDAO;
import com.liuhuangming.service.UserInfoService;
import com.sun.tools.javac.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	@Autowired
	private FollowDAO followDAO;
	@Autowired
	private FansDAO fasDAO;
	
	
	/**
	  * 验证用户是否存在
	 */
	@Override
	public boolean checkUser(String account) {
		// TODO Auto-generated method stub
		UserInfoExample userExample = new UserInfoExample();
		Criteria cheCri = userExample.createCriteria();
		cheCri.andAccountEqualTo(account);
		long count = userInfoDAO.countByExample(userExample);
		return count == 0;
	}
	/**
	  * 用户注册
	 */
	@Override
	public Message regist(UserInfo userInfo, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		try {
			if(checkUser(userInfo.getAccount())==false) {
				message.setCode(500);
			}else {
				//初始化钱包金额
				userInfo.setMoney(0);
				int resultCode = userInfoDAO.insert(userInfo);
				if(resultCode > 0) {
					message.setCode(200);
					httpSession.setAttribute("account", userInfo.getAccount());
				}else {
					message.setCode(500);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setCode(500);
		}
		return message;
	}
	/**
	  * 用户登录
	 */
	@Override
	public Message login(UserInfo userInfo, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		String password = userInfo.getPassword();
		userInfo = userInfoDAO.selectByPrimaryKey(userInfo.getAccount());
		if(userInfo.getPassword().equals(password)) {
			message.setCode(200);
			httpSession.setAttribute("account", userInfo.getAccount());
		}else {
			message.setCode(500);
		}
		return message;
	}
	/**
	  * 判断用户是否登录,如果登录则返回当前登录用户的详细信息，否则返回null
	 */
	@Override
	public Object isLogin(HttpSession httpSession) {
		// TODO Auto-generated method stub
		String account = (String)httpSession.getAttribute("account");
		if(account != null) {
			UserInfo userInfo = getAll(httpSession);
			return userInfo;
		}else {
			return null;
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
			userInfo = userInfoDAO.selectByPrimaryKey(account);
			//获取关注数
			List<Follow> resultList = followDAO.selectByAccount(account);
			if(resultList.isEmpty() == true) {
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userInfo;
	}
	/**
	 * 更新当前登录用户信息
	 */
	@Override
	public Message updateUserInfo(UserInfo userInfo, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		String account = (String)httpSession.getAttribute("account");
		Date date = new Date();//获得系统时间.
		String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(date);//将时间格式转换成符合Timestamp要求的格式.
		userInfo.setAccount(account);
		userInfo.setBirthDay(nowTime);
		UserInfoExample userInfoExample = new UserInfoExample();
		int resultCode = userInfoDAO.updateByExample(userInfo, userInfoExample);
		if(resultCode > 0) {
			message.setCode(200);
			message.setMessage("修改成功");
		}else {
			message.setCode(-101);
			message.setMessage("修改失败");
		}
		return message;
	}
}
