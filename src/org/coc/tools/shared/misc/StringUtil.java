package org.coc.tools.shared.misc;

public class StringUtil {


	/**
	 * e.g. cutFolatString("123.45678",2) => "123.45"
	 * e.g. cutFolatString("123.45",3) => "123.45"
	 * e.g. cutFolatString("123.456.78",2) => "123.456.78"
	 * e.g. cutFolatString("123.45.678",2) => "123.45.67" 
	 * @param floatString
	 * @param maxCharAfterDot
	 * @return
	 */
	public static String	cutDotString(String floatString,int maxCharAfterDot){
		int lastDot=floatString.lastIndexOf('.');
		if(-1 !=lastDot ){
			// found '.'
			String head=floatString.substring(0, lastDot);
			String back="";
			if(floatString.length() > lastDot+1){
				back=floatString.substring(lastDot+1, floatString.length() );
				if(back.length()>maxCharAfterDot){
					back=back.substring(0, maxCharAfterDot);
				}
			}
			return head+"."+back;
		}else{
			return floatString;
		}
	}
}
