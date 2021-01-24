package com.dauXanh.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "user_name")
	String userName;
	
	@Column(name = "phone_number")
	String phoneNumber;
	
	@Column(name = "ship_address")
	String shipAddress;
	
	boolean status;
	String createdAt;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id_bill")
//	Set<BillDetail> billDetails;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getShipAddress() {
		return shipAddress;
	}
	
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

//	public Set<BillDetail> getBillDetails() {
//		return billDetails;
//	}
//
//	public void setBillDetails(Set<BillDetail> billDetails) {
//		this.billDetails = billDetails;
//	}
}
