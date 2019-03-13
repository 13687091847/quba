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
}
