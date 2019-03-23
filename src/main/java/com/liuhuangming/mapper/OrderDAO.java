package com.liuhuangming.mapper;

import com.liuhuangming.entity.Order;
import com.liuhuangming.entity.OrderExample;
import org.springframework.stereotype.Repository;

/**
 * OrderDAO继承基类
 */
@Repository
public interface OrderDAO extends MyBatisBaseDao<Order, String, OrderExample> {
}