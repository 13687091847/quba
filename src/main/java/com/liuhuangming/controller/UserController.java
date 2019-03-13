package com.liuhuangming.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.User;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.service.UserInfoService;
import com.liuhuangming.service.UserService;

/**
 * 用户控制器
 * 
 * @author LHM
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 验证用户是否存在
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "checkUser", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public boolean checkUser(String account) {
		System.out.println("checkUser=====>");
		return userService.checkUser(account);
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("regist")
	@ResponseBody
	public Message regist(User user, HttpSession httpSession) {
		System.out.println("regist=====>");
		return userService.regist(user, httpSession);
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public Message login(User user, HttpSession httpSession) {
		System.out.println("lgin=====>");
		return userService.login(user, httpSession);
	}

	/**
	 * 判断用户是否已登录
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("checkLogin")
	@ResponseBody
	public boolean checkLogin(HttpSession httpSession) {
		System.out.println("checkLogin=====>");
		return userService.isLogin(httpSession);
	}

	/**
	 * 获取当前登录用户的信息
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("getUserInfor")
	@ResponseBody
	public Object getUserInfo(HttpSession httpSession) {
		Object userInfo = userService.getAll(httpSession);
		return userInfo;
	}

	/**
	 * 修改当前用户的信息
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("updateUserInfo")
	@ResponseBody
	public Message updateUserInfo(@RequestBody UserInfo userInfo,HttpSession httpSession) {
		Message message = userInfoService.updateUserInfo(userInfo,httpSession);
		return message;
	}
	/**
	 * 测试登录
	 * @param account
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("testLogin")
	@ResponseBody
	public Message testLogin(String account, HttpSession httpSession) {
		httpSession.setAttribute("account", account);
		Message message = new Message();
		message.setMessage(account + "登录成功！");	
		return message;
	}
}
