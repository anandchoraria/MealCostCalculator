package com.meal.cost.services;

import java.util.ArrayList;
import java.util.List;

public class OrderedItemUtil{

	public String[] orderItemGenerator(String[] args){
		
		// Taking all the items in order
		List<String> itemsOrderedList 	= new ArrayList<String>();
		for(int i=1;i<args.length;i++){
			if(!(args[i].isEmpty())){
				itemsOrderedList.add(args[i].trim().toLowerCase());
			}
		}
		String[] itemsOrdered = new String[itemsOrderedList.size()];
		itemsOrderedList.toArray( itemsOrdered );
		return itemsOrdered;
	}
	
}