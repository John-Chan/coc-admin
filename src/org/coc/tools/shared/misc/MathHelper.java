package org.coc.tools.shared.misc;

public class MathHelper {


	/**
	 * get float value [0.00 ~ x.xx] for (member/denominator)
	 * @param member
	 * @param denominator
	 * @return
	 */
	public static final float getPercentF(int member,int denominator){
		if(denominator==0) return 0.00f;
		float percent = ((float) member)
				/ ((float) denominator);
		percent = (float) (Math.round(percent * 100)) / 100;
		return percent;
	}
	
	/**
	 * get String value [0.00 ~ x.xx] for (member/denominator)
	 * @param member
	 * @param denominator
	 * @return
	 */
	public static final String getPercentS(int member,int denominator){

		float percent = getPercentF(member,denominator);
		String strVal=Float.toString(percent);
		return StringUtil.cutDotString(strVal, 2);
	}
}
