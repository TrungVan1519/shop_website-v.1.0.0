package com.dauXanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_color")
public class ProductColor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String color;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ProductColor [id=" + id + ", color=" + color + "]";
	}
}
