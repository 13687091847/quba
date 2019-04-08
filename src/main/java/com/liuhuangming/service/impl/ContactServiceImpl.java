package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuhuangming.bean.Mes;
import com.liuhuangming.entity.Contacts;
import com.liuhuangming.entity.ContactsExample;
import com.liuhuangming.entity.ContactsExample.Criteria;
import com.liuhuangming.mapper.ContactsDAO;
import com.liuhuangming.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactsDAO contactsDAO;

	/**
	 * 获取当前登录用户的所有的乘车人信息
	 */
	@Override
	public List<Contacts> getAll(HttpSession session) {
		// TODO Auto-generated method stub
		List<Contacts> contacts = new ArrayList<>();
		String account = (String) session.getAttribute("account");
		ContactsExample contactsExample = new ContactsExample();
		Criteria conCri = contactsExample.createCriteria();
		try {
			conCri.andAccountEqualTo(account);
			conCri.andStatusEqualTo(true);
			contacts = contactsDAO.selectByExample(contactsExample);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 * 添加联系人
	 */
	@Override
	public Mes addContact(Contacts contacts, HttpSession session) {
		// TODO Auto-generated method stub
		Mes message = new Mes();
		String account = (String) session.getAttribute("account");
		ContactsExample contactsExample = new ContactsExample();
		Criteria conCri = contactsExample.createCriteria();
		conCri.andAccountEqualTo(account);
		conCri.andIdCardEqualTo(contacts.getIdCard());
		List<Contacts> constactsList = contactsDAO.selectByExample(contactsExample);
		// 验证该用户的身份信息是否已存在
		if (constactsList.size() != 0) {
			message.setCode(500);
			message.setMessage("添加失败，请查看身份证是否有误");
			return message;
		}
		contacts.setAccount(account);
		contacts.setStatus(true);
		int resultCode = contactsDAO.insertSelective(contacts);
		if (resultCode > 0) {
			message.setCode(200);
			return message;
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 更新相应的联系人为删除状态
	 */
	@Override
	public Mes deleteContact(String idCard, HttpSession session) {

		System.err.println(idCard);
		Mes message = new Mes();
		String account = (String) session.getAttribute("account");
		Contacts contacts = new Contacts();
		contacts.setAccount(account);
		contacts.setIdCard(idCard);
		contacts.setStatus(false);
		int resultCode = contactsDAO.updateByPrimaryKeySelective(contacts);
		System.err.println(resultCode);
		if (resultCode > 0) {
			message.setCode(200);
			return message;
		}
		message.setCode(500);
		return message;
	}

	/**
	 * 根据idCard查询对应联系人信息
	 */
	@Override
	public Contacts findByIdCard(String idCard) {
		Contacts contacts = new Contacts();
		if (idCard != null) {
			contacts = contactsDAO.selectByPrimaryKey(idCard);
		}
		return contacts;
	}

}
