package com.liuhuangming.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.Contacts;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.service.ContactService;
import com.liuhuangming.service.UserInfoService;

/**
 * 用户控制器
 * 
 * @author LHM
 * 
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ContactService contactService;
	/**
	 * 验证用户是否存在
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "checkUser", produces = "application/json;charset=UTF-8")
	public boolean checkUser(String account) {
		System.out.println("checkUser=====>");
		return userInfoService.checkUser(account);
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("regist")
	public Message regist(UserInfo userInfo) {
		System.out.println("regist=====>");
		return userInfoService.regist(userInfo);
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("login")
	public Message login(UserInfo user, HttpSession httpSession) {
		System.out.println("lgin=====>");
		return userInfoService.login(user, httpSession);
	}

	/**
	 * 判断用户是否已登录，如果用户已登录，则返回当前登录用用户的详细信息，否则返回null
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("checkLogin")
	public Object checkLogin(HttpSession httpSession) {
		System.out.println("checkLogin=====>");
		return userInfoService.isLogin(httpSession);
	}

	/**
	 * 获取当前登录用户的信息
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("getUserInfor")
	public UserInfo getUserInfo(HttpSession httpSession) {
		UserInfo userInfo = userInfoService.getAll(httpSession);
		return userInfo;
	}
	/**
	 * 用户退出登录
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("quit")
	public Message quit(HttpSession httpSession) {
		return userInfoService.quit(httpSession);
	}

	/**
	 * 用户注销账号
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("logout")
	public Message logout(HttpSession httpSession) {
		return userInfoService.logout(httpSession);
	}

	/**
	 * 验证用户邮箱是否输入正确
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("checkEmail")
	public boolean checkEmail(String email) {
		return userInfoService.checkEmail(email);
	}

	/**
	 * 用户获取验证码
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("getCode")
	public Message getEmailCode(String email, HttpSession httpSession) {
		return userInfoService.getEmailCode(email, httpSession);
	}

	/**
	 * 验证用户验证码
	 * 
	 * @param code
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("checkCode")
	public boolean checkCode(String code, HttpSession httpSession) {
		return userInfoService.checkCode(code, httpSession);
	}

	/**
	 * 用户重设密码
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping("resetPassWord")
	public Message resetPassWord(String account, String password) {
		return userInfoService.resetPassWord(account, password);
	}

	/**
	 * 用户修改信息
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("updateUserInfo")
	public Message updateUserInfo(UserInfo userInfo,HttpSession httpSession) {
		System.err.println("updatauserInfo============>");
		return userInfoService.updateUserInfo(userInfo,httpSession);
	}
	/**
	 * 用户上传头像
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("uploadImg")
	public Message UploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpSession session) {
		return userInfoService.uploadImg(file,session);
	}
	/**
	 * 获取当前联系人的所有乘车人信息
	 * @param session
	 * @return
	 */
	@RequestMapping("getContacts")
	public List<Contacts> getContacts(HttpSession session){
		return contactService.getAll(session);
	}
	/**
	 * 添加联系人
	 * @param session
	 * @return
	 */
	@RequestMapping("addContact")
	public Message addContact(Contacts contacts,HttpSession session){
		return contactService.addContact(contacts,session);
	}
	/**
	 * 根据身份证和当前登录用户账号标记该联系人为删除状态
	 * @param idCard
	 * @param session
	 * @return
	 */
	@RequestMapping("deleteContact")
	public Message deleteContact(String idCard,HttpSession session){
		return contactService.deleteContact(idCard,session);
	}
	/**
	 * 测试登录
	 * 
	 * @param account
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("testLogin")
	public Message testLogin(HttpSession httpSession) {
		httpSession.setAttribute("account", "13687091847");
		Message message = new Message();
		message.setMessage("登录成功！");
		return message;
	}
}
