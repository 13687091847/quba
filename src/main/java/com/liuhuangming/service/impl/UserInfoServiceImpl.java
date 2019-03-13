package com.liuhuangming.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.UserInfo;
import com.liuhuangming.entity.UserInfoExample;
import com.liuhuangming.mapper.UserInfoMapper;
import com.liuhuangming.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	/**
	 * 更新当前登录用户信息
	 */
	@Override
	public Message updateUserInfo(UserInfo userInfo, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Message message = new Message();
		String account = (String)httpSession.getAttribute("account");
		userInfo.setAccount(account);
		UserInfoExample userInfoExample = new UserInfoExample();
		int result = userInfoMapper.updateByExample(userInfo, userInfoExample);
		if(result > 0) {
			message.setCode(200);
			message.setMessage("修改成功");
		}else {
			message.setCode(-101);
			message.setMessage("修改失败");
		}
		return message;
	}
}
