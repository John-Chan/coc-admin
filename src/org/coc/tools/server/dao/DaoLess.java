package org.coc.tools.server.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Key;
//import com.googlecode.objectify.Objectify;

public class DaoLess<EntityType extends Serializable> {
	
	//private Objectify	ofy;
	
	/*public	Objectify	getOfy(){
		return ofy();
	}*/
	
	public  Key<EntityType>  add(EntityType val) {
		return SimpleDao.add(val);
	}

	public Map<Key<EntityType>,EntityType> add(
			Collection<EntityType> more) {
		return SimpleDao.add(more);
	}

	public void remove(EntityType val) {
		SimpleDao.remove(val);

	}

	public void remove(
			Collection<EntityType> more) {
		SimpleDao.remove(more);

	}

	public Key<EntityType> update(EntityType val) {
		return SimpleDao.update(val);

	}

	public Map<Key<EntityType>,EntityType> update(
			Collection<EntityType> more) {
		return SimpleDao.update(more);

	}

	public List<EntityType> query(
			Class<EntityType> clazz, int maxResult) {
		return SimpleDao.query(clazz, maxResult);

	}
	
	
}
