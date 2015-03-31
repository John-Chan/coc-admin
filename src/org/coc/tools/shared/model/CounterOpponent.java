package org.coc.tools.shared.model;

import java.util.ArrayList;
import java.util.List;

public class CounterOpponent {
	private	Clan	enemyClan=null;
	private List<ClanWarEntryPojo>		warHistoryList=new ArrayList<ClanWarEntryPojo>();
	public Clan getEnemyClan() {
		return enemyClan;
	}
	public List<ClanWarEntryPojo> getWarHistoryList() {
		return warHistoryList;
	}
	public void setEnemyClan(Clan enemyClan) {
		this.enemyClan = enemyClan;
	}
	public void setWarHistoryList(List<ClanWarEntryPojo> warHistoryList) {
		this.warHistoryList = warHistoryList;
	}
	
	
}
