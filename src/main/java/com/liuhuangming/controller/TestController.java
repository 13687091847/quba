package com.liuhuangming.controller;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("testEmail")
public class TestController {
	@Resource(name = "mailSender")
	JavaMailSenderImpl mailSender;

	@RequestMapping(path = "/sendword")
	public void handle(){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("357066443@qq.com");
		mailMessage.setFrom("357066443@qq.com");
		mailMessage.setSubject("第一个文本文件");
		mailMessage.setText("hi");
		mailSender.send(mailMessage);
	}
}