package org.coc.tools.server.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//import com.google.gwt.i18n.client.DateTimeFormat;
//import com.google.gwt.i18n.client.TimeZone;

public class DateTimeHelper {
public static final String	SIMPLE_DATE_FMT_STRING="yyyy-MM-dd";
public static final String	SIMPLE_TIME_FMT_STRING="HH:mm:ss";
public static final String	SIMPLE_DATE_TIME_FMT_STRING="yyyy-MM-dd HH:mm:ss";
public static final String	GMT_FMT_STRING="yyyy-MM-dd HH:mm:ss";

public	static Date	parse(String dtStr,String fmt){
	SimpleDateFormat formatter = new SimpleDateFormat(fmt);
	try {
		Date date = formatter.parse(dtStr);
		return date;
	} catch (ParseException e) {
		e.printStackTrace();
		return null;
	}
}

public	static Date	parseFromSimpleDateStr(String dtStr){
	return parse(dtStr,SIMPLE_DATE_FMT_STRING);
}

public	static Date	parseFromSimpleDateTimeStr(String dtStr){
	return parse(dtStr,SIMPLE_DATE_TIME_FMT_STRING);
}

public static long	getTimestamp(Date dt){
	return dt.getTime();
}
public static Date	fromTimestamp(long n){
	return new Date(n);
}
public	static Date	addYear(Date dt,int n){
	Calendar c = Calendar.getInstance(); 
	c.setTime(dt); 
	c.add(Calendar.YEAR, n);
	return c.getTime();
}

public	static Date	addMonth(Date dt,int n){
	Calendar c = Calendar.getInstance(); 
	c.setTime(dt); 
	c.add(Calendar.MONTH, n);
	return c.getTime();
}
public	static Date	addDay(Date dt,int n){
	Calendar c = Calendar.getInstance(); 
	c.setTime(dt); 
	c.add(Calendar.DATE, n);
	return c.getTime();
}
public	static Date	addHour(Date dt,int n){
	Calendar c = Calendar.getInstance(); 
	c.setTime(dt); 
	c.add(Calendar.HOUR, n);
	return c.getTime();
}
public	static Date	addMimute(Date dt,int n){
	Calendar c = Calendar.getInstance(); 
	c.setTime(dt); 
	c.add(Calendar.MINUTE, n);
	return c.getTime();
}
public	static Date	addSecond(Date dt,int n){
	Calendar c = Calendar.getInstance(); 
	c.setTime(dt); 
	c.add(Calendar.SECOND, n);
	return c.getTime();
}
public	static String toGmtString(Date date){
    SimpleDateFormat sd = new SimpleDateFormat(SIMPLE_DATE_TIME_FMT_STRING);
    sd.setTimeZone(TimeZone.getTimeZone("GMT"));
    return sd.format(date);
}
public	static String toString(Date date,String fmt){
    SimpleDateFormat sd = new SimpleDateFormat(fmt);
    return sd.format(date);
}
/*public	static Date getUtcTimeNow(String fmt){
	Date date = new Date();
	DateTimeFormat dtf = DateTimeFormat.getFormat(fmt);
	dtf.format(date, TimeZone.);
	return date;
}*/
}
