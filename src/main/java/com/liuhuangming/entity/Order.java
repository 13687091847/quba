package com.liuhuangming.entity;

import java.util.Date;

public class Order {
    public Order() {
		super();
	}

	public Order(Long orderId, String account, Date createTime, Boolean orderStatus, Integer totalPrice, Date payTime) {
		super();
		this.orderId = orderId;
		this.account = account;
		this.createTime = createTime;
		this.orderStatus = orderStatus;
		this.totalPrice = totalPrice;
		this.payTime = payTime;
	}

	private Long orderId;

    private String account;

    private Date createTime;

    private Boolean orderStatus;

    private Integer totalPrice;

    private Date payTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}