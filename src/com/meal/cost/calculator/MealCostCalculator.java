package com.meal.cost.calculator;

import java.util.HashMap;
import java.util.Map;

import com.meal.cost.domain.Restaurant;
import com.meal.cost.services.CalculateCost;
import com.meal.cost.services.CsvReader;
import com.meal.cost.services.OrderedItemUtil;

public class MealCostCalculator {

	public static void main( String[] args) throws Exception{
		/* We should get two arguments as input -> DataInputFile In CSV Format and
		 *  Meal Items One wants to eat, each separated by comma.
		 */
		Map<Integer,Restaurant> restaurantList	= new HashMap<Integer, Restaurant>();
		String[] costRestArr 					= new String[2];


		if(args.length>=2 && args[0].endsWith(CostConstants.FILE_INPUT_TYPE)){ // For CSV File Execute this code
			try{
				//Reading Input Data File For Cost
				String csvFile = args[0];
				CsvReader csvReader = new CsvReader();
				csvReader.readCsvFile(restaurantList,csvFile);

				// Removing Extra Spaces From Order Item Array
				OrderedItemUtil orderArrayGenerator = new OrderedItemUtil();
				String[] itemsOrdered = orderArrayGenerator.orderItemGenerator(args);			

				//Calculating Cost of the Order
				CalculateCost calculateCost = new CalculateCost();
				costRestArr = calculateCost.calculateCost(itemsOrdered , restaurantList);

				// Cost Calculation Algo Complete
				float minPrice 			= Float.parseFloat(costRestArr[0]);
				int favourableRestId 	= Integer.parseInt(costRestArr[1]);

				System.out.print("*********************************************************** \n \" ");
				for(int j=0;j<itemsOrdered.length;j++){
					System.out.print(itemsOrdered[j] + " " );
				}
				if(minPrice == CostConstants.DEFAULT_MIN_PRICE || favourableRestId == CostConstants.DEFAULT_REST_ID)
					System.out.println("\" are Not Availbale in Any Restaurants.");
				else{
					System.out.println("\" will cost you - " + minPrice);
					System.out.println("You should visit Restaurant - " + favourableRestId + " for your meal.");
				}
				System.out.println("\n***********************************************************");

			}
			catch(Exception e){
				System.out.println("***********************************************************");
				System.out.println("Error While Processing Input File");
				System.out.println("***********************************************************");
			}
		}
		else{
			System.out.println("Input arguments are not in proper format.");
			System.out.println("e.g. of input format is-");
			System.out.println("*******************************************************");
			System.out.println("MealCostCalculator.java //Users//ACE//Basecamp//Workspace//JurgensvilleRestaurant//sample_data.csv pao_bhaji,chole");
			System.out.println("Data Input File should be in CSV format and have ',' as delimiter");
			System.out.println("Meal Items should be comma separated");
			System.out.println("*******************************************************");
		}
	}

}
