package com.liuhuangming.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Message;

public interface MessageService {
	/**
	 * 发送消息
	 * @param session
	 * @param message
	 * @return
	 */
	int sendMessage(Message message);
	/**
	 * 获取消息
	 * @param account
	 * @return
	 */
	List<Message> getMessages(HttpSession session);
	/**
	 * 获取我的消息数量
	 * @param account
	 * @return
	 */
	long countMessageByAccount(HttpSession session);
	/**
	 * 根据消息ID标记消息为已读
	 * @param messageId
	 * @return
	 */
	Mes readMessageByMessageId(int messageId);
	/**
	 * 一键已读所有消息
	 * @return
	 */
	Mes readMessages(HttpSession session);
	/**
	 * 将消息置为未读
	 * @param messageId
	 * @return
	 */
	Mes unReadMessage(int messageId);
	/**
	 * 根据id删除消息
	 * @param messageId
	 * @return
	 */
	Mes deleteMessage(int messageId);
}
