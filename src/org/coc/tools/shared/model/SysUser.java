package org.coc.tools.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class SysUser implements Serializable,ObjectifyEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8982224190091858076L;

	@Index
	private String	loginId="";
	@Index
	private String  displayName="";
	private String	password="";

	@Id
	private Long	id;
	@Index
	private Date dataEditDate=new Date();

	@Override
	public Long getRowId() {
		return this.id;
	}
	@Override
	public void setRowId(Long id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public String getPassword() {
		return password;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
