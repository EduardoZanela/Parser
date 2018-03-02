package com.ef.helper;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CommandLineHelper {
	
	private CommandLineHelper(){
		
	}
	
	public static Options getOptions(){
		Options options = new Options();
    	
        Option accesslog = new Option("al", "accesslog", true, "access file with log to be stored on database");
        accesslog.setRequired(false);
        accesslog.setArgName("FILE PATH");
        
        Option startDate = new Option("sd", "startDate", true, "Filter Start date; yyyy-MM-dd HH:mm:ss.SSS");
        startDate.setRequired(true);
        
        Option duration = new Option("d", "duration", true, "The interval period to perform the search and block; daily or hourly");
        duration.setRequired(true);
        
        Option threshold = new Option("t", "threshold", true, "The maximun quantity of request that can be performed in given period");
        threshold.setRequired(true);
        
        options.addOption(accesslog);
        options.addOption(startDate);
        options.addOption(duration);
        options.addOption(threshold);
        
        return options;
	}
	
}
