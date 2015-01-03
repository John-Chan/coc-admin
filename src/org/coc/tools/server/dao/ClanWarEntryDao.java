package org.coc.tools.server.dao;

import static org.coc.tools.server.MyOfyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.coc.tools.server.model.ClanWarEntry;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarBaseOrder;
import org.coc.tools.shared.model.WarDetail;
import org.coc.tools.shared.model.WarResult;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

public class ClanWarEntryDao   extends ObjectifyDao<ClanWarEntry> {

	public ClanWarEntryDao(){
		//
	}
	public ClanWarEntryDao(Objectify ofy){
		super(ofy);
	}

	public  ClanWarEntryPojo	loadPojo(ClanWarEntry entry){
		ClanWarEntryPojo pojo=new ClanWarEntryPojo();
		
		pojo.setWarIndex( entry.getWarIndex().get());
		//pojo.setHomeClan(entry.getHomeClan().get());
		//pojo.setEnemyClan(entry.getEnemyClan().get());
		pojo.setHomeClanWarResult(entry.getHomeClanWarResult().get());
		pojo.setEnemyClanWarResult(entry.getEnemyClanWarResult().get());
		
		pojo.setWarBaseOrders(new ArrayList<WarBaseOrder>( loadWarBaseOrderList(entry)));
		pojo.setWarDetails( new ArrayList<WarDetail>(loadWarDetailList(entry)));
		
		return pojo;
	}
	public ClanWarEntry createFromPojo(ClanWarEntryPojo pojo){
		ClanWarEntry entry=new ClanWarEntry();

		//this.setEnemyClan(entry,pojo.getEnemyClan());
		this.setEnemyClanWarResult(entry,pojo.getEnemyClanWarResult());
		//this.setHomeClan(entry,pojo.getEnemyClan());
		this.setHomeClanWarResult(entry,pojo.getHomeClanWarResult());
		this.setWarBaseOrderList(entry,pojo.getWarBaseOrders());
		this.setWarDetailList(entry,pojo.getWarDetails());
		this.setWarIndex(entry,pojo.getWarIndex());
		
		
		return entry;
	}
	public ClanWarEntry saveCascade(ClanWarEntry entry){

		// TODO: TXN
		this.saveEnemyClan(entry);
		this.saveEnemyClanWarResult(entry);
		this.saveHomeClan(entry);
		this.saveHomeClanWarResult(entry);
		this.saveWarBaseOrderList(entry);
		this.saveWarDetailList(entry);
		this.saveWarIndex(entry);
		this.saveEntry(entry);
		return entry;
	}
	public List<WarBaseOrder> loadWarBaseOrderList(ClanWarEntry entry) {

	    List<WarBaseOrder> ret = new ArrayList<WarBaseOrder>();
	    Iterator<Ref<WarBaseOrder>> it = entry.getWarBaseOrderList().iterator();
	    while (it.hasNext())
	        ret.add(it.next().getValue());
	    return ret;
	}

	public List<WarDetail> loadWarDetailList(ClanWarEntry entry) {

	    List<WarDetail> ret = new ArrayList<WarDetail>();
	    Iterator<Ref<WarDetail>> it = entry.getWarDetailList().iterator();
	    while (it.hasNext())
	        ret.add(it.next().getValue());
	    return ret;
	}
	public void setWarBaseOrderList(ClanWarEntry entry,List<WarBaseOrder> warBaseOrders) {

	    Iterator<WarBaseOrder> it = warBaseOrders.iterator();

	    while (it.hasNext())
	    	addWarBaseOrder(entry,it.next());
	}
	public void setWarDetailList(ClanWarEntry entry,List<WarDetail> warDetails) {

	    Iterator<WarDetail> it = warDetails.iterator();

	    while (it.hasNext())
	    	addWarDetail(entry,it.next());
	}
	public WarBaseOrder addWarBaseOrder(ClanWarEntry entry,WarBaseOrder value) {
		entry.getWarBaseOrderList().add(Ref.create(value));
		return value;
	}
	
	public WarDetail addWarDetail(ClanWarEntry entry,WarDetail value) {
		entry.getWarDetailList().add(Ref.create(value));
		return value;
	}
	
	public CWIndex loadWarIndex(ClanWarEntry entry) {
		return entry.getWarIndex().get();
	}

	public CWIndex setWarIndex(ClanWarEntry entry,CWIndex value) {
		entry.setWarIndex(Ref.create(value));
		return value;
	}
	
	public WarResult loadHomeClanWarResult(ClanWarEntry entry) {
		return entry.getHomeClanWarResult().get();
	}

	public WarResult setHomeClanWarResult(ClanWarEntry entry,WarResult value) {
		entry.setHomeClanWarResult(Ref.create(value));
		return value;
	}
	public WarResult loadEnemyClanWarResult(ClanWarEntry entry) {
		return entry.getEnemyClanWarResult().get();
	}

	public WarResult setEnemyClanWarResult(ClanWarEntry entry,WarResult value) {
		entry.setEnemyClanWarResult(Ref.create(value));
		return value;
	}
	
	public Clan loadHomeClan(ClanWarEntry entry) {
		return entry.getHomeClan().get();
	}

	public Clan setHomeClan(ClanWarEntry entry,Clan value) {
		entry.setHomeClan(Ref.create(value));
		return value;
	}

	public Clan loadEnemyClan(ClanWarEntry entry) {
		return entry.getEnemyClan().get();
	}

	public Clan setEnemyClan(ClanWarEntry entry,Clan value) {
		entry.setEnemyClan(Ref.create(value));
		return value;
	}
	
	/*
	public  ClanWarEntry  addCascade(ClanWarEntry val) {
		ofy().save().entity(val.getHomeClan()).now();
		return val;
	}

	public Map<ClanWarEntry,ClanWarEntry> add(
			Collection<EntityType> more) {
		return SimpleDao.add(more);
	}

	public void remove(ClanWarEntry val) {
		SimpleDao.remove(val);

	}

	public void remove(
			Collection<ClanWarEntry> more) {
		SimpleDao.remove(more);

	}

	public ClanWarEntry update(ClanWarEntry val) {
		return SimpleDao.update(val);

	}

	public Map<ClanWarEntry,ClanWarEntry> update(
			Collection<ClanWarEntry> more) {
		return SimpleDao.update(more);

	}

	public List<ClanWarEntry> query(
			Class<ClanWarEntry> clazz, int maxResult) {
		return SimpleDao.query(clazz, maxResult);

	}*/
	
	public ClanWarEntry saveWarIndex(ClanWarEntry entry) {

		ofy().save().entity(entry.getWarIndex().get()).now();
		return entry;
	}
	public ClanWarEntry saveHomeClan(ClanWarEntry entry) {

		ofy().save().entity(entry.getHomeClan().get() ).now();
		return entry;
	}
	public ClanWarEntry saveEnemyClan(ClanWarEntry entry) {
		ofy().save().entity(entry.getEnemyClan().get() ).now();
		return entry;
	}
	public ClanWarEntry saveHomeClanWarResult(ClanWarEntry entry) {

		ofy().save().entity(entry.getHomeClanWarResult().get() ).now();
		return entry;
	}
	public ClanWarEntry saveEnemyClanWarResult(ClanWarEntry entry) {

		ofy().save().entity(entry.getEnemyClanWarResult().get() ).now();
		return entry;
	}
	public ClanWarEntry saveWarBaseOrderList(ClanWarEntry entry) {

		List<WarBaseOrder> list=this.loadWarBaseOrderList(entry);
		for(WarBaseOrder val : list){
			ofy().save().entity(val).now();
		}
		return entry;
	}
	public ClanWarEntry saveWarDetailList(ClanWarEntry entry) {

		List<WarDetail> list=loadWarDetailList( entry);
		for(WarDetail val : list){
			ofy().save().entity(val).now();
		}
		return entry;
	}
	
	public ClanWarEntry saveEntry(ClanWarEntry entry){
		ofy().save().entity(entry).now();
		return entry;
	}
	
	/*
	List<ClanWarEntry> getListByClanTag(String tag,int maxResult){
		ClanWarEntry found=null;
		found= this.getOfy().load().type(ClanWarEntry.class).filter("clanTag", tag).first().now();
        return found;
	}
	*/
	
}
