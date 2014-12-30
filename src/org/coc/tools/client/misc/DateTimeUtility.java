package org.coc.tools.client.misc;

import java.util.Date;

import com.google.gwt.user.datepicker.client.CalendarUtil;

public class DateTimeUtility {

public static long	getTimestamp(Date dt){
	return dt.getTime();
}
public static Date	fromTimestamp(long n){
	return new Date(n);
}
public	static Date	addYear(final Date dt,int n){
	Date result= dt;
	CalendarUtil.addDaysToDate(result, n);
	return result;
}

public	static Date	addMonth(final Date dt,int n){
	Date result= CalendarUtil.copyDate(dt);
	CalendarUtil.addMonthsToDate(result, n);
	return result;
}
public	static Date	addDay(Date dt,int n){
	Date result= CalendarUtil.copyDate(dt);
	CalendarUtil.addDaysToDate(result, n);
	return result;
}
}
