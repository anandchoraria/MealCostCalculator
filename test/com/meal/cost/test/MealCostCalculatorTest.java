package com.meal.cost.test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.meal.cost.domain.Restaurant;
import com.meal.cost.services.CalculateCost;
import com.meal.cost.services.CsvReader;
import com.meal.cost.services.OrderedItemUtil;

public class MealCostCalculatorTest {

	private static Map<Integer,Restaurant> restaurantList	= new HashMap<Integer, Restaurant>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		CsvReader csvReader = new CsvReader();
		csvReader.readCsvFile(restaurantList,"sample_data.csv");
	}

	@Test
	public void testForInvalidMenuItem() {
		CalculateCost calculateCost = new CalculateCost();

		String[] expectedValues 			= new String[10];
		OrderedItemUtil orderArrayGenerator = new OrderedItemUtil();
		String[] results 					= new String[3];
		String[] orderedItems;
		
		expectedValues[0] = "2.3";
		expectedValues[1] = "6.5";
		expectedValues[2] = "6.5";
	
		String[] orderItems0 	= {"","burger"};
		String[] orderItems1 	= {"","tofu_LOG"};
		String[] orderItems2 	= {"","TOFU_LOG"};

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems0);
		results[0] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems1);
		results[1] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems2);
		results[2] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		for(int i=0; i<3;i++){
			assertTrue(expectedValues[i].equals(results[i]));
		}
	}
	
	@Test
	public void testForMultipleItems() {	
		CalculateCost calculateCost = new CalculateCost();

		String[] expectedValues = new String[10];
		expectedValues[0] = "6.0";
		expectedValues[1] = "1.0E9";
		expectedValues[2] = "1.0E9";
		expectedValues[3] = "5.5";
		expectedValues[4] = "1.0E9";

		String[] orderItems0 	= {"","extreme_fajita", "jalapeno_poppers"};
		String[] orderItems1 	= {"","veg_salad, pasta"};
		String[] orderItems2 	= {"","pao_bhaji","WATER"," bUtter"};
		String[] orderItems3 	= {"","pao_bhaji"," chole"};
		String[] orderItems4 	= {"","WATER","", " bUtter"};

		OrderedItemUtil orderArrayGenerator = new OrderedItemUtil();
		String[] orderedItems;
		String[] results 		= new String[10];

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems0);
		results[0] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems1);
		results[1] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems2);
		results[2] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems3);
		results[3] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		orderedItems = orderArrayGenerator.orderItemGenerator(orderItems4);
		results[4] = calculateCost.calculateCost(orderedItems, restaurantList)[0];

		for(int i=0; i<5;i++){
			assertTrue(expectedValues[i].equals(results[i]));
		}
	}
}
