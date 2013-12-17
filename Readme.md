PROBLEM STATEMENT 
===========
Jurgensville Restaurant Problem -


Because it is the Internet Age, but also it is a recession, the Comptroller
of the town of Jurgensville has decided to publish the prices of every item
on every menu of every restaurant in town, all in a single CSV file
(Jurgensville is not quite up to date with modern data serialization
methods). 

In addition, the restaurants of Jurgensville also offer Value
Meals, which are groups of several items, at a discounted price.  The
Comptroller has also included these Value Meals in the file.  The file's
format is:
```sh
for lines that define a price for a single item:
restaurant ID, price, item label
```
```sh
for lines that define the price for a Value Meal (there can be any number of
items in a value meal)
restaurant ID, price, item 1 label, item 2 label, ...
```


All restaurant IDs are integers, all item labels are lower case letters and
underscores, and the price is a decimal number.
 
 
Because you are an expert software engineer, you decide to write a program
that accepts the town's price file, and a list of item labels that someone
wants to eat for dinner, and outputs the restaurant they should go to, and
the total price it will cost them.  It is okay to purchase extra items, as
long as the total cost is minimized.

Here are some sample data sets, program inputs, and the expected result:
 
----------------------------
Data File data.csv
```sh
1, 4.00, burger
1, 8.00, tofu_log
2, 5.00, burger
2, 6.50, tofu_log
``` 
Command To Run Program

```sh
program data.csv burger tofu_log
```
Expected Output
```sh
2, 11.5
``` 
---------------------------
 
 
----------------------------
Data File data.csv
```sh
3, 4.00, chef_salad
3, 8.00, steak_salad_sandwich
4, 5.00, steak_salad_sandwich
4, 2.50, wine_spritzer
``` 
Command To Run Program
```sh
program data.csv chef_salad wine_spritzer
```
Expected Output
```sh
nil (or null or false or something to indicate that no matching
restaurant could be found)
```

Data File data.csv
```sh
5, 4.00, extreme_fajita
5, 8.00, fancy_european_water
6, 5.00, fancy_european_water
6, 6.00, extreme_fajita, jalapeno_poppers, extra_salsa
```

Program Input
```sh
program data.csv fancy_european_water extreme_fajita
```

Expected Output
```sh
6, 11.0
```
  
----------------------------

PROBLEM ASSUMPTIONS - 
---

- Prices - Float (Negative prices are allowed - They can be discounted price)
- Empty strings rows are discarded from data input.
- Assuming there are no same entries in each restaurant for same food item.
- Assuming entries in data are comma separated.
- Assuming Food Items in data are comma separated.
- Any Restaurant ID which is not an integer is skipped from the Input Data File.
- Any Item Price which is not a float is skipped from the Input Data File.

  
----------------------------

ALGORITHMIC APPROACH- 
---


- If 'i' is the no of items ordered then. And 'r' is the no of Restaurants.

- Algortihmically program searches for the minPrice among all the meals for the ordered items recursively. Worst case comarisions are 'i * r'.

- After meals , ordered items are searched in individual items in restaurant.
Worst Case Comparisions are 'i * r'.

- Min. of the two prices among meal and individual items are taken for a restaurant.
This minimum is comapred with the minimum among all restaurants, for answer.

  
----------------------------
INPUT REQUIREMENTS - 
---
- Data Input For Restaurant Price List is accepted through .csv file only.
- Input Data CSV File Should have ',' as separator.
- Ordered Items should be separated by space.
- Algorithm will disregard empty values or extra spaces in order items.
---------------------------
  
----------------------------
HOW TO RUN - 
---
- The program can be run using the jar file.
- Open CMD. Navigate to the folder where the jar file is.
- Use the following command to run the jar --
java -jar MealCostCalculator.jar *Input CSV File Complete Path*       *Order Items Seprated by space
   
```sh
e.g. - 
java -jar MealCostCalculator.jar C:\Users\Abc\Desktop\sample_data.csv item1,item2
```

