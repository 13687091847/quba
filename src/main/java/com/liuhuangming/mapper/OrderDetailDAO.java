package com.liuhuangming.mapper;

import com.liuhuangming.entity.OrderDetail;
import com.liuhuangming.entity.OrderDetailExample;
import org.springframework.stereotype.Repository;

/**
 * OrderDetailDAO继承基类
 */
@Repository
public interface OrderDetailDAO extends MyBatisBaseDao<OrderDetail, Integer, OrderDetailExample> {
	
}