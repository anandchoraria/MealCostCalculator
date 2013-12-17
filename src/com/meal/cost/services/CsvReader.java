package com.meal.cost.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.meal.cost.calculator.CostConstants;
import com.meal.cost.domain.Restaurant;

public class CsvReader {

	public void readCsvFile(Map<Integer,Restaurant> restaurantList,String csvFile) throws Exception{

		String line 		= "";
		BufferedReader br 	= null;

		try {
			br = new BufferedReader(new FileReader(csvFile));

			while((line = br.readLine())!= null){
				
				String[] row = line.split(CostConstants.CSV_SPLIT_ON);
				
				if(row.length>=3){
					int restId 				= Integer.parseInt(row[0].trim());
					float price 			= Float.parseFloat(row[1].trim());
					List<String> menuItems 	= new ArrayList<String>();
					int noOfItemsInMeal		= 0; 

					for(int i=2; i<row.length;i++){
						if(row[i].trim()!=null && !(row[i].trim().isEmpty())){
							menuItems.add(row[i].trim().toLowerCase());
							noOfItemsInMeal++;
						}
					}
					
					if(!((isInteger(row[0].trim()) && isFloat(row[1].trim())) && (noOfItemsInMeal>0)))
						continue;
					
					RestaurantMenuDAO saveRestaurantMenus = new RestaurantMenuDAO();
					saveRestaurantMenus.saveRestaurantMenus(restId,price,menuItems,noOfItemsInMeal,restaurantList);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			throw e;
		} catch (IOException e) {
			System.out.println("I/O File Exception Occurred");
			throw e;
		}
		br.close();
	}

	private boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		return true;
	}

	private boolean isFloat(String s) {
		try { 
			Float.parseFloat(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		return true;
	}
}
