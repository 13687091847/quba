package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Contacts;

public interface ContactService {

	/**
	 * 获取所有的乘车人信息
	 * @return
	 */
	List<Contacts> getAll(HttpSession session);
	/**
	 * 添加联系人
	 * @param contacts
	 * @param session
	 * @return
	 */
	Mes addContact(Contacts contacts,HttpSession session);
	/**
	 * 根据身份证和当前登录用户账号标记该联系人为删除状态
	 * @param idCard
	 * @param session
	 * @return
	 */
	Mes deleteContact(String idCard,HttpSession session);
	/**
	 * 根据身份证查询对应的联系人信息
	 * @param idCard
	 * @return
	 */
	Contacts findByIdCard(String idCard);
}
