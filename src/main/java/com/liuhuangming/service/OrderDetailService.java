package com.liuhuangming.service;

import java.util.List;

import com.liuhuangming.entity.OrderDetail;

public interface OrderDetailService {

	/**
	 * 添加订单详情
	 * @param orderDetail
	 * @return
	 */
	int addOrderDetail(List<OrderDetail> orderDetails);
	/**
	 * 验证该订单是否已经提交
	 * @param orderDetails
	 * @return
	 */
	boolean checkOrder(List<OrderDetail> orderDetails);
	/**
	 * 根据订单号获取所有的订单详情
	 * @param orderId
	 * @return
	 */
	List<OrderDetail> getAllByOrderId(String orderId);
	/**
	 * 根据orderId删除对应的订单详情
	 * @param orderId
	 * @return
	 */
	int deleteByOrderId(String orderId);
}
