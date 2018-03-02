package com.ef.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ef.entity.Access;

public class FileHelper {
	
	private FileHelper(){
		
	}
	
	public static List<Access> composeListbyLogFile(String accessLog) throws IOException, ParseException{
		List<Access> accessList = new ArrayList<>();
    	
    	FileReader fr = new FileReader(accessLog);
    	BufferedReader br = new BufferedReader(fr);
    	String nextLine;
    	
    	while((nextLine = br.readLine()) != null){
    		String[] accessArray = nextLine.split("\\|");
    		accessList.add(new Access(accessArray[0], DateHelper.getCalendarDateByString(accessArray[0]), accessArray[1], accessArray[2], accessArray[3], accessArray[4]));
    	}
    	br.close();
    	
    	return accessList;
	}
}
