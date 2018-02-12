package com.interset.interview;


import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.*;


public class Parserjson {


	/**
	* This class will parse and retirve JSON array data using gson, with the help
	* of the Jinfo class wich is use in the deserialization process
	* 
	* Each method in this class will get the file from Runner and process it
	* according to the desired method called.
	*
    *As per some of the references I was reading on performance (memory) it was suggested to have
	* most of my methods when possible as static to not keeping creating objects>
	*
	*
	* @author  Ziad Shuwaikh
	* 
	*/

    public static void ParserJson() {
		//empty constructor
	}
	
	
	/**
   * This method is used to parse the JSON array from file and pass 
   * the required parameters to class Analyzer avg. calculate method.
   * 
   *
   * @param filePath This is the JSON file to be parsed
   * @return nothing
   */ 
	
	public static void getAvgSiblings(String filePath) {
		
		//defining my variables and instances
		String jsonFile = filePath;
		BufferedReader fHandler = null;
		int sumSiblings = 0;
		int count = 0;
		int[] result = {sumSiblings,count};

        try {
			
                //reading the JSON file in stream mode
				InputStream stream = new FileInputStream(filePath);
				JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
				Gson gson = new Gson();

				// Read file in stream mode
				reader.beginArray();
				
				//keep looping until the file is done and no more streaming
				while (reader.hasNext()) {
					
					Jinfo jnfo = gson.fromJson(reader, Jinfo.class);

					sumSiblings = sumSiblings + jnfo.getSiblings();
					count++;
				}
				
			    //close resources
				reader.endArray();
				reader.close();
				
				//Display the returned value of the avg method
			    System.out.println("Average Siblings: "+Analyzer.getAvg(sumSiblings,count++)+"\n");
 
        } 
		
		catch (Exception e) {
            e.printStackTrace();
        }
	    
		
	}
	
	
	/**
   * This method is used to parse the JSON array from file and pass 
   * the required parameters to class Analyzer methods that will provide
   * the first top # of favourite foods.
   * 
   *
   * @param filePath This is the JSON file to be parsed
   * @param mostCommon This is the number of top # favourite foods
   * @return nothing
   */
	public static void getFavFood(String filePath, int mostCommon) {
	
	    //defining my variables and instances
		String jsonFile = filePath;
		BufferedReader fHandler = null;
		
		String favFood = "";

		try {
			
			    //reading the JSON file in stream mode
				InputStream stream = new FileInputStream(filePath);
				JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
				Gson gson = new Gson();
				
				//hashMap new obj
                Map<String, Integer> map = new HashMap<String, Integer>();
				
				// Read file in stream mode
				reader.beginArray();
				
				//keep looping until the file is done and no more streaming
				while (reader.hasNext()) {
					
					//call method in Jinfo to return the proper string/int value
					Jinfo jnfo = gson.fromJson(reader, Jinfo.class);
					favFood = (jnfo.getFavFood().toLowerCase().trim());
					
					//call method to check the occurrence of fav food in the map and update accordingly
					map = Analyzer.updateFavfood(map, favFood);
				
				}
				
			    //close resources
				reader.endArray();
				reader.close();
				
				//returned value of the sorted map
				Object[] arry = Analyzer.sortMap(map);
				//print the map/result
				Analyzer.printMap(arry, mostCommon);

		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	    
		
	}
	
	
	/**
   * This method is used to parse the JSON array from file and pass 
   * the required parameters to class Analyzer methods that will provide
   * the number of births each month of the year for the given set of timestamps.
   * 
   *
   * @param filePath This is the JSON file to be parsed
   * @return nothing
   */
	public static void getMonthBirth(String filePath) {
	
		String jsonFile = filePath;
		BufferedReader fHandler = null;
		int[] trackOccurance = new int[12];
		
		
			   
		try {
			
				InputStream stream = new FileInputStream(filePath);
				JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
				Gson gson = new Gson();

				// Read file in stream mode
				reader.beginArray();
				
				
				while (reader.hasNext()) {
					
					//call method in Jinfo to return the proper string/int value
					Jinfo jnfo = gson.fromJson(reader, Jinfo.class);
					String myOffset = jnfo.getBirthTimeZone();
					String myDate = jnfo.getBirthTime();
                    
					//convert my date from milliseconds to humane readable
					String dateFinal = Analyzer.convertDate(myDate,myOffset);
					
					//check the occurrence of each month and save it.
					trackOccurance = Analyzer.checkMonth(dateFinal,trackOccurance);	
					
				}
			    
				//close resources
				reader.endArray();
				reader.close();
	
	            //call method to print results
				Analyzer.printMonths(trackOccurance);

		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}	
	
}