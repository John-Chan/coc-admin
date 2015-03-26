package org.coc.tools.server.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.coc.tools.shared.model.ClanWarEntryPojo;


public class InMemorySearch {
	public enum WARLOG_SEARCH_TYPE{
		CLAN_NAME_ONLY,
		CLAN_TAG_ONLY,
		CLAN_NAME_OR_TAG
	}
	public static boolean containsTxt(String src,String searchTxt,boolean igroneCharCase){
		if(igroneCharCase){
			return src.toUpperCase().contains(searchTxt.toUpperCase());
		}
		return src.contains(searchTxt);
	}
	public static List<ClanWarEntryPojo>	search(List<ClanWarEntryPojo> source,String txt,WARLOG_SEARCH_TYPE type){
		List<ClanWarEntryPojo> result=new ArrayList<ClanWarEntryPojo>();
		for(ClanWarEntryPojo one:source){
			boolean found_name=false;
			boolean found_tag=false;
			if(containsTxt(one.getWarIndex().getHomeClan().getClanName(),txt,true) || containsTxt(one.getWarIndex().getEnemyClan().getClanName(),txt,true)  ){
				found_name=true;
			}
			if(containsTxt(one.getWarIndex().getHomeClan().getClanTag(),txt,true) || containsTxt(one.getWarIndex().getEnemyClan().getClanTag(),txt,true)  ){
				found_tag=true;
			}
			if(WARLOG_SEARCH_TYPE.CLAN_NAME_ONLY == type && found_name){
				result.add(one);
				continue;
			}
			if(WARLOG_SEARCH_TYPE.CLAN_TAG_ONLY == type && found_tag){
				result.add(one);
				continue;
			}
			if(WARLOG_SEARCH_TYPE.CLAN_NAME_OR_TAG == type && (found_tag || found_name)){
				result.add(one);
				continue;
			}
		}
		return result;
		
	}
	public static List<ClanWarEntryPojo>	removeRepeatWarLog(List<ClanWarEntryPojo> source){
		Set<ClanWarEntryPojo> s= new TreeSet<ClanWarEntryPojo>(new Comparator<ClanWarEntryPojo>(){

	           @Override
		        public int compare(ClanWarEntryPojo  one, ClanWarEntryPojo  two)
		        {
		        	return one.getWarIndex().getRowId().compareTo(two.getWarIndex().getRowId());
		        }

	      });

	      s.addAll(source);
	      return new ArrayList<ClanWarEntryPojo>(s);
		//return source;
	}
	public static void	sortByWarId(List<ClanWarEntryPojo> source){
		Collections.sort(source, new Comparator<ClanWarEntryPojo>() {
	        @Override
	        public int compare(ClanWarEntryPojo  one, ClanWarEntryPojo  two)
	        {
	        	return one.getWarIndex().getRowId().compareTo(two.getWarIndex().getRowId());
	        }
	    });
	}
	public static void	sortByPrepareDate(List<ClanWarEntryPojo> source,boolean desc){

		Comparator<ClanWarEntryPojo> cmper=new Comparator<ClanWarEntryPojo>() {
	        @Override
	        public int compare(ClanWarEntryPojo  one, ClanWarEntryPojo  two)
	        {
	        	return one.getWarIndex().getPrepareDate().compareTo(two.getWarIndex().getPrepareDate());
	        	 
	        }
	    };
	    
	    /// GAE dont have java.util.Comparator.reversed
	    Comparator<ClanWarEntryPojo> cmper_desc=new Comparator<ClanWarEntryPojo>() {
	        @Override
	        public int compare(ClanWarEntryPojo  one, ClanWarEntryPojo  two)
	        {
	        	int ret=one.getWarIndex().getPrepareDate().compareTo(two.getWarIndex().getPrepareDate());
	        	return (ret!=0)?(-ret):ret;
	        	 
	        }
	    };
	    if(desc){
	    	Collections.sort(source, cmper_desc);
	    }else{
	    	Collections.sort(source, cmper);
	    }
				/*
		Collections.sort(source, new Comparator<ClanWarEntryPojo>() {
	        @Override
	        public int compare(ClanWarEntryPojo  one, ClanWarEntryPojo  two)
	        {
	        	int ret=one.getWarIndex().getPrepareDate().compareTo(two.getWarIndex().getPrepareDate());
	        	if(desc && ret!=0){
	    			Collections.sort(source,Collections.reverseOrder());
	    		}
	        	 
	        }
	    });*/
		
	}
}
