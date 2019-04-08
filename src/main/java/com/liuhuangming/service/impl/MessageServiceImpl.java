package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Message;
import com.liuhuangming.entity.MessageExample;
import com.liuhuangming.entity.MessageExample.Criteria;
import com.liuhuangming.mapper.MessageDAO;
import com.liuhuangming.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageDAO messageDAO;
	
	/**
	 * 发送消息给指定用户
	 */
	@Override
	public int sendMessage(Message message) {
		return messageDAO.insertSelective(message);
	}
	/**
	 * 获取所有消息
	 */
	@Override
	public List<Message> getMessages(HttpSession session) {
		String account = (String)session.getAttribute("account");
		List<Message> messages = new ArrayList<>();
		MessageExample messageExample = new MessageExample();
		messageExample.setOrderByClause("create_time desc");
		Criteria criteria = messageExample.createCriteria();
		if(account != null) {
			criteria.andSendToEqualTo(account);
			criteria.andStatusBetween((byte)0, (byte)1);
			messages = messageDAO.selectByExample(messageExample);
		}
		return messages;
	}
	/**
	 * 获取我的未读消息数量
	 */
	@Override
	public long countMessageByAccount(HttpSession session) {
		String account = (String)session.getAttribute("account");
		MessageExample messageExample = new MessageExample();
		Criteria criteria = messageExample.createCriteria();
		if(account != null) {
			criteria.andSendToEqualTo(account);
			criteria.andStatusEqualTo((byte)0);
			return messageDAO.countByExample(messageExample);
		}
		return 0;
	}
	/**
	 * 根据消息ID标记消息为已读
	 */
	@Override
	public Mes readMessageByMessageId(int messageId) {
		Mes mes = new Mes();
		Message message = new Message();
		message.setMessageId(messageId);
		message.setStatus((byte)1);
		int updateCode = messageDAO.updateByPrimaryKeySelective(message);
		if(updateCode > 0) {
			mes.setCode(200);
			return mes;
		}
		mes.setCode(500);
		return mes;
	}
	/**
	 * 一键已读
	 */
	@Override
	public Mes readMessages(HttpSession session) {
		Mes mes = new Mes();
		String account = (String)session.getAttribute("account");
		MessageExample messageExample = new MessageExample();
		Criteria criteria = messageExample.createCriteria();
		int count = 0;
		//先查出该账号有哪些消息是未读的
		if(account != null) {
			criteria.andSendToEqualTo(account);
			criteria.andStatusEqualTo((byte)0);
			List<Message> messages = messageDAO.selectByExample(messageExample);
			if(messages != null) {
				int size = messages.size();
				for(Message message : messages) {
					message.setStatus((byte)1);
					count += messageDAO.updateByPrimaryKeySelective(message);
				}
				if(size == count) {
					mes.setCode(200);
					return mes;
				}
			}
			
		}
		mes.setCode(500);
		return mes;
	}
	/**
	 * 将消息置为未读
	 */
	@Override
	public Mes unReadMessage(int messageId) {
		Mes mes = new Mes();
		Message message = new Message();
		message.setMessageId(messageId);
		message.setStatus((byte)0);
		int updateCode = messageDAO.updateByPrimaryKeySelective(message);
		if(updateCode > 0) {
			mes.setCode(200);
			return mes;
		}
		mes.setCode(500);
		return mes;
	}
	/**
	 * 根据消息id删除消息
	 */
	@Override
	public Mes deleteMessage(int messageId) {
		Mes mes = new Mes();
		Message message = new Message();
		message.setMessageId(messageId);
		message.setStatus((byte)2);
		int updateCode = messageDAO.updateByPrimaryKeySelective(message);
		if(updateCode > 0) {
			mes.setCode(200);
			return mes;
		}
		mes.setCode(500);
		return mes;
	}
	

}
