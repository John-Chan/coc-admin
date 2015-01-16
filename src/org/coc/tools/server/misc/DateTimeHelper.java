package org.coc.tools.server.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.coc.tools.shared.DateTimeConstants;

public class DateTimeHelper {
	public static final String DATE_TIME_LONG = DateTimeConstants.DATE_TIME_LONG;
	// time zone (RFC 822) => -0700
	public static final String DATE_TIME_LONG_GMT = DateTimeConstants.DATE_TIME_LONG_GMT;
	public static final String DATE_ONLY = DateTimeConstants.DATE_ONLY;
	public static final String TIME_ONLY = DateTimeConstants.TIME_ONLY;

	// / yyyy-MM-dd HH:mm:ss
	public static SimpleDateFormat FmtLong() {
		return new SimpleDateFormat(DATE_TIME_LONG);
	}

	// / yyyy-MM-dd
	public static SimpleDateFormat FmtDate() {
		return new SimpleDateFormat(DATE_ONLY);
	}

	// / HH:mm:ss
	public static SimpleDateFormat FmtTime() {
		return new SimpleDateFormat(TIME_ONLY);
	}

	// / yyyy-MM-dd HH:mm:ss ZZ
	public static SimpleDateFormat FmtLongGmt() {
		return new SimpleDateFormat(DATE_TIME_LONG_GMT);
	}

	public static String getString(Date dt, SimpleDateFormat fmt) {
		return fmt.format(dt);
	}

	public static Date parseDate(String dateStr, SimpleDateFormat fmt) {
		try {
			return fmt.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date addYear(Date dt, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.YEAR, n);
		return c.getTime();
	}

	public static Date addMonth(Date dt, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, n);
		return c.getTime();
	}

	public static Date addDay(Date dt, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}

	public static Date addHour(Date dt, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.HOUR, n);
		return c.getTime();
	}

	public static Date addMimute(Date dt, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MINUTE, n);
		return c.getTime();
	}

	public static Date addSecond(Date dt, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.SECOND, n);
		return c.getTime();
	}
}
