package org.coc.tools.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ClanWarEntryPojo   implements Serializable,ObjectifyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3698667659761620548L;

	@Id
	private Long	id;
	@Index
	private Date dataEditDate=new Date();
	private CWIndex warIndex=new CWIndex();
	//private Clan homeClan=new Clan();
	//private Clan enemyClan=new Clan();
	private WarResult homeClanWarResult=new WarResult();
	private WarResult enemyClanWarResult=new WarResult();
	private List<WarBaseOrder> warBaseOrders =new ArrayList<WarBaseOrder>();
	private List<WarDetail> warDetails =new ArrayList<WarDetail>();
	
	public CWIndex getWarIndex() {
		return warIndex;
	}
	public void setWarIndex(CWIndex warIndex) {
		this.warIndex = warIndex;
	}

	@Override
	public Long getRowId() {
		return this.id;
	}
	@Override
	public void setRowId(Long id) {
		this.id = id;
	}
	/*
	public Clan getHomeClan() {
		return homeClan;
	}
	public void setHomeClan(Clan homeClan) {
		this.homeClan = homeClan;
	}
	public Clan getEnemyClan() {
		return enemyClan;
	}
	public void setEnemyClan(Clan enemyClan) {
		this.enemyClan = enemyClan;
	}
	*/
	public WarResult getHomeClanWarResult() {
		return homeClanWarResult;
	}
	public void setHomeClanWarResult(WarResult homeClanWarResult) {
		this.homeClanWarResult = homeClanWarResult;
	}
	public WarResult getEnemyClanWarResult() {
		return enemyClanWarResult;
	}
	public void setEnemyClanWarResult(WarResult enemyClanWarResult) {
		this.enemyClanWarResult = enemyClanWarResult;
	}
	public List<WarBaseOrder> getWarBaseOrders() {
		return warBaseOrders;
	}
	public void setWarBaseOrders(List<WarBaseOrder> warBaseOrders) {
		this.warBaseOrders = warBaseOrders;
	}
	public List<WarDetail> getWarDetails() {
		return warDetails;
	}
	public void setWarDetails(List<WarDetail> warDetails) {
		this.warDetails = warDetails;
	}

	
	
	
}
