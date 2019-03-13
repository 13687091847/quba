package com.liuhuangming.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.User;

/**
  * 用户业务逻辑接口
 * 
 * @author LHM
 *
 */
@Service
public interface UserService {

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
	Message regist(User user, HttpSession httpSession);

	/**
	  * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	Message login(User user, HttpSession httpSession);

	/**
	  * 判断用户是否已登录
	 * 
	 * @param httpSession
	 * @return
	 */
	boolean isLogin(HttpSession httpSession);
	/**
	  * 获取用户的详细信息
	 * 
	 * @param httpSession
	 * @return
	 */
	Object getAll(HttpSession httpSession);
}
