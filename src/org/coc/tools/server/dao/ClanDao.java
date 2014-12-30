package org.coc.tools.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.shared.model.Clan;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;


public class ClanDao   extends ObjectifyDao<Clan>{

	public ClanDao(){
		//
	}
	public ClanDao(Objectify ofy){
		super(ofy);
	}
	
	// return null on not found
	public Clan	getClanByTag(String tag){
		return this.getOfy().load().type(Clan.class).filter("clanTag", tag).first().now();
	}

	public List<Clan>	getClanByName(String name){
		List<Clan> list=this.getOfy().load().type(Clan.class).filter("clanTag", name).list();
		if(list == null){
			return new ArrayList<Clan>();
		}
		return list;
	}
	public List<Clan>	getRegedClan(){
		List<Clan> list=this.getOfy().load().type(Clan.class).filter("registered", Clan.REG_STATUS.REGED).list();
		if(list == null){
			return new ArrayList<Clan>();
		}
		return list;
	}
	
	// 
	// save ,if clan tag not exists,or igrone
	@Override
	public Key<Clan>  save(Clan val) {
		Clan saved= getClanByTag(val.getClanTag());
		if(saved == null){
			return super.save(val);
		}
		return ObjectifyDao.getKey(Clan.class, saved.getRowId());
	}
	
	// save - if clan tag not exists
	// update - if clan tag not exists
	@Override
	public Key<Clan>  update(Clan val) {
		Clan saved= getClanByTag(val.getClanTag());
		if(saved != null){
			saved.copyWithoutId(val);
		}

		return super.save(val);
	}
}
