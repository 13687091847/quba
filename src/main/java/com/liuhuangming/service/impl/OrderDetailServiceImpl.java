package com.liuhuangming.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuhuangming.entity.OrderDetail;
import com.liuhuangming.entity.OrderDetailExample;
import com.liuhuangming.entity.OrderDetailExample.Criteria;
import com.liuhuangming.mapper.OrderDetailDAO;
import com.liuhuangming.service.ContactService;
import com.liuhuangming.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailDAO orderDetailDAO;
	@Autowired
	ContactService contactService;

	/**
	 * 批量添加订单详情
	 */
	@Override
	public int addOrderDetail(List<OrderDetail> orderDetails) {
		if (orderDetails != null) {
			int count = 0;
			for (OrderDetail orderDetail : orderDetails) {
				count = orderDetailDAO.insertSelective(orderDetail);
			}
			return count;
		}
		return -1;
	}

	/**
	 * 验证该订单是否已经提交
	 */
	@Override
	public boolean checkOrder(List<OrderDetail> orderDetails) {
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		Criteria criteria = orderDetailExample.createCriteria();
		if (orderDetails != null) {
			for (OrderDetail orderDetail : orderDetails) {
				// 通过购票用户的身份证号来搜索订单表中有的订单
				// 时间在48小时内，则认为时间冲突或重复提交订单
				criteria.andIdCardEqualTo(orderDetail.getIdCard());
				List<OrderDetail> listOrderDetails = orderDetailDAO.selectByExample(orderDetailExample);
				if (listOrderDetails != null) {
					for (OrderDetail orderDetail2 : listOrderDetails) {
						// 日期差，单位是天数
						int days = (int) ((orderDetail.getDeparturetime().getTime()
								- orderDetail2.getDeparturetime().getTime()) / (1000 * 3600 * 24));
						if (days <= 2) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 根据订单号获取相应的订单详情
	 */
	@Override
	public List<OrderDetail> getAllByOrderId(String orderId) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		Criteria criteria = orderDetailExample.createCriteria();
		if (orderId != null) {
			criteria.andOrderIdEqualTo(orderId);
			orderDetails = orderDetailDAO.selectByExample(orderDetailExample);
			// 根据身份证号查询对应的乘车人信息
			for (OrderDetail orderDetail : orderDetails) {
				orderDetail.setContacts(contactService.findByIdCard(orderDetail.getIdCard()));
				System.err.println(orderDetail.getDeparturetime());
			}
		}
		return orderDetails;
	}

	/**
	 * 根据orderID删除对应的订单详情
	 */
	@Override
	public int deleteByOrderId(String orderId) {
		int resultCode = 0;
		OrderDetailExample ordeDetailExample = new OrderDetailExample();
		Criteria criteria = ordeDetailExample.createCriteria();
		if (orderId != null) {
			criteria.andOrderIdEqualTo(orderId);
			resultCode = orderDetailDAO.deleteByExample(ordeDetailExample);
		}
		return resultCode;
	}

}
