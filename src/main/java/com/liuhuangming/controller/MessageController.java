package com.liuhuangming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Message;
import com.liuhuangming.service.MessageService;

@RestController
@RequestMapping("message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	/**
	 * 读消息
	 * @param session
	 * @return
	 */
	@RequestMapping("readMyMessageByMessageId")
	public Mes readMyMessageByMessageId(int messageId) {
		System.out.println("readMyMessageByMessageId================>");
		return messageService.readMessageByMessageId(messageId);
	}
	/**
	 * 获取用户消息数量
	 * @param session
	 * @return
	 */
	@RequestMapping("getMyMessageNum")
	public long getMyMessageNum(HttpSession session) {
		System.out.println("getMyMessageNum================>");
		return messageService.countMessageByAccount(session);
	}
	/**
	 * 获取用户消息
	 * @param session
	 * @return
	 */
	@RequestMapping("getMyMessage")
	public List<Message> getMyMessage(HttpSession session) {
		System.out.println("getMyMessage================>");
		return messageService.getMessages(session);
	}
	/**
	 * 一键已读
	 * @param session
	 * @return
	 */
	@RequestMapping("readMyMessages")
	public Mes readMyMessages(HttpSession session) {
		System.out.println("readMyMessageByMessageId================>");
		return messageService.readMessages(session);
	}
	/**
	 * 将消息置为未读
	 * @param messageId
	 * @return
	 */
	@RequestMapping("unReadMessage")
	public Mes unReadMessage(int messageId) {
		System.out.println("unReadMessage================>");
		return messageService.unReadMessage(messageId);
	}
	/**
	 * 删除消息
	 * @param messageId
	 * @return
	 */
	@RequestMapping("deleteMessage")
	public Mes deleteMessage(int messageId) {
		System.out.println("deleteMessage================>");
		return messageService.deleteMessage(messageId);
	}
}
