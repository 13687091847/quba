package com.liuhuangming.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liuhuangming.bean.Message;
import com.liuhuangming.bean.Upload;
import com.liuhuangming.entity.Fans;
import com.liuhuangming.entity.Follow;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.UserInfoExample;
import com.liuhuangming.entity.UserInfoExample.Criteria;
import com.liuhuangming.mapper.UserInfoDAO;
import com.liuhuangming.service.FansService;
import com.liuhuangming.service.FollowService;
import com.liuhuangming.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;
	@Autowired
	private FollowService followService;
	@Autowired
	private FansService fansService;
	@Resource(name = "mailSender")
	JavaMailSenderImpl mailSender;
	// 定义邮件的发送方
	private static final String FROM_EMAIL = "357066443@qq.com";

	/**
	 * 验证用户是否存在(如果返回true则说明用户不存在，false说明存在)
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
	public Message regist(UserInfo userInfo) {
		// TODO Auto-generated method stub
		Message message = new Message();
		try {
			if (checkUser(userInfo.getAccount()) == false) {
				message.setCode(500);
			} else {
				// 初始化钱包金额
				userInfo.setMoney(0);
				userInfo.setFansNum(0);
				userInfo.setFollowerNum(0);
				userInfo.setStatus(true);
				int resultCode = userInfoDAO.insert(userInfo);
				if (resultCode > 0) {
					message.setCode(200);

				} else {
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
		if (checkUser(userInfo.getAccount()) != true) {
			userInfo = userInfoDAO.selectByPrimaryKey(userInfo.getAccount());
			if (userInfo.getPassword().equals(password) && userInfo.getStatus().equals(true)) {
				message.setCode(200);
				httpSession.setAttribute("account", userInfo.getAccount());
			} else {
				message.setCode(500);
			}
		} else {
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
		String account = (String) httpSession.getAttribute("account");
		System.out.println(account + new Date());
		if (account != null) {
			UserInfo userInfo = getAll(httpSession);
			return userInfo;
		} else {
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
			String account = (String) httpSession.getAttribute("account");
			userInfo = userInfoDAO.selectByPrimaryKeyWithoutPassword(account);
			// 获取关注数
			List<Follow> followsList = followService.getAll(account);
			if (followsList.isEmpty() == true) {
				userInfo.setFollowerNum(0);
			} else {
				userInfo.setFollowerNum(followsList.size());
			}
			// 获取粉丝数
			List<Fans> fansList = fansService.getAll(account);
			if (fansList.isEmpty() == true) {
				userInfo.setFansNum(0);
			} else {
				userInfo.setFansNum(fansList.size());
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
		String account = (String) httpSession.getAttribute("account");
		userInfo.setAccount(account);
		int resultCode = userInfoDAO.updateByPrimaryKeySelective(userInfo);
		System.err.println(resultCode);
		if (resultCode > 0) {
			message.setCode(200);
			message.setMessage("修改成功");
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 用户退出登录
	 */
	@Override
	public Message quit(HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		httpSession.removeAttribute("account");
		if (httpSession.getAttribute("account") == null) {
			message.setCode(200);
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 用户注销账号
	 */
	@Override
	public Message logout(HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		String account = (String) httpSession.getAttribute("account");
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount(account);
		userInfo.setStatus(false);
		httpSession.removeAttribute("account");
		int result = userInfoDAO.updateByPrimaryKeySelective(userInfo);
		if (result > 0) {
			message.setCode(200);
		} else {
			message.setCode(500);
		}
		return message;
	}

	/**
	 * 验证用户邮箱是否正确
	 */
	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		// 判断参数不为空
		if (email == null) {
			return false;
		}
		UserInfoExample userInfoExample = new UserInfoExample();
		Criteria checkCri = userInfoExample.createCriteria();
		checkCri.andEmailEqualTo(email);
		List<UserInfo> uList = userInfoDAO.selectByExample(userInfoExample);
		System.err.println(uList.size());
		if (null != uList && uList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 发送验证码至邮箱
	 */
	@Override
	public Message getEmailCode(String email,HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		// 获取上次获得验证码的时间，若时间在15分钟内，则不改变验证码
		Date lastTime = (Date) httpSession.getAttribute("createCodeTime");
		// 获取上一次生成的验证码
		String code = (String) httpSession.getAttribute("code");

		// 获取当前时间
		Date currTime = new Date();
		if ((null != lastTime) && (null != code)) {// 非首次获取验证码
			// 时间差
			long diff = (currTime.getTime() - lastTime.getTime()) / 60 / 1000;
			if (diff > 15) {// 超时
				// 随机生成六位数验证码x
				code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
			}
		} else {// 首次或者时间超过15分钟
				// 随机生成六位数验证码
			code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
		}

		// 发送邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setFrom(FROM_EMAIL);
		mailMessage.setSubject("密码修改-去吧");
		mailMessage.setText("您的验证码为： " + code + "\n15分钟内有效！");
		mailSender.send(mailMessage);

		// 储存验证码和生成时间
		httpSession.setAttribute("createCodeTime", currTime);
		httpSession.setAttribute("code", code);
		message.setCode(200);
		
		return message;
	}

	/**
	 * 验证用户验证码
	 */
	@Override
	public boolean checkCode(String code, HttpSession httpSession) {
		// TODO Auto-generated method stub
		String lastCode = (String)httpSession.getAttribute("code");
		if(code != null && code.equals(lastCode)) {
			return true;
		}
		return false;
	}
	/**
	 * 用户重设密码
	 */
	@Override
	public Message resetPassWord(String account, String password) {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		Message message = new Message();
		userInfo.setAccount(account);
		userInfo.setPassword(password);
		int result = userInfoDAO.updateByPrimaryKeySelective(userInfo);
		if(result > 0) {
			message.setCode(200);
			return message;
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 用户上传图片
	 */
	@Override
	public Message uploadImg(MultipartFile file,HttpSession session) {
		// TODO Auto-generated method stub
		Message message = new Message();
//		UserInfo userInfo = new UserInfo();
//		String account = (String)session.getAttribute("account");
		try {
			String imgUrl = Upload.uploadFile(file);
//			System.err.println(imgUrl);
//			userInfo.setAccount(account);
//			userInfo.setHeadImg(imgUrl);
//			//int result = userInfoDAO.updateByPrimaryKeySelective(userInfo);
			if(imgUrl != null) {
				message.setCode(200);
				message.setMessage(imgUrl);
			}else {
				message.setCode(500);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return message;
	}
	/**
	 * 根据用户id查询用户信息
	 */
	@Override
	public UserInfo getUserInfoByAccount(String account) {
		// TODO Auto-generated method stub
		return userInfoDAO.selectByPrimaryKey(account);
	}
}
