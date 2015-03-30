package org.coc.tools;

import org.coc.tools.shared.FieldVerifier;
import org.coc.tools.shared.VerifieStatus;

public class TestCheckClanTag {

	public static void main(String[] args) {
		VerifieStatus ret;

		String test="#123";
		ret=FieldVerifier.isValidClanTag(test);
		System.out.println("test:"+test+",result="+ret.getPassed()+"=>"+ret.getMsg());
		
		test="#12345是";
		ret=FieldVerifier.isValidClanTag(test);
		System.out.println("test:"+test+",result="+ret.getPassed()+"=>"+ret.getMsg());
		
		test="#12345";
		ret=FieldVerifier.isValidClanTag(test);
		System.out.println("test:"+test+",result="+ret.getPassed()+"=>"+ret.getMsg());
		
		test="#12o345";
		ret=FieldVerifier.isValidClanTag(test) ;
		System.out.println("test:"+test+",result="+ret.getPassed()+"=>"+ret.getMsg());
		
		test="#12345?";
		ret=FieldVerifier.isValidClanTag(test) ;
		System.out.println("test:"+test+",result="+ret.getPassed()+"=>"+ret.getMsg());
		
		test="#1234!5";
		ret=FieldVerifier.isValidClanTag(test) ;
		System.out.println("test:"+test+",result="+ret.getPassed()+"=>"+ret.getMsg());
		
		test="##12345";
		ret=FieldVerifier.isValidClanTag(test) ;
		System.out.println("test:"+test+",result="+ret.getPassed()+"=>"+ret.getMsg());
	}

}
