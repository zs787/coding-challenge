package com.interset.interview;


import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;

public class Parsercsv {
	
	/**
	* This class will parse and retrieve CSV data reading one line at a time.
	* 
	* Each method in this class will get the file from Runner and process it
	* according to the desired method called.
	*
	* As per some of the references I was reading on performance (memory) it was suggested to have
	* most of my methods when possible as static to not keeping creating objects
	*
	*
	* @author  Ziad Shuwaikh
	* 
	*/
	
    public void Parsercsv() {
		
		//empty constructor 
	}
	
	/**
   * This method is used to parse the cav data from file and pass 
   * the required parameters to class Analyzer avg. calculate method.
   * 
   *
   * @param filePath This is the CSV file to be parsed
   * @return nothing
   */ 
	
	public static void getAvgSiblings(String filePath) {
		
	    String csvFile = filePath;
        BufferedReader fHandler = null;
        String line = "";
        String SplitBy = ",";
		int sumSiblings = 0;
		int count = 0;
		   
		try {

           fHandler = new BufferedReader(new FileReader(csvFile));
			
            while ((line = fHandler.readLine()) != null) {

                // use comma as separator to split the line values and get each value in an array index 
                String[] mylist = line.split(SplitBy);
				
				if (count != 0) {
                
					//sum the number of siblings in the file for all people
					sumSiblings = sumSiblings + Integer.parseInt(mylist[2]);	
				}

				count++;
            }
			
			//get the average and display it
			System.out.println("Average Siblings: "+Analyzer.getAvg(sumSiblings,count-1)+"\n");
			      
        }
		
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		
		catch (IOException e) {
            e.printStackTrace();
        } 
		
		finally {
            if (fHandler != null) {
                try {
                    fHandler.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	   
	}
	
	/**
   * This method is used to parse the csv data from file and pass 
   * the required parameters to class Analyzer methods that will provide
   * the number of births each month of the year for the given set of timestamps.
   * 
   *
   * @param filePath This is the CSV file to be parsed
   * @return nothing
   */
   
	public static void getMonthBirth(String filePath) {
		
		String csvFile = filePath;
        BufferedReader fHandler = null;
        String line = "";
        String SplitBy = ",";
		
		int count= 0;
		int[] trackOccurance = new int[12];		
		
		try {
	    
           fHandler = new BufferedReader(new FileReader(csvFile));
			
            while ((line = fHandler.readLine()) != null) {

                // use comma as separator
                String[] mylist = line.split(SplitBy);
				
				if (count != 0){
					
					//convert my date from milliseconds to humane readable
					String dateFinal = Analyzer.convertDate(mylist[5],mylist[4]);
					
					//check the occurrence of each month and save it.
					trackOccurance = Analyzer.checkMonth(dateFinal,trackOccurance);	
				}
				
            count++;
			}//while	
			
			//call method to print results
			Analyzer.printMonths(trackOccurance);

		}
		
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		
		catch (IOException e) {
            e.printStackTrace();
        } 
		
		finally {
            if (fHandler != null) {
                try {
                    fHandler.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
			}
		}
	}
	
	/**
   * This method is used to parse the csv data from file and pass 
   * the required parameters to class Analyzer methods that will provide
   * the first top # of favourite foods.
   * 
   *
   * @param filePath This is the CSV file to be parsed
   * @param mostCommon This is the number of top # favourite foods
   * @return nothing
   */
	
	public static void getFavFood(String filePath, int mostCommon) {
		
		

		String csvFile = filePath;
        BufferedReader fHandler = null;
        String line = "";
        String SplitBy = ",";
		String favFood = "";
		
		int count= 0;
		
		int[] trackOccurance = new int[12];		
		
		try {
	    
           fHandler = new BufferedReader(new FileReader(csvFile));
		   Map<String, Integer> map = new HashMap<String, Integer>();
			
            while ((line = fHandler.readLine()) != null) {

                // use comma as separator
                String[] mylist = line.split(SplitBy);
				
				//some foods have upper/lower case characters so making sure this is valid for all 
				favFood = mylist[3].toLowerCase().trim();
				
				//check occurrence of food and update the occurrence value in the map				
				map = Analyzer.updateFavfood(map, favFood);
			
					
            count++;
			}//while	
			
			    //returned sorted map
				Object[] arry = Analyzer.sortMap(map);
				//print key value representing the occurrence s of each birth month
				Analyzer.printMap(arry, mostCommon);
		}
		
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		
		catch (IOException e) {
            e.printStackTrace();
        } 
		
		finally {
            if (fHandler != null) {
                try {
                    fHandler.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
			}
		}		
		
	}
}
