package com.nike.dto;

import java.io.Serializable;
import java.sql.Date;

public class OrderDTO implements Serializable{
	private int order_id;
	private int pnum;
	private int qty;
	private int member_id;
	private int total;
	private String size;
	private Date date;
	private String img;
	private String name;
	
	public OrderDTO(int order_id, int pnum, int qty, int member_id, int total, String size, Date date, String img,
			String name) {
		super();
		this.order_id = order_id;
		this.pnum = pnum;
		this.qty = qty;
		this.member_id = member_id;
		this.total = total;
		this.size = size;
		this.date = date;
		this.img = img;
		this.name = name;
	}

	public OrderDTO() {
		super();
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrderDTO [order_id=" + order_id + ", pnum=" + pnum + ", qty=" + qty + ", member_id=" + member_id
				+ ", total=" + total + ", size=" + size + ", date=" + date + ", img=" + img + ", name=" + name + "]";
	}
	
	
}
