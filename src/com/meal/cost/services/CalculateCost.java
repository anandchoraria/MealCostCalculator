package com.meal.cost.services;

import java.util.Map;

import com.meal.cost.domain.Restaurant;

public class CalculateCost {

	public String[] calculateCost(String[] itemsOrdered, Map<Integer, Restaurant> restaurantList) {	
		
		CostFinder searchMultipleItems = new CostFinder();
		return(searchMultipleItems.searchMultipleItems(itemsOrdered,restaurantList));
	}
}
