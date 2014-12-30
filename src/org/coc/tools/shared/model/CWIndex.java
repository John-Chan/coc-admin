package org.coc.tools.shared.model;

import java.io.Serializable;
import java.util.Date;

import org.coc.tools.client.misc.DateTimeUtility;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index = true)
public class CWIndex extends ObjectifyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8254557111856699504L;


	private int scope=15;
	private Date prepareDate=new Date();
	
	private Clan homeClan=new Clan();
	private Clan enemyClan=new Clan();

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

}
