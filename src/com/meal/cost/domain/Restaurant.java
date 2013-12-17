package com.meal.cost.domain;

public class Restaurant {
	private int id;
	private Menu menu;

	public Restaurant(int id, Menu menu) {
		this.id = id;
		this.menu = menu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}