package org.coc.tools.server.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Key;
//import com.googlecode.objectify.ObjectifyService;

import static org.coc.tools.server.MyOfyService.ofy;

public class SimpleDao {

	public static <EntityType extends Serializable> Key<EntityType>  add(EntityType val) {
		return ofy().save().entity(val).now();
	}

	public static <EntityType extends Serializable> Map<Key<EntityType>,EntityType> add(
			Collection<EntityType> more) {
		return ofy().save().entities(more).now();
	}

	public static <EntityType extends Serializable> void remove(EntityType val) {
		ofy().delete().entity(val);

	}

	public static <EntityType extends Serializable> void remove(
			Collection<EntityType> more) {
		ofy().delete().entities(more);

	}

	public static <EntityType extends Serializable> Key<EntityType> update(EntityType val) {
		return ofy().save().entity(val).now();

	}

	public static <EntityType extends Serializable> Map<Key<EntityType>,EntityType> update(
			Collection<EntityType> more) {
		return ofy().save().entities(more).now();

	}

	public static <EntityType extends Serializable> List<EntityType> query(
			Class<EntityType> clazz, int maxResult) {
		return ofy().load().type(clazz).limit(maxResult).list();

	}

	public static <EntityType extends Serializable> EntityType loadById(Class<EntityType> clazz,Long id) {
		return ofy().load().type(clazz).id(id).now();
	}
}
