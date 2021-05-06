package com.mcg.inventory;


public class Inventory {

	private Long invId;

	private int productId;

	private int quantity;

	private int threshold;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	
	public Long getInvId() {
		return invId;
	}

	public void setInvId(Long invId) {
		this.invId = invId;
	}

	@Override
	public String toString() {
		return "Inventory{invId=" + invId + ", productId=" + productId + ", quantity=" + quantity + ", threshold="
				+ threshold + "}";
	}
	
	

}
