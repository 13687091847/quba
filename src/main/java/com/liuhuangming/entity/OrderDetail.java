package com.liuhuangming.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * tb_order_detail
 * 
 * @author
 */
public class OrderDetail implements Serializable {
	/**
	 * 表ID
	 */
	private Integer orderDetailId;

	/**
	 * 订单ID
	 */
	private String orderId;

	/**
	 * 火车编号
	 */
	private String trainNo;

	/**
	 * 乘车人身份证号码
	 */
	private String idCard;

	/**
	 * 起始站
	 */
	private String startArea;

	/**
	 * 终点站
	 */
	private String endArea;

	/**
	 * 坐席类型
	 */
	private String seatType;

	/**
	 * 发车时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date departuretime;

	/**
	 * 该订单详情金额
	 */
	private BigDecimal price;

	/**
	 * 乘车人信息
	 */
	private Contacts contacts;

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	private static final long serialVersionUID = 1L;

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStartArea() {
		return startArea;
	}

	public void setStartArea(String startArea) {
		this.startArea = startArea;
	}

	public String getEndArea() {
		return endArea;
	}

	public void setEndArea(String endArea) {
		this.endArea = endArea;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public Date getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(Date departuretime) {
		this.departuretime = departuretime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		OrderDetail other = (OrderDetail) that;
		return (this.getOrderDetailId() == null ? other.getOrderDetailId() == null
				: this.getOrderDetailId().equals(other.getOrderDetailId()))
				&& (this.getOrderId() == null ? other.getOrderId() == null
						: this.getOrderId().equals(other.getOrderId()))
				&& (this.getTrainNo() == null ? other.getTrainNo() == null
						: this.getTrainNo().equals(other.getTrainNo()))
				&& (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
				&& (this.getStartArea() == null ? other.getStartArea() == null
						: this.getStartArea().equals(other.getStartArea()))
				&& (this.getEndArea() == null ? other.getEndArea() == null
						: this.getEndArea().equals(other.getEndArea()))
				&& (this.getSeatType() == null ? other.getSeatType() == null
						: this.getSeatType().equals(other.getSeatType()))
				&& (this.getDeparturetime() == null ? other.getDeparturetime() == null
						: this.getDeparturetime().equals(other.getDeparturetime()))
				&& (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOrderDetailId() == null) ? 0 : getOrderDetailId().hashCode());
		result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result + ((getTrainNo() == null) ? 0 : getTrainNo().hashCode());
		result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
		result = prime * result + ((getStartArea() == null) ? 0 : getStartArea().hashCode());
		result = prime * result + ((getEndArea() == null) ? 0 : getEndArea().hashCode());
		result = prime * result + ((getSeatType() == null) ? 0 : getSeatType().hashCode());
		result = prime * result + ((getDeparturetime() == null) ? 0 : getDeparturetime().hashCode());
		result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", orderDetailId=").append(orderDetailId);
		sb.append(", orderId=").append(orderId);
		sb.append(", trainNo=").append(trainNo);
		sb.append(", idCard=").append(idCard);
		sb.append(", startArea=").append(startArea);
		sb.append(", endArea=").append(endArea);
		sb.append(", seatType=").append(seatType);
		sb.append(", departuretime=").append(departuretime);
		sb.append(", price=").append(price);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}