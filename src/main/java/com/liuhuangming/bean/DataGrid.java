package com.liuhuangming.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来存放被pageHelper处理好的数据，返回给前台
 * @author LHM
 *
 */
public class DataGrid {
	/**
	 * 当前页的数量
	 */
	private long size;
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 要查询的数据
	 */
	private List rows = new ArrayList<>();
	
	
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public DataGrid() {
		super();
	}
	
	public DataGrid(long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
 
	public long getTotal() {
		return total;
	}
 
	public void setTotal(long total) {
		this.total = total;
	}
 
	public List getRows() {
		return rows;
	}
 
	public void setRows(List rows) {
		this.rows = rows;
	}
}
