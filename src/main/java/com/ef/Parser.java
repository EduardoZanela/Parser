package com.ef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.hibernate.Session;

import com.ef.entity.Access;
import com.ef.entity.BlockedAccess;
import com.ef.helper.CommandLineHelper;
import com.ef.helper.DateHelper;
import com.ef.helper.FileHelper;
import com.ef.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class Parser {
    public static void main( String[] args ){
    	
    	// Maven compile
    	//mvn clean compile assembly:single
    	    	
    	List<Access> accessList = new ArrayList<>();
    	
    	HelpFormatter helpFormatter = new HelpFormatter();
    	CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine;
    	
    	Options options = CommandLineHelper.getOptions();
        
        try {
        	commandLine = commandLineParser.parse(options, args);
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	session.beginTransaction();
        	
        	if(commandLine.hasOption("accesslog")){
        		
	        	accessList = FileHelper.composeListbyLogFile(commandLine.getOptionValue("accesslog").toString());
	        	
	        	System.out.println("We are inserting the requests into the database sorry for your delay. Quatity of requests: " +accessList.size());
	        	
	        	int i = 0;
	        	
	        	for(Access access : accessList){
	    			session.merge(access);
	        	    if ( i % 20 == 0 ) { 
	        	        session.flush();
	        	        session.clear();
	        	    }
	        	    i++;
	        	}
	        	
	        	System.out.println("Finish");
	        	
        	} else {
        		accessList = (List<Access>)session.createQuery("from Access").list();
        	}
        	
        	session.getTransaction().commit();
        	
    		Date filterStartDate = DateHelper.getCalendarDateByStringFormatCommandLine(commandLine.getOptionValue("startDate"));
    		Date filterStartDateWithDuration = new Date();
        	
			if(commandLine.getOptionValue("duration").equalsIgnoreCase("daily")){
				filterStartDateWithDuration = DateHelper.addDay(filterStartDate, 1);
    		} else if(commandLine.getOptionValue("duration").equalsIgnoreCase("hourly")){
    			filterStartDateWithDuration = DateHelper.addHour(filterStartDate, 1);
    		}
    		
			final Date filterStartDateWithDurationStream = filterStartDateWithDuration;
			
        	Map<String, List<Access>> collect = 
        			accessList.stream()
        			.filter(q -> q.getAccessDate().after(filterStartDate) && q.getAccessDate().before(filterStartDateWithDurationStream))
        			.collect(Collectors.groupingBy(o -> o.getIp()))
        			.entrySet().stream().filter(q -> q.getValue().size() >= Long.parseLong(commandLine.getOptionValue("threshold")))
        			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        	session.beginTransaction();
        	
        	collect.forEach((k,v)-> {
        		System.out.println("IP : " + k + "\tNumber of requests at given time : " + v.size());
        		session.merge(new BlockedAccess(k, "exceed the maximum permited number of requests", filterStartDate, filterStartDateWithDurationStream, v.size()));
        	});
        	
        	session.getTransaction().commit();
        	session.close();
        	
        } catch (IOException | java.text.ParseException | org.apache.commons.cli.ParseException e) {
		   System.out.println(e.getMessage());
		   helpFormatter.printHelp("utility-name", options);
	
		   System.exit(1);
		   return;
		}
        
        System.exit(1);
    }
}
