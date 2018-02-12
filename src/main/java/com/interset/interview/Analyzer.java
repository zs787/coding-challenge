package com.interset.interview;

import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Enum;
import java.lang.Object;
import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;

public class Analyzer {
	
	/**
	* This class has most of the common methods that can be used
	* between the process of parsing both types JSON and CSV.
	* 
	* The main purpose of this class is to make sure I reuse my code
	* as much as possible and make other classes focus more on parsing data.
	*
	* As per some of the references I was reading on performance (memory) it was suggested to have
	* most of my methods when possible as static to not keeping creating objects
	*
	*
	* @author  Ziad Shuwaikh
	* 
	*/
	
	
	
	static String[] months = new String[]{"Jan","Feb","Mar","Apr","May",
	                              "Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    public static void Analyzer() {
       
	   //empty constructor
	}
	
    /**
   * This method is used to calculate average.
   * Its a generic method than can calculate the avg. of any data
   * of type integer.
   * 
   *
   * @param sum This is sum of the actual data in the field
   * @param total This is the total number of the data occurrence
   * @return int Will return the avg rounded up
   */ 
    public static int getAvg(int sum, int total) {

		int avg = 0;
         
		//calculate avegarge vlaues and round up the total
		avg = (int) Math.round(((double)sum)/(total));
		
		return avg;
	}

	
	/**
   * This method is used to convert birth_timestamp from milliseconds to human readable.
   * Method will accept data in string type and covert the numbers accordingly.
   * Then will use the birth_timezone filed to adjust the date of birth 
   * 
   * 
   * @param datInMil This is the extracted data from birth_timestamp
   * @param datOffset This is the total number of the data occurrence
   * @return int This will return the date in a human readable formate
   */ 
	public static String convertDate(String datInMil, String datOffset) {
		
		// new date object with the time stamp in long type
		Date date = new Date(Long.valueOf(datInMil));
		//get the desired date formate 
		SimpleDateFormat myDate = new SimpleDateFormat("EEE, MMM d, ''yy");
		
		//parse out the offset numerical value 
		Integer offset = Integer.parseInt(datOffset.replaceAll(":",""));
		
		//set the offset to the above date to get the proper date with respect to timezone
		myDate.setTimeZone(TimeZone.getTimeZone("GMT "+offset));
		//finally format the date 
		String formattedDate = myDate.format(date);

		return formattedDate;
		
	}

	
	/**
   * This method will be used to check the occurrence of a month by
   * comparing the occurrence of the value passed against months array.
   * Then will update the track of occurrences array and return it.
   * 
   * 
   * @param BirthDate This is human readable data formate
   * @param trackOccurance This is the number of births corresponding to each month
   * @return Boolean Will return true or false 
   */
	public static int[] checkMonth(String BirthDate, int[] trackOccurance) {
		
        int i = 0;
		
		
		for (i=0; i < months.length; i++) {

            //if passed value exists in the month array then update occurrence
			if (BirthDate.contains(months[i])){

				trackOccurance[i] = trackOccurance[i]+1;
			}

		}
		
		return trackOccurance;
	}
	
	/**
   * This method will just print the months map with number of births 
   * in each month.
   * 
   * 
   * @param trackOccurance This is the number of births corresponding to each month
   * @return nothing
   */
	public static void printMonths(int[] trackOccurance) {
		
		int i=0;
		
		System.out.println("BIRTH MONTHS:");

		//print array contents
		for (i=0; i < months.length; i++) {

			System.out.println(months[i]+"("+trackOccurance[i]+")");
		}
		
		System.out.println("\n");
	}
	
	
	/**
   * This method is used to convert birth_timestamp from milliseconds to human readable.
   * Method will accept data in string type and covert the numbers accordingly.
   * Then will use the birth_timezone filed to adjust the date of birth 
   * 
   * 
   * @param foodmap This is map containing <food,occurrence>
   * @param favFood This is the string value compared against its occurrence
   * @return Map Will return updated key,value map
   */ 
	public static Map updateFavfood(Map<String, Integer> foodmap, String favFood) {
		
		//if passed food parametereter match the key then update its occurrence value
		if (foodmap.containsKey(favFood)) {
			//update value
			foodmap.put(favFood, foodmap.get(favFood) + 1);
			
		}
		else {
			
            //else it means its occurring for the first time, add it to to the map 		
			foodmap.put(favFood, 1);
		}
		
		return foodmap;
	}
	
	
	/**
   * This method is used to sort map <key, value>, based on the value of each key.
   * Method is used as part of getting the number of births per month
   * 
   * 
   * @param numOfCommom This top number of favourite foods to be displayed
   * @param map This is the map with <key = month,value = occurrence>
   * @return int This will return the sorted in the map
   */
    @SuppressWarnings("unchecked")
	public static Object[] sortMap(Map<String, Integer> map) {

		
		Object[] arry = map.entrySet().toArray();
		
		//start the sorting process
		Arrays.sort(arry, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				return ((Map.Entry<String, Integer>) obj2).getValue()
						   .compareTo(((Map.Entry<String, Integer>) obj1).getValue());
			}
		});

		return arry;
	}

	
	/**
   * This method is used to print the contents of the map
   * 
   * 
   * @param map This is the map with <key = month,value = occurrence>
   * @return method will return nothing
   */
    @SuppressWarnings("unchecked")
	public static void printMap(Object[] foods, int mostCommon) {
		
		int count = 0;
		
		System.out.println(mostCommon+" FAVOURITE FOODS:");

		//loop through the sorted hashMap and print key and corresponding value.
		for (Object foodObj : foods) {
			
			//this is a generic way in case we need the top 5 !
			if(count < mostCommon){
				System.out.println(((Map.Entry<String, Integer>) foodObj).getKey() + ": "
					+ ((Map.Entry<String, Integer>) foodObj).getValue());
			}
			count++;
		}
		
		System.out.println("\n");
		
	}
	

	
	

	
}

