package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuhuangming.entity.Order;
import com.liuhuangming.entity.OrderExample;
import com.liuhuangming.entity.OrderExample.Criteria;
import com.liuhuangming.mapper.OrderDAO;
import com.liuhuangming.service.OrderDetailService;
import com.liuhuangming.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDAO;
	@Autowired
	OrderDetailService orderDetailService;

	/**
	 * 添加订单（不支付）
	 */
	@Override
	public int addOrder(Order order) {
		return orderDAO.insertSelective(order);
	}

	/**
	 * 订单支付
	 */
	@Override
	public int payOrder(String orderId) {
		Order order = new Order();
		order.setOrderStatus((byte)1);
		order.setOrderId(orderId);
		return orderDAO.updateByPrimaryKeySelective(order);
	}

	/**
	 * 根据订单号获取订单信息
	 */
	@Override
	public Order selectByOrderId(String orderId) {
		if (orderId != null) {
			return orderDAO.selectByPrimaryKey(orderId);
		}
		return null;
	}

	/**
	 * 获取对应账号的订单信息
	 */
	@Override
	public List<Order> selectByAccount(String account) {
		List<Order> orders = new ArrayList<>();
		OrderExample orderExample = new OrderExample();
		Criteria criteria = orderExample.createCriteria();
		if (account != null) {
			criteria.andAccountEqualTo(account);
			orders = orderDAO.selectByExample(orderExample);
			if (orders != null) {
				// 获取每个订单对应的订单详情
				for (Order order : orders) {
					order.setOrderDetails(orderDetailService.getAllByOrderId(order.getOrderId()));
					// 获取现在的时间用来与订单创建时间对比，如果时间超过15分钟且未付款，则关闭订单
					Date now = new Date();
					// 计算相差多少分钟
					long diff = now.getTime() - order.getCreateTime().getTime();
					long min = diff / (1000*60);
					if (min > 15 && order.getOrderStatus().equals((byte)0) ){
						// 订单超过15分钟未付款
						// 设置订单过期
						order.setOrderStatus((byte)2);
						//调用订单更新函数，修改订单为过期状态
						int code = orderDAO.updateByPrimaryKey(order);
						if(code > 0) {
							System.err.println("修改成功");
						}
					}
				}
			}
		}
		return orders;
	}
	/**
	 * 用户取消订单
	 */
	@Override
	public int cancelOrder(String orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderStatus((byte)-1);
		return orderDAO.updateByPrimaryKeySelective(order);
	}

}
