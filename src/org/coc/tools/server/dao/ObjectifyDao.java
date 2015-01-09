package org.coc.tools.server.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


import org.coc.tools.shared.model.ObjectifyEntity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.cmd.Query;

public class ObjectifyDao<EntityType /* extends ObjectifyEntity */> {

	private Objectify ofy = null;

	// ////////////////////////////////////////////////////////////////////////////////////

	public static <E extends ObjectifyEntity> Key<E> getKey(Class<E> clazz,
			Long id) {
		return Key.create(clazz, id);
	}

	// / never return null
	public static <E extends ObjectifyEntity> List<Key<E>> getKeyList(
			Class<E> clazz, List<E> list) {
		List<Key<E>> keys = new ArrayList<Key<E>>();
		for (E val : list) {
			keys.add(ObjectifyDao.getKey(clazz, val.getRowId()));
		}
		return keys;
	}

	public static <E extends ObjectifyEntity> Ref<E> getRef(Key<E> key) {
		return Ref.create(key);
	}

	public static <E extends ObjectifyEntity> Ref<E> getRef(Class<E> clazz,
			Long id) {
		return Ref.create(ObjectifyDao.getKey(clazz, id));
	}

	// / never return null
	public static <E extends ObjectifyEntity> List<Ref<E>> getRefList(
			Class<E> clazz, List<E> list) {
		List<Ref<E>> refs = new ArrayList<Ref<E>>();
		for (E val : list) {
			// Ref<E> ref=Ref.create(ObjectifyDao.getKey(clazz,
			// val.getRowId()));
			refs.add(getRef(clazz, val.getRowId()));
		}
		return refs;
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	public ObjectifyDao() {
		// this.ofy = MyOfyService.ofy();
	}

	public ObjectifyDao(Objectify ofy) {
		this.ofy = ofy;
	}

	public Objectify getOfy() {
		return ofy;
	}

	public void setOfy(Objectify ofy) {
		this.ofy = ofy;
	}

	public Key<EntityType> save(EntityType val) {
		return ofy.save().entity(val).now();
	}

	public Map<Key<EntityType>, EntityType> save(Collection<EntityType> more) {
		return ofy.save().entities(more).now();
	}

	public void delete(EntityType val) {
		ofy.delete().entity(val);

	}

	public void delete(Collection<EntityType> more) {
		ofy.delete().entities(more);

	}

	// / equal to save currently
	public Key<EntityType> update(EntityType val) {
		return save(val);
	}

	// / equal to save currently
	public Map<Key<EntityType>, EntityType> update(Collection<EntityType> more) {
		// return ofy.save().entities(more).now();
		return save(more);
	}

	// / maxResult can be 0
	public List<EntityType> getList(Class<EntityType> clazz, int maxResult) {
		if (maxResult == 0) {
			return ofy.load().type(clazz).list();
		} else {
			return ofy.load().type(clazz).limit(maxResult).list();
		}

	}

	// / maxResult can be 0
	// / orders can be null or empty
	public List<EntityType> getList(Class<EntityType> clazz,
			List<String> orders, int maxResult) {
		
		Query<EntityType> qry = startQry(clazz);
		if(qry == null ){
			return new ArrayList<EntityType>();
		}
		if (orders != null && orders.size() > 0) {
			for (String exp : orders) {
				qry.order(exp);
			}
		}
		if (maxResult == 0) {
			return qry.list();
		} else {
			return qry.limit(maxResult).list();
		}

	}
	public Query<EntityType>	startQry(Class<EntityType> clazz){
		return  ofy.load().type(clazz);
	}

	public EntityType loadById(Class<EntityType> clazz, Long id) {
		return ofy.load().type(clazz).id(id).now();
	}

}
