package org.coc.tools.client.misc;

import java.util.Date;

import org.coc.tools.shared.DateTimeConstants;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateTimeFmt {

	public static final	String	DATE_TIME_LONG=DateTimeConstants.DATE_TIME_LONG ;
	// time zone (RFC 822)	=> -0700
	public static final	String	DATE_TIME_LONG_GMT=DateTimeConstants.DATE_TIME_LONG_GMT ;
	public static final	String	DATE_ONLY=DateTimeConstants.DATE_ONLY ;
	public static final	String	TIME_ONLY=DateTimeConstants.TIME_ONLY ;
	
	/// yyyy-MM-dd HH:mm:ss
	public	static  DateTimeFormat FmtLong(){
		return DateTimeFormat.getFormat(DATE_TIME_LONG);
	}

	/// yyyy-MM-dd
	public	static  DateTimeFormat FmtDate(){
		return DateTimeFormat.getFormat(DATE_ONLY);
	}
	/// HH:mm:ss
	public	static  DateTimeFormat FmtTime(){
		return DateTimeFormat.getFormat(TIME_ONLY);
	}
	
	/// yyyy-MM-dd HH:mm:ss ZZ
	public	static  DateTimeFormat FmtLongGmt(){
		return DateTimeFormat.getFormat(DATE_TIME_LONG_GMT);
	}
	
	public static String getString(Date dt,DateTimeFormat fmt){
		return fmt.format(dt);
	}
	public static Date parseDate(String dateStr,DateTimeFormat fmt){
		return fmt.parse(dateStr);
	}
}
