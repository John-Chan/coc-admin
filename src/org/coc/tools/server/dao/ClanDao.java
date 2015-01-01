package org.coc.tools.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.shared.model.Clan;

import com.google.gwt.core.shared.GWT;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
//import com.googlecode.objectify.cmd.Query;


public class ClanDao   extends ObjectifyDao<Clan>{

	public ClanDao(){
		//
	}
	public ClanDao(Objectify ofy){
		super(ofy);
	}
	
	// return null on not found
	public Clan	getClanByTag(String tag){
		Clan found=null;
		found= this.getOfy().load().type(Clan.class).filter("clanTag", tag).first().now();
		/*
		Query<Clan> q = this.getOfy().load().type(Clan.class).filter("clanTag", tag);
		// #YY980J
        List<Clan> results = q.list();
        if(results.size()>0)
        	found=results.get(0);
        */
        return found;
	}

	public List<Clan>	getClanByName(String name){
		List<Clan> list=this.getOfy().load().type(Clan.class).filter("clanName", name).list();
		if(list == null){
			return new ArrayList<Clan>();
		}
		return list;
	}
	public List<Clan>	getRegedClan(int maxResult){
		List<Clan> list=this.getOfy().load().type(Clan.class).filter("registered", Clan.REG_STATUS.REGED).limit(maxResult).list();
		if(list == null){
			return new ArrayList<Clan>();
		}
		return list;
	}
	
	// 
	// save 
	// if clan tag not exists,save as new
	// if clan tag  exists,retrieve old one
	@Override
	public Key<Clan>  save(Clan val) {
		printAll();
		Clan saved= getClanByTag(val.getClanTag());
		if(saved == null){
			return super.save(val);
		}else{
			val.copyFull(saved);
			return ObjectifyDao.getKey(Clan.class, val.getRowId());
			//return null;
		}
		
	}
	

	// update
	// if clan tag not exists,do noting and return null
	// if clan tag  exists,do update
	@Override
	public Key<Clan>  update(Clan val) {
		Clan saved= getClanByTag(val.getClanTag());
		if(saved != null){
			saved.copyWithoutId(val);
			return super.save(val);
		}else{
			return null;
		}

	}
	
	public Key<Clan>  saveOrUpdate(Clan val) {
		Clan saved= getClanByTag(val.getClanTag());
		if(saved != null){
			return update(val);
		}else{
			return save(val);
		}
	}
	
	private void	printAll(){

		List<Clan> all= super.getList(Clan.class, 200);
		if(all == null) return;
		
		for(Clan v:all){
			GWT.log(v.getClanTag() + "  "+v.getClanName());
		}
	}
}
