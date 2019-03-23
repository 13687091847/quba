package com.liuhuangming.bean;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SendEmail {
	@Resource(name = "mailSender")
	JavaMailSenderImpl mailSender;
	
	public void sendText(String email,int code) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("357066443@qq.com");
		mailMessage.setFrom("357066443@qq.com");
		mailMessage.setSubject("第一个文本文件");
		mailMessage.setText("hi");
		mailSender.send(mailMessage);
	}
}
