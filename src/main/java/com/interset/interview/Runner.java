package com.interset.interview;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.lang.Enum;
import org.apache.commons.io.FilenameUtils;



public class Runner {

    /**
     * This is main method which is starting point for this application.
     * It requires 1 arguments to run successfully.
     *
     * @param: args[0] : Path to JSON or CSV file to read.
     *
     * The JSON and CSV files must contain the following fields:
     *  name, siblings, favourite_food, birth_timezone, birth_timestamp
     *
     * This application parses the files and prints out the following information:
     *       - Average number of siblings (round up)
     *       - Top 3 favourite foods
     *       - How many people were born in each month of the year (uses the month of each person's respective timezone of birth)
     *
     */
    public static void main(String args[]) {

        if (args.length != 1) {
            System.out.println("We currently only expect 1 argument! A path to a JSON or CSV file to read.");
            System.exit(1);
        }

		
		//getting the file path
		String filePath = args[0];
		
		//check file extension if CSV, JSON or something else
 	    String fileExt = checkExtension(filePath);
	    
		//check each return for each corresponding method
		if (fileExt.equals("csv"))
		{
		  	System.out.println("This is a csv file!\n");
			
			Parsercsv.getAvgSiblings(filePath);
			Parsercsv.getFavFood(filePath, 3);
			Parsercsv.getMonthBirth(filePath);
			
			
			
		}
		else if (fileExt.equals("json"))
	    {
			System.out.println("This is a json file!\n");
			
			
			Parserjson.getAvgSiblings(filePath);
			Parserjson.getFavFood(filePath,3);
			Parserjson.getMonthBirth(filePath);
		}
		else if (fileExt.equals("csvgz"))
	    {
			//assuming new path
			String newPath = "/root/project/coding-challenge/src/main/resources/population_large_new.csv";
			
			System.out.println("This is a CSV-GZ file!");
			System.out.println("Extracted location is: "+newPath+"\n");
			
			File myNewFile = new File(newPath);
			// if file already exists will do nothing
			try {
				// if already exists will do nothing
				myNewFile.createNewFile(); 
			}
			catch (Exception e) {
                  e.printStackTrace();
            }
			
			//call method and assign and assign the extracted file for parsing 
			filePath = gunzip(filePath, newPath);
			
			Parsercsv.getAvgSiblings(filePath);
			Parsercsv.getFavFood(filePath,3);
			Parsercsv.getMonthBirth(filePath);
		}
		else if (fileExt.equals("jsongz"))
	    {
			String newPath = "/root/project/coding-challenge/src/main/resources/population_large_new.json";
			
			System.out.println("This is a JSON-GZ file!");
			System.out.println("Extracted location is: "+newPath+"\n");
			
			File myNewFile = new File(newPath);
			
			try {
				// if already exists will do nothing
				myNewFile.createNewFile(); 
			}
			catch (Exception e) {
                  e.printStackTrace();
            }
			
			//call method and assign and assign the extracted file for parsing 
			filePath = gunzip(filePath, newPath);
			
			Parserjson.getAvgSiblings(filePath);
			Parserjson.getFavFood(filePath,3);
			Parserjson.getMonthBirth(filePath);
		}
		else 
		{
			System.out.println("UNKNOWN file extension!");
			System.exit(1);			
		}
		
    }
	
	/**
   * This method is used to check to check the file extension provided in the path
   * and return the proper string to let program proceed accordingly.
   * the required parameters to class Analyzer avg. calculate method.
   * 
   *
   * @param filePath This is the CSV/JSON/Other file to be parsed
   * @return String Will return the file extension
   */ 
   
	public static String checkExtension(String filePath) {

			//conditions will check if file extension is CSV, JSON or something else 
			if (FilenameUtils.getExtension(filePath.toLowerCase()).equals("csv")) {
				return "csv";
			}
			else if (FilenameUtils.getExtension(filePath.toLowerCase()).equals("json")) {
				return "json";
			}
			else if (filePath.toLowerCase().contains("csv".toLowerCase()) && filePath.toLowerCase().contains("gz".toLowerCase())) {
				return "csvgz";
			}
		    else if (filePath.toLowerCase().contains("json".toLowerCase()) && filePath.toLowerCase().contains("gz".toLowerCase())) {
				return "jsongz";
			}
			else {
				return "unknown";
			}
	}
	
	/**
   * This method is used to handle the gzip files. I will extract the data to 
   * to a new location specified above in the code "newPath"
   *
   * 
   *
   * @param gzFile This is the gzipped file
   * @param fileNew This is the new location and file of extracted data.
   * @return String Will return the file new location
   */ 
   
	public static String gunzip(String gzFile, String fileNew)
	{
        //keep track of the buffer 
		int bfCount = 0;
		
		//standard buffer size to load file contents
		byte[] buffer = new byte[1024];
		
        try {
				//input
				FileInputStream myInputStream = new FileInputStream(gzFile);
				
				//stream 
				GZIPInputStream myOutStream = new GZIPInputStream(myInputStream);
		        
				//output
				FileOutputStream myFileNew = new FileOutputStream(fileNew);
				
				while((bfCount = myOutStream.read(buffer)) != -1){
					myFileNew.write(buffer, 0, bfCount);
				}
				
            //close resources
            myFileNew.close();
            myOutStream.close();
        } 
		
		catch (IOException e) {
            e.printStackTrace();
        }
		
		return fileNew;
	}
	
}
