package org.coc.tools.server.dao;

import java.util.List;

import org.coc.tools.shared.model.CWIndex;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.cmd.Query;


public class CWIndexDao  extends ObjectifyDao<CWIndex>{

	public CWIndexDao(){
		//
	}
	public CWIndexDao(Objectify ofy){
		super(ofy);
	}
	
	public List<CWIndex> getListByHomeClanTag(String tag,int maxResult){
		Query<CWIndex> qry=super.startQry(CWIndex.class);
		return qry.filter("homeClan.clanTag", tag).order("-prepareDate").limit(maxResult).list();
		//return getOfy().load().type(CWIndex.class).filter("homeClan.clanTag", tag).limit(maxResult).list();
	}
	
	public int	countByHomeClanTag(String tag){
		Query<CWIndex> qry=super.startQry(CWIndex.class);
		return qry.filter("homeClan.clanTag", tag).order("-prepareDate").count();
	}
}
