package org.coc.tools.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.shared.model.WarBaseOrder;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

public class WarBaseOrderDao   extends ObjectifyDao<WarBaseOrder>{

	public WarBaseOrderDao(){
		//
	}
	public WarBaseOrderDao(Objectify ofy){
		super(ofy);
	}
	/// never reurn null
	public List<WarBaseOrder>	getListByWarId(Long warId){
		//List<Car> cars = ofy().load().type(Car.class).filter("year in", yearList).list();
		List<WarBaseOrder> list=this.getOfy().load().type(WarBaseOrder.class).filter("warId", warId).list();
		if(list == null){
			return new ArrayList<WarBaseOrder>();
		}
		return list;
	}
	public List<Key<WarBaseOrder>>	getKeyListByWarId(Long warId){
		List<WarBaseOrder> list=getListByWarId(warId);
		return ObjectifyDao.getKeyList(WarBaseOrder.class, list);
	}
	public List<Ref<WarBaseOrder>>	getRefListByWarId(Long warId){
		List<WarBaseOrder> list=getListByWarId(warId);
		return ObjectifyDao.getRefList(WarBaseOrder.class, list);
	}	
}
