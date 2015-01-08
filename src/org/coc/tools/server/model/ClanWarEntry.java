package org.coc.tools.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ObjectifyEntity;
import org.coc.tools.shared.model.WarBaseOrder;
import org.coc.tools.shared.model.WarDetail;
import org.coc.tools.shared.model.WarResult;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
//@Subclass(index=true)
@Entity
public class ClanWarEntry  implements Serializable,ObjectifyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3768024952646365946L;
	//

	/// main index for a ClanWarEntry
	@Load
	private Ref<CWIndex> warIndex;
	
	/// Linked by:  warIndex.homeClan.
	@Load
	private Ref<Clan> homeClan;
	
	/// Linked by:  warIndex.homeClan.
	@Load
	private Ref<Clan> enemyClan;

	/// Linked by: WarResult.warId -> warIndex.rowId
	@Load
	private Ref<WarResult> homeClanWarResult;

	/// Linked by: WarResult.warId -> warIndex.rowId
	@Load
	private Ref<WarResult> enemyClanWarResult;

	/// Linked by: WarBaseOrder.warId -> warIndex.rowId
	@Index 
	private List<Ref<WarBaseOrder>> warBaseOrderList = new ArrayList<Ref<WarBaseOrder>>();

	/// Linked by: WarDetail.warId -> warIndex.rowId
	@Index 
	private List<Ref<WarDetail>> warDetailList = new ArrayList<Ref<WarDetail>>();
	
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
	
	public Ref<CWIndex> getWarIndex() {
		return warIndex;
	}
	public void setWarIndex(Ref<CWIndex> warIndex) {
		this.warIndex = warIndex;
	}
	public Ref<Clan> getHomeClan() {
		return homeClan;
	}
	public void setHomeClan(Ref<Clan> homeClan) {
		this.homeClan = homeClan;
	}
	public Ref<Clan> getEnemyClan() {
		return enemyClan;
	}
	public void setEnemyClan(Ref<Clan> enemyClan) {
		this.enemyClan = enemyClan;
	}
	public Ref<WarResult> getHomeClanWarResult() {
		return homeClanWarResult;
	}
	public void setHomeClanWarResult(Ref<WarResult> homeClanWarResult) {
		this.homeClanWarResult = homeClanWarResult;
	}
	public Ref<WarResult> getEnemyClanWarResult() {
		return enemyClanWarResult;
	}
	public void setEnemyClanWarResult(Ref<WarResult> enemyClanWarResult) {
		this.enemyClanWarResult = enemyClanWarResult;
	}
	public List<Ref<WarBaseOrder>> getWarBaseOrderList() {
		return warBaseOrderList;
	}
	public void setWarBaseOrderList(List<Ref<WarBaseOrder>> warBaseOrderList) {
		this.warBaseOrderList = warBaseOrderList;
	}
	public List<Ref<WarDetail>> getWarDetailList() {
		return warDetailList;
	}
	public void setWarDetailList(List<Ref<WarDetail>> warDetailList) {
		this.warDetailList = warDetailList;
	}
	/////////////////////////////////////////////
	
	public ClanWarEntry(){
		//
	}
	
	/*
	
	public  ClanWarEntryPojo	toPojo(){
		ClanWarEntryPojo pojo=new ClanWarEntryPojo();
		pojo.setWarIndex(warIndex.get());
		pojo.setHomeClan(homeClan.get());
		pojo.setEnemyClan(enemyClan.get());
		pojo.setHomeClanWarResult(homeClanWarResult.get());
		pojo.setEnemyClanWarResult(enemyClanWarResult.get());
		
		pojo.setWarBaseOrders(new ArrayList<WarBaseOrder>( getWarBaseOrderList()));
		pojo.setWarDetails( new ArrayList<WarDetail>(getWarDetailList()));
		
		return pojo;
	}
	
	public ClanWarEntry(ClanWarEntryPojo pojo){
		this.warIndex=Ref.create(pojo.getWarIndex());
		this.homeClan=Ref.create(pojo.getEnemyClan());
		this.enemyClan=Ref.create(pojo.getEnemyClan());
		this.homeClanWarResult=Ref.create(pojo.getHomeClanWarResult());
		this.enemyClanWarResult=Ref.create(pojo.getEnemyClanWarResult());
		setWarBaseOrderList(pojo.getWarBaseOrders());
		setWarDetailList(pojo.getWarDetails());
	}

	public List<WarBaseOrder> getWarBaseOrderList() {

	    List<WarBaseOrder> ret = new ArrayList<WarBaseOrder>();
	    Iterator<Ref<WarBaseOrder>> it = warBaseOrderList.iterator();
	    while (it.hasNext())
	        ret.add(it.next().getValue());
	    return ret;
	}
	public void addWarBaseOrder(WarBaseOrder value) {
		warBaseOrderList.add(Ref.create(value));
	}
	public void setWarBaseOrderList(List<WarBaseOrder> warBaseOrders) {

	    Iterator<WarBaseOrder> it = warBaseOrders.iterator();

	    while (it.hasNext())
	    	addWarBaseOrder(it.next());
	}
	public List<WarDetail> getWarDetailList() {

	    List<WarDetail> ret = new ArrayList<WarDetail>();
	    Iterator<Ref<WarDetail>> it = warDetailList.iterator();
	    while (it.hasNext())
	        ret.add(it.next().getValue());
	    return ret;
	}

	public void addWarDetail(WarDetail value) {
		warDetailList.add(Ref.create(value));
	}
	
	public void setWarDetailList(List<WarDetail> warDetails) {

	    Iterator<WarDetail> it = warDetails.iterator();

	    while (it.hasNext())
	    	addWarDetail(it.next());
	}
	

	public CWIndex getWarIndex() {
		return warIndex.get();
	}

	public void setWarIndex(CWIndex value) {
		warIndex = Ref.create(value);
	}
	
	public WarResult getHomeClanWarResult() {
		return homeClanWarResult.get();
	}

	public void setHomeClanWarResult(WarResult value) {
		homeClanWarResult = Ref.create(value);
	}
	public WarResult getEnemyClanWarResult() {
		return homeClanWarResult.get();
	}

	public void setEnemyClanWarResult(WarResult value) {
		homeClanWarResult = Ref.create(value);
	}
	
	public Clan getHomeClan() {
		return homeClan.get();
	}

	public void setHomeClan(Clan value) {
		homeClan = Ref.create(value);
	}

	public Clan getEnemyClan() {
		return enemyClan.get();
	}

	public void setEnemyClan(Clan value) {
		enemyClan = Ref.create(value);
	}

*/

}
