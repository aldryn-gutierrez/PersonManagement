package com.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class InputValidationUtil {
	
	public List<String> errors = new ArrayList<String>();
	
	public void checkIfStringEmpty(Map<String, String> inputToCheck)
	{
		Iterator<Entry<String, String>> it = inputToCheck.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
	        
	        String fieldValue = pair.getValue();
	        String fieldName = pair.getKey();
	        if(
	        	fieldValue.equals(null) ||
				fieldValue.trim().equals("")
			){ 
				this.errors.add(fieldName+" must not be empty."); 
			}
	    }
		
	}
	
	public boolean containsError() 
	{
		return (this.errors.size() != 0);
	}
	
	public String errors()
	{
		String errorMessage = "";
		
		for (String error : this.errors) {
			errorMessage += error;
		}
		
		return errorMessage;
	}
}
