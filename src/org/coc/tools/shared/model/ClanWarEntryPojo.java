package org.coc.tools.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class ClanWarEntryPojo  extends ObjectifyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3698667659761620548L;

	private CWIndex warIndex=new CWIndex();
	private Clan homeClan=new Clan();
	private Clan enemyClan=new Clan();
	private WarResult homeClanWarResult=new WarResult();
	private WarResult enemyClanWarResult=new WarResult();
	private ArrayList<WarBaseOrder> warBaseOrders =new ArrayList<WarBaseOrder>();
	private ArrayList<WarDetail> warDetails =new ArrayList<WarDetail>();
	
	public CWIndex getWarIndex() {
		return warIndex;
	}
	public void setWarIndex(CWIndex warIndex) {
		this.warIndex = warIndex;
	}
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
	public ArrayList<WarBaseOrder> getWarBaseOrders() {
		return warBaseOrders;
	}
	public void setWarBaseOrders(ArrayList<WarBaseOrder> warBaseOrders) {
		this.warBaseOrders = warBaseOrders;
	}
	public ArrayList<WarDetail> getWarDetails() {
		return warDetails;
	}
	public void setWarDetails(ArrayList<WarDetail> warDetails) {
		this.warDetails = warDetails;
	}

	
	
	
}
