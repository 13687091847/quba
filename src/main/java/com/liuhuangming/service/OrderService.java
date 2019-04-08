package com.liuhuangming.service;

import java.util.List;
import com.liuhuangming.entity.Order;

/**
 * 订单管理接口
 * @author Administrator
 *
 */
public interface OrderService {

	/**
	 * 添加订单（不支付）
	 * @param order
	 * @return
	 */
	int addOrder(Order order);
	/**
	 * 订单支付
	 * @param order
	 * @return
	 */
	int payOrder(String orderId);
	/**
	 * 根据订单号获取订单信息
	 * @param orderId
	 * @return
	 */
	Order selectByOrderId(String orderId);
	/**
	 * 获取当前登录用户的订单信息
	 * @param session
	 * @return
	 */
	List<Order> selectByAccount(String account);
	/**
	 * 用户取消订单
	 * @param orderId
	 * @return
	 */
	int cancelOrder(String orderId);
}
