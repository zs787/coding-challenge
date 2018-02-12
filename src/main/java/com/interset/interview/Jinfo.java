package com.interset.interview;

public class Jinfo {

   	/**
	* This class is used in the process  "from json" deserialization 
	* 
	*
	* @author  Ziad Shuwaikh
	* 
	*/
   
   
   
   
   private int siblings;
   private String favourite_food;
   private String birth_timestamp;
   private String birth_timezone;   


    
	public int getSiblings() {
        
		return siblings;
    }
	
	public String getFavFood() {
        
		return favourite_food;
    }
	
	public String getBirthTime() {
		
		return birth_timestamp.trim();
    }
	
	public String getBirthTimeZone() {
		
		return birth_timezone.trim();
    }
}