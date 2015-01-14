package org.coc.tools.server.dao;

import org.coc.tools.shared.model.PermissionList;

import com.googlecode.objectify.Objectify;
public class PermissionListDao   extends ObjectifyDao<PermissionList>{
	public PermissionListDao(){
		//
	}
	public PermissionListDao(Objectify ofy){
		super(ofy);
	}
}
