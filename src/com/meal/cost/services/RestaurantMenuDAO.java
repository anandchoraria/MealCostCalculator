package com.meal.cost.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meal.cost.domain.Item;
import com.meal.cost.domain.Menu;
import com.meal.cost.domain.Restaurant;
import com.meal.cost.domain.ValueMeal;

public class RestaurantMenuDAO {

	public void saveRestaurantMenus (int restId, float price, List<String> menuItems,
											int noOfItemsInMeal, Map<Integer, Restaurant> restaurantList){

		Map<String, Item> items 	= new HashMap<String, Item>();
		List<ValueMeal> valueMeals 	= new ArrayList<ValueMeal>();

		if(noOfItemsInMeal>1){
			valueMeals.add(new ValueMeal(menuItems,price));	

			if(restaurantList.containsKey(restId))
				restaurantList.get(restId).getMenu().getValueMeals().add(new ValueMeal(menuItems,price));
			else					
				restaurantList.put(restId,new Restaurant(restId,new Menu(items,valueMeals)));
		}
		else{
			items.put(menuItems.get(0), new Item(menuItems.get(0),price));

			if(restaurantList.containsKey(restId))
				restaurantList.get(restId).getMenu().getItems().put(menuItems.get(0), new Item(menuItems.get(0),price));
			else
				restaurantList.put(restId, new Restaurant(restId,new Menu(items,valueMeals)));
		}
	}
}
