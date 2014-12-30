package org.coc.tools.shared;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

//import java.util.Calendar;

public class TimeZoneHelper {

	public static class ClientSide{
		public enum TIMEZONE_TYPE{
			TZ_OFFSET, //like "420"
			TZ_RFC822, //like "-0700"
			TZ_SHORT,  //like "GMT-07:00"
			TZ_LONG,   //like "March 22, 2008 5:39:22 PM GMT-07:00"
			TZ_NAME    //like "GMT-07:00";
		}
		
		/**
		 * get client's time zone
		 * @param type
		 * @return
		 */
		@SuppressWarnings("deprecation")
		public static  String getTimeZone(TIMEZONE_TYPE type) {
		        Date today = new Date();
		        String timezone = null;
		        switch (type) {
		                case TZ_OFFSET: // date timeZoneMinOffset in minutes
		                        timezone = Integer.toString(today.getTimezoneOffset()); //like "420"
		                        break;
		                case TZ_RFC822: // time zone RFC822
		                        timezone = DateTimeFormat.getFormat("Z").format(today); //like "-0700"
		                        break;
		                case TZ_SHORT: //time zone TextShort
		                        timezone = DateTimeFormat.getFormat("v").format(today); //like "GMT-07:00"
		                        break;
		                case TZ_NAME: //time zone Name 

		                        //THIS WONT COMPILE - WORKS IN DEBUGGER - this will not work on client side
		                        //TimeZone tz = TimeZone.getDefault();
		                        //timezone = tz.getID(); //like "America/Vancouver"
		                        timezone = DateTimeFormat.getFormat("z").format(today); //like "GMT-07:00";

		                        break;
		                case TZ_LONG: //timezone TextLong
		                        timezone = DateTimeFormat.getLongDateTimeFormat().format(today); //like "March 22, 2008 5:39:22 PM GMT-07:00"
		                        break;
		        }
		        return timezone;
		}
		
		public static int getTimeZoneOffset(){
			return Integer.parseInt(getTimeZone(TIMEZONE_TYPE.TZ_RFC822));
			
		}
		public static String getTimeZoneOffsetStr(){
			return getTimeZone(TIMEZONE_TYPE.TZ_RFC822);
		}
		
	}
}
