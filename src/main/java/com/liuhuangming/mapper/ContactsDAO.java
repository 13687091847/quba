package com.liuhuangming.mapper;

import com.liuhuangming.entity.Contacts;
import com.liuhuangming.entity.ContactsExample;
import org.springframework.stereotype.Repository;

/**
 * ContactsDAO继承基类
 */
@Repository
public interface ContactsDAO extends MyBatisBaseDao<Contacts, Integer, ContactsExample> {
}