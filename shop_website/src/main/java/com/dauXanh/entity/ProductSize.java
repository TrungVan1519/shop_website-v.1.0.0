package com.dauXanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_size")
public class ProductSize {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String size;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ProductSize [id=" + id + ", size=" + size + "]";
	}
}
