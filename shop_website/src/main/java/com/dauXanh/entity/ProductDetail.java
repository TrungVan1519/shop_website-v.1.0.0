package com.dauXanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_detail")
public class ProductDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
	
	int qty;
	String createdAt;

	@OneToOne
	@JoinColumn(name = "id_product")
	Product product;

	@OneToOne
	@JoinColumn(name = "id_product_size")
	ProductSize productSize;

	@OneToOne
	@JoinColumn(name = "id_product_color")
	ProductColor productColor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public ProductColor getProductColor() {
		return productColor;
	}

	public void setProductColor(ProductColor productColor) {
		this.productColor = productColor;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", qty=" + qty + ", createdAt=" + createdAt + ", product=" + product.getId()
				+ ", productSize=" + productSize + ", productColor=" + productColor + "]";
	}
}
