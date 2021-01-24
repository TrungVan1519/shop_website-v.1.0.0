package com.dauXanh.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	String startAt;
	String endAt;
	String description;
	String img;
	int cost;
	
	@ManyToMany
	@JoinTable(name = "sale_detail", 
		joinColumns = { @JoinColumn(name = "id_sale", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "id_product", referencedColumnName = "id") })
	Set<Product> products;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStartAt() {
		return startAt;
	}
	
	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}
	
	public String getEndAt() {
		return endAt;
	}
	
	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
