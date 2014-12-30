package org.coc.tools.shared;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateTimeFmt {

	public static final	String	DATE_TIME_LONG="yyyy-MM-dd HH:mm:ss";
	// time zone (RFC 822)	=> -0700
	public static final	String	DATE_TIME_LONG_GMT="yyyy-MM-dd HH:mm:ss ZZ";
	public static final	String	DATE_ONLY="yyyy-MM-dd";
	public static final	String	TIME_ONLY="HH:mm:ss";
	
	public	static  DateTimeFormat FmtLong(){
		return DateTimeFormat.getFormat(DATE_TIME_LONG);
	}

	public	static  DateTimeFormat FmtDate(){
		return DateTimeFormat.getFormat(DATE_ONLY);
	}
	public	static  DateTimeFormat FmtTime(){
		return DateTimeFormat.getFormat(TIME_ONLY);
	}
	public	static  DateTimeFormat FmtLongGmt(){
		return DateTimeFormat.getFormat(DATE_TIME_LONG_GMT);
	}
	
	public static String getString(Date dt,DateTimeFormat fmt){
		return fmt.format(dt);
	}
}
