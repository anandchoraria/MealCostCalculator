package com.meal.cost.domain;

import java.util.List;
import java.util.Map;

public class Menu {

	private Map<String, Item> items;
	private List<ValueMeal> valueMeals;

	public Menu(Map<String, Item> items, List<ValueMeal> valueMeals) {
		this.items = items;
		this.valueMeals = valueMeals;
	}

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

	public List<ValueMeal> getValueMeals() {
		return valueMeals;
	}

	public void setValueMeals(List<ValueMeal> valueMeals) {
		this.valueMeals = valueMeals;
	}

}
