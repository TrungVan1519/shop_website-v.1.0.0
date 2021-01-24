package com.dauXanh.dto;

public class ShoppingCartItemDTO {

	int productDetailId;
	int productId;
	int sizeId;
	int colorId;
	int qty;
	int cost;

	String productName;
	String size;
	String color;

	public ShoppingCartItemDTO(int productDetailId, int productId, int sizeId, int colorId, int qty, int cost,
			String productName, String size, String color) {
		this.productDetailId = productDetailId;
		this.productId = productId;
		this.sizeId = sizeId;
		this.colorId = colorId;
		this.qty = qty;
		this.cost = cost;
		this.productName = productName;
		this.size = size;
		this.color = color;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	
	public int getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(int productDetailId) {
		this.productDetailId = productDetailId;
	}

	@Override
	public String toString() {
		return "ShoppingCartItemDTO [productDetailId=" + productDetailId + ", productId=" + productId + ", sizeId="
				+ sizeId + ", colorId=" + colorId + ", qty=" + qty + ", cost=" + cost + ", productName=" + productName
				+ ", size=" + size + ", color=" + color + "]";
	}
}
