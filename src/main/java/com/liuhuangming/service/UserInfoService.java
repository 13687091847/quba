package com.liuhuangming.service;

import javax.servlet.http.HttpSession;
import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.UserInfo;


public interface UserInfoService {

	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	Message updateUserInfo(UserInfo userInfo,HttpSession httpSession);
	/**
	  * 验证用户是否存在
	 * 
	 * @param account
	 * @return
	 */
	boolean checkUser(String account);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	Message regist(UserInfo user, HttpSession httpSession);

	/**
	  * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	Message login(UserInfo user, HttpSession httpSession);

	/**
	  * 判断用户是否已登录
	 * 
	 * @param httpSession
	 * @return
	 */
	Object isLogin(HttpSession httpSession);
	/**
	  * 获取用户的详细信息
	 * 
	 * @param httpSession
	 * @return
	 */
	UserInfo getAll(HttpSession httpSession);
}
