package com.meal.cost.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.meal.cost.domain.Restaurant;
import com.meal.cost.services.CsvReader;

public class ReaderTest {
	
	private static Map<Integer,Restaurant> restaurantList	= new HashMap<Integer, Restaurant>();
	private static CsvReader csvReader = new CsvReader();
	
	
	@Test
	public void testReader() throws Exception{

		assertTrue(restaurantList.isEmpty()==true);		
		csvReader.readCsvFile(restaurantList,"sample_data.csv");
		assertTrue(restaurantList.size()==11);
		}
}
