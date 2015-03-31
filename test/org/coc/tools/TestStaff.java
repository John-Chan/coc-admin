package org.coc.tools;

import org.coc.tools.shared.misc.StringUtil;

public class TestStaff {

	public static void main(String[] args) {
		/**
		 * e.g. cutFolatString("123.45678",2) => "123.45"
		 * e.g. cutFolatString("123.45",3) => "123.45"
		 * e.g. cutFolatString("123.456.78",2) => "123.456.78"
		 * e.g. cutFolatString("123.45.678",2) => "123.45.67" 
		 * @param floatString
		 * @param maxCharAfterDot
		 * @return
		 */
		
		String test="";
		int maxCharAfterDot=0;
		String result="";

		test="123.45678";
		maxCharAfterDot=2;
		result=StringUtil.cutDotString(test, maxCharAfterDot);
		System.out.println("test StringUtil.cutDotString:"+test+",maxCharAfterDot="+maxCharAfterDot+",result=" +result);
		
		test="123.45";
		maxCharAfterDot=3;
		result=StringUtil.cutDotString(test, maxCharAfterDot);
		System.out.println("test StringUtil.cutDotString:"+test+",maxCharAfterDot="+maxCharAfterDot+",result=" +result);
		
		test="123.456.78";
		maxCharAfterDot=2;
		result=StringUtil.cutDotString(test, maxCharAfterDot);
		System.out.println("test StringUtil.cutDotString:"+test+",maxCharAfterDot="+maxCharAfterDot+",result=" +result);
		
		test="123.45.678";
		maxCharAfterDot=2;
		result=StringUtil.cutDotString(test, maxCharAfterDot);
		System.out.println("test StringUtil.cutDotString:"+test+",maxCharAfterDot="+maxCharAfterDot+",result=" +result);
		
	}
	
}
