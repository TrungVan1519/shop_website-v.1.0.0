package com.dauXanh.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dauXanh.entity.embbeded.BillDetailId;

@Entity
@Table(name = "bill_detail")
public class BillDetail {

	@EmbeddedId
	BillDetailId id;
	
	int qty;
	String cost;
	
	public BillDetailId getId() {
		return id;
	}

	public void setId(BillDetailId id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public String getCost() {
		return cost;
	}
	
	public void setCost(String cost) {
		this.cost = cost;
	}
}
