package com.ef.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {
	
	private DateHelper(){
		
	}
	
	public static Date getCalendarDateByString(String date) throws ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.parse(date);
	}
	
	public static Date getCalendarDateByStringFormatCommandLine(String date) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
		
		return sdf.parse(date);
	}
	
	public static Date addDay(Date currentDate, Integer quantity){
		
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(quantity);

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date addHour(Date currentDate, Integer quantity){
		
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusHours(quantity);
        
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
}
