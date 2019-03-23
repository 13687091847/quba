package com.liuhuangming.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

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
	Message regist(UserInfo user);

	/**
	  * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	Message login(UserInfo user,HttpSession httpSession);

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
	/**
	 * 用户退出登录
	 * @param httpSession
	 * @return
	 */
	Message quit(HttpSession httpSession);
	/**
	 * 用户注销账号
	 * @param httpSession
	 * @return
	 */
	Message logout(HttpSession httpSession);
	/**
	 * 验证用户邮箱是否正确
	 * @param httpSession
	 * @return
	 */
	boolean checkEmail(String email);
	/**
	 * 用户获取验证码
	 * @param email
	 * @return
	 */
	Message getEmailCode(String email,HttpSession httpSession);
	/**
	 * 验证用户验证码
	 * @param code
	 * @param httpSession
	 * @return
	 */
	boolean checkCode(String code,HttpSession httpSession);
	/**
	 * 用户重设密码
	 * @param account
	 * @param password
	 * @return
	 */
	Message resetPassWord(String account,String password);
	/**
	 * 用户上传头像
	 * @param file
	 * @return
	 */
	Message uploadImg(MultipartFile file,HttpSession session);
}
