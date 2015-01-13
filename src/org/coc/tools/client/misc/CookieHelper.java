package org.coc.tools.client.misc;

import java.util.Date;
import java.util.List;

import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.Cookies;
public class CookieHelper {
	public static final String CK_SELECTED_HOME_CLAN_TAG="selected-home-clan-tag";
	
	/// set and return old one
	/// return null if  value never saved 
	@SuppressWarnings("deprecation")
	public static void saveHomeClanTagForever(String tag){
		Date veryLong=new Date();
		veryLong.setYear(3000);
		Cookies.setCookie(CK_SELECTED_HOME_CLAN_TAG, tag, veryLong);
	
	}
	
	/// return current saved tag 
	public	static String ensureHomeClanTagSaved(String tag){
		String old=getHomeClanTag();
		if(old==null){
			saveHomeClanTagForever(tag);
		}
		return tag;
	}
	// return null if not exist
	public static String getHomeClanTag(){
		return Cookies.getCookie(CK_SELECTED_HOME_CLAN_TAG);
	}
	
	public static int tagExists(String tag,final List<Clan> list){
		int index=0;
		for(;index < list.size();++index){
			if(list.get(index).getClanTag().equals(tag))
				return index;
		}
		return -1;
	}
}
