package org.coc.tools.server.dao;

import org.coc.tools.shared.model.CWIndex;

import com.googlecode.objectify.Objectify;


public class CWIndexDao  extends ObjectifyDao<CWIndex>{

	public CWIndexDao(){
		//
	}
	public CWIndexDao(Objectify ofy){
		super(ofy);
	}
}
