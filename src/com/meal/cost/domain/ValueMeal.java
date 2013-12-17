package com.meal.cost.domain;

import java.util.List;

public class ValueMeal {
	private List<String> mealItems;
	private Float price;

	public ValueMeal(List<String> mealItems, float price) {
		this.mealItems = mealItems;
		this.price = price;
	}

	public List<String> getMealItems() {
		return mealItems;
	}

	public void setMealItems(List<String> mealItems) {
		this.mealItems = mealItems;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}
