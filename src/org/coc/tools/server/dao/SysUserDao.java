package org.coc.tools.server.dao;

import org.coc.tools.shared.model.SysUser;

import com.googlecode.objectify.Objectify;

public class SysUserDao   extends ObjectifyDao<SysUser>{
	public SysUserDao(){
		//
	}
	public SysUserDao(Objectify ofy){
		super(ofy);
	}
}
