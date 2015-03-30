package org.coc.tools.shared.model;

import java.io.Serializable;
import java.util.Date;

import org.coc.tools.client.misc.DateTimeUtility;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class CWIndex   implements Serializable,ObjectifyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8254557111856699504L;


	private int scope=15;
	@Index
	private Date prepareDate=new Date();
	@Index
	private Date dataEditDate=new Date();
	
	/*
	@Index
	private boolean warResultLocked=false;
	@Index
	private boolean warDetailLocked=false;
	@Index
	private boolean warBaseOrderLocked=false;
	*/
	private Clan homeClan=new Clan();
	private Clan enemyClan=new Clan();
	@Id
	private Long	id;

	@Override
	public Long getRowId() {
		return this.id;
	}
	@Override
	public void setRowId(Long id) {
		this.id = id;
	}
	public Clan getHomeClan() {
		return homeClan;
	}

	public void setHomeClan(Clan value) {
		homeClan=value;
	}

	public Clan getEnemyClan() {
		return enemyClan;
	}

	public void setEnemyClan(Clan value) {
		enemyClan=value;
	}

	

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public Date getPrepareDate() {
		return prepareDate;
	}

	public Date getEndDate() {
		return DateTimeUtility.addDay(prepareDate, 2);
	}

	public Date getWarDate() {
		return DateTimeUtility.addDay(prepareDate, 1);
	}

	public void setPrepareDate(Date prepareDate) {
		this.prepareDate = prepareDate;
	}
	
	/*
	public boolean isWarResultLocked() {
		return warResultLocked;
	}
	public boolean isWarDetailLocked() {
		return warDetailLocked;
	}
	public boolean isWarBaseOrderLocked() {
		return warBaseOrderLocked;
	}
	public void setWarResultLocked(boolean warResultLocked) {
		this.warResultLocked = warResultLocked;
	}
	public void setWarDetailLocked(boolean warDetailLocked) {
		this.warDetailLocked = warDetailLocked;
	}
	public void setWarBaseOrderLocked(boolean warBaseOrderLocked) {
		this.warBaseOrderLocked = warBaseOrderLocked;
	}
	*/

	
}
