package org.coc.tools.server.dao;

import java.util.List;

import org.coc.tools.shared.model.CWIndex;

import com.googlecode.objectify.Objectify;


public class CWIndexDao  extends ObjectifyDao<CWIndex>{

	public CWIndexDao(){
		//
	}
	public CWIndexDao(Objectify ofy){
		super(ofy);
	}
	
	public List<CWIndex> getListByHomeClanTag(String tag,int maxResult){
		return getOfy().load().type(CWIndex.class).filter("homeClan.clanTag", tag).limit(maxResult).list();
	}
}
