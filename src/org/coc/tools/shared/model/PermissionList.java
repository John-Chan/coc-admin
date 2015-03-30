package org.coc.tools.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class PermissionList implements Serializable,ObjectifyEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6985291697615202441L;
	@Index
	private	String	key="";
	private String  value="";
	private String  describtion="";

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
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	
}
