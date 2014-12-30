package org.coc.tools.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.shared.model.WarDetail;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

public class WarDetailDao extends ObjectifyDao<WarDetail> {

	public WarDetailDao(){
		//
	}
	public WarDetailDao(Objectify ofy){
		super(ofy);
	}
	
	// / never reurn null
	public List<WarDetail> getListByWarId(Long warId) {
		// List<Car> cars = ofy().load().type(Car.class).filter("year in",
		// yearList).list();
		List<WarDetail> list = this.getOfy().load().type(WarDetail.class)
				.filter("warId", warId).list();
		if (list == null) {
			return new ArrayList<WarDetail>();
		}
		return list;
	}

	public List<Key<WarDetail>> getKeyListByWarId(Long warId) {
		List<WarDetail> list = getListByWarId(warId);
		return ObjectifyDao.getKeyList(WarDetail.class, list);
	}

	public List<Ref<WarDetail>> getRefListByWarId(Long warId) {
		List<WarDetail> list = getListByWarId(warId);
		return ObjectifyDao.getRefList(WarDetail.class, list);
	}
	public WarDetail getOne(Long warId,Long attackerClanId) {
		WarDetail one = this.getOfy().load().type(WarDetail.class).filter("warId", warId).filter("attackerClanId", attackerClanId).first().now();
		return one;
	}
}
