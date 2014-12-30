package org.coc.tools.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.shared.model.WarResult;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

public class WarResultDao   extends ObjectifyDao<WarResult>{


	public WarResultDao(){
		//
	}
	public WarResultDao(Objectify ofy){
		super(ofy);
	}
	
	//  never reurn null
	public List<WarResult> getListByWarId(Long warId) {
		// List<Car> cars = ofy().load().type(Car.class).filter("year in",
		// yearList).list();
		List<WarResult> list = this.getOfy().load().type(WarResult.class)
				.filter("warId", warId).list();
		if (list == null) {
			return new ArrayList<WarResult>();
		}
		return list;
	}

	public List<Key<WarResult>> getKeyListByWarId(Long warId) {
		List<WarResult> list = getListByWarId(warId);
		return ObjectifyDao.getKeyList(WarResult.class, list);
	}

	public List<Ref<WarResult>> getRefListByWarId(Long warId) {
		List<WarResult> list = getListByWarId(warId);
		return ObjectifyDao.getRefList(WarResult.class, list);
	}

	public WarResult getOne(Long warId,Long clanId) {
		WarResult one = this.getOfy().load().type(WarResult.class).filter("warId", warId).filter("clanId", clanId).first().now();
		return one;
	}
}
