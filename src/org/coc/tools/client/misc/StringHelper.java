package org.coc.tools.client.misc;

public class StringHelper {
	public static String	CocNsapString(float nspa){
		float val = (float) (Math.round(nspa * 100)) / 100;
		String strVal=Float.toString(val);
		if(strVal.length()>4){
			strVal=strVal.substring(0, 4);
		}
		return strVal;
	}
}
