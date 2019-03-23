package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.bean.Message;
import com.liuhuangming.entity.Contacts;

public interface ContactService {

	/**
	 * 获取所有的乘车人信息
	 * @return
	 */
	public List<Contacts> getAll(HttpSession session);
	/**
	 * 添加联系人
	 * @param contacts
	 * @param session
	 * @return
	 */
	public Message addContact(Contacts contacts,HttpSession session);
	/**
	 * 根据身份证和当前登录用户账号标记该联系人为删除状态
	 * @param idCard
	 * @param session
	 * @return
	 */
	public Message deleteContact(String idCard,HttpSession session);
}
