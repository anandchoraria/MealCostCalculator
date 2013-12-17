package com.meal.cost.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import com.meal.cost.calculator.CostConstants;
import com.meal.cost.domain.Restaurant;
import com.meal.cost.domain.ValueMeal;

public class CostFinder {

	public String[] searchMultipleItems(String[] orderedItems, Map<Integer, Restaurant> restaurantList) {	

		int favourableRestId		= CostConstants.DEFAULT_REST_ID;
		float minPrice 				= CostConstants.DEFAULT_MIN_PRICE;
		String[] costRestArr		= new String[2];
		float minPriceInItems 		= CostConstants.DEFAULT_MIN_PRICE;
		float minPriceInMeals 		= CostConstants.DEFAULT_MIN_PRICE;
		float minPriceInRest		= CostConstants.DEFAULT_MIN_PRICE;
		float minPartPriceInMeals 	= CostConstants.DEFAULT_MIN_PRICE;
		float minPriceInMeal 		= CostConstants.DEFAULT_MIN_PRICE;
		float minPriceInIndvItems 	= 0;

		//Iteration On restaurants
		for (Entry<Integer, Restaurant> entry : restaurantList.entrySet()){
			minPriceInItems 		= CostConstants.DEFAULT_MIN_PRICE;
			minPriceInMeals 		= CostConstants.DEFAULT_MIN_PRICE;
			minPriceInRest			= CostConstants.DEFAULT_MIN_PRICE;
			minPartPriceInMeals 	= CostConstants.DEFAULT_MIN_PRICE;
			minPriceInMeal 			= CostConstants.DEFAULT_MIN_PRICE;
			minPriceInIndvItems 	= 0;

			//*******************************************************
			//Checking for Prices Of Items In Meals
			//*******************************************************
			ListIterator<ValueMeal> mealsIter = entry.getValue().getMenu().getValueMeals().listIterator();

			while(mealsIter.hasNext()){
				ValueMeal meals = mealsIter.next();

				// Create a Duplicate List of Ordered Items
				List<String> duplicateOrderedItems = new ArrayList<String>();
				for(int k=0; k<orderedItems.length; k++){
					duplicateOrderedItems.add(orderedItems[k].toString().trim());
				}

				for(int k=0; k<orderedItems.length; k++){
					String eachItem = orderedItems[k].trim();

					if(meals.getMealItems().contains(eachItem)){
						// Item Found in Meal Remove from the duplicate list
						duplicateOrderedItems.remove(eachItem);
						//Remove all the other ordered items included in this Meal
						for(int l=k+1; l<orderedItems.length; l++){
							if(meals.getMealItems().contains(orderedItems[l].trim())){
								duplicateOrderedItems.remove(orderedItems[l].trim());
							}
						}				

						// Search for remaining ordered Items In Same Rest Recursively
						if(duplicateOrderedItems.size()>1){
							String[] restOfOrderedItems =  duplicateOrderedItems.toArray(new String[0]);
							Map<Integer, Restaurant> newRestaurantList = new HashMap<Integer, Restaurant>();
							newRestaurantList.put(entry.getKey(), entry.getValue());
							minPartPriceInMeals = meals.getPrice() + Float.parseFloat(searchMultipleItems(restOfOrderedItems,newRestaurantList)[0]);
						}
						else if(duplicateOrderedItems.size()==1){
							if(entry.getValue().getMenu().getItems().containsKey(duplicateOrderedItems.get(0))){
								minPartPriceInMeals = meals.getPrice() + entry.getValue().getMenu().getItems().get(duplicateOrderedItems.get(0)).getPrice();
							}
						}
						else if(duplicateOrderedItems.size()==0){
							minPartPriceInMeals = meals.getPrice();
						}
						break;
					}
				}
				// Among all the min. meals take the one with the min. price
				if(minPartPriceInMeals < minPriceInMeal){
					minPriceInMeal = minPartPriceInMeals;
				}
			}

			if(minPriceInMeal < minPriceInMeals){
				minPriceInMeals = minPriceInMeal;
			}

			//*******************************************************
			//Checking for Individual Prices Of Ordered Items In Rests.
			//*******************************************************
			boolean allItemsFound = true;
			for(int k=0; k<orderedItems.length; k++){
				String eachItem = orderedItems[k].trim();
				if(entry.getValue().getMenu().getItems().containsKey(eachItem)){
					minPriceInIndvItems = minPriceInIndvItems + entry.getValue().getMenu().getItems().get(eachItem).getPrice();
				}
				else{
					allItemsFound = false;
					break;
				}
			}

			if(allItemsFound){
				minPriceInItems = minPriceInIndvItems;
			}

			// Checking for Min Price Between Items In Meal or Ind. Items
			if(minPriceInItems < minPriceInMeals){
				minPriceInRest = minPriceInItems;
			}
			else{
				minPriceInRest = minPriceInMeals;
			}

			// Checking Min Price Among All Rests.
			if(minPriceInRest < minPrice){
				minPrice 			= minPriceInRest;
				favourableRestId 	= entry.getKey();
			}
		}

		// Retrun Results
		costRestArr[0] = Float.toString(minPrice);					// Calculated Price
		costRestArr[1] = Integer.toString(favourableRestId); 		// Favourable Restaurant

		return costRestArr;
	}
}
