package com.liuhuangming.mapper;

import com.liuhuangming.entity.Collection;
import com.liuhuangming.entity.CollectionExample;
import org.springframework.stereotype.Repository;

/**
 * CollectionDAO继承基类
 */
@Repository
public interface CollectionDAO extends MyBatisBaseDao<Collection, Integer, CollectionExample> {
}