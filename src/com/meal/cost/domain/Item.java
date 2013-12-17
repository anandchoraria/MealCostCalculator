package com.meal.cost.domain;

public class Item {

	private String itemName;
	private float price;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Item(String itemName, float price) {
		this.itemName = itemName;
		this.price = price;
	}

}