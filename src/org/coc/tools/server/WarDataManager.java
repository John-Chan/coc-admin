package org.coc.tools.server;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.server.dao.CWIndexDao;
import org.coc.tools.server.dao.ClanDao;
import org.coc.tools.server.dao.WarBaseOrderDao;
import org.coc.tools.server.dao.WarDetailDao;
import org.coc.tools.server.dao.WarResultDao;
import org.coc.tools.shared.QueryPage;
import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarBaseOrder;
import org.coc.tools.shared.model.WarDetail;
import org.coc.tools.shared.model.WarResult;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.cmd.Query;

/// high level warper for war informations
public class WarDataManager {

	private ClanDao	clanDao=null;
	//private ClanWarEntryDao clanWarEntryDao=null;
	private CWIndexDao cwIndexDao=null;
	private WarBaseOrderDao warBaseOrderDao=null;
	private WarDetailDao warDetailDao=null;
	private WarResultDao warResultDao=null;
	//private int maxQryLimit=MAX_CW_ENTRY_QRY_COUNT_ONCE;
	
	public WarDataManager(Objectify ofy){
		clanDao=new ClanDao(ofy);
		//private ClanWarEntryDao clanWarEntryDao=new ClanWarEntryDao(ofy);
		cwIndexDao=new CWIndexDao(ofy);
		warBaseOrderDao=new WarBaseOrderDao(ofy);
		warDetailDao=new WarDetailDao(ofy);
		warResultDao=new WarResultDao(ofy);
	}

	public ClanDao getClanDao() {
		return clanDao;
	}
	public CWIndexDao getCwIndexDao() {
		return cwIndexDao;
	}
	public WarBaseOrderDao getWarBaseOrderDao() {
		return warBaseOrderDao;
	}
	public WarDetailDao getWarDetailDao() {
		return warDetailDao;
	}
	public WarResultDao getWarResultDao() {
		return warResultDao;
	}
	
	public ClanWarEntryPojo	setupLinks(CWIndex cwIndex,ClanWarEntryPojo val){
		Long warId = cwIndex.getRowId();
		val.getEnemyClanWarResult().setWarId(warId);
		val.getEnemyClanWarResult().setClanId(cwIndex.getEnemyClan().getRowId());
		val.getHomeClanWarResult().setWarId(warId);
		val.getHomeClanWarResult().setClanId(cwIndex.getHomeClan().getRowId());
		
		for (WarBaseOrder one:val.getWarBaseOrders()) {
			one.setWarId(warId);
			one.setAttackerClanId(cwIndex.getHomeClan().getRowId());
			one.setTargetClanId(cwIndex.getEnemyClan().getRowId());
		}
		for (WarDetail one:val.getWarDetails()) {
			one.setWarId(warId);
			one.setAttackerClanId(cwIndex.getHomeClan().getRowId());
		}
		return val;
	}
	public ClanWarEntryPojo addWithoutTxn(ClanWarEntryPojo value) {
		Clan homeClan=value.getWarIndex().getHomeClan();
		Clan enemyClan=value.getWarIndex().getEnemyClan();
		
		//value.setHomeClan(homeClan);
		//value.setEnemyClan(enemyClan);
		
		clanDao.save(homeClan);
		clanDao.save(enemyClan);
		
		cwIndexDao.save(value.getWarIndex());
		/// update to link warid
		setupLinks(value.getWarIndex(),value);

		warBaseOrderDao.save(value.getWarBaseOrders());
		warDetailDao.save(value.getWarDetails());
		warResultDao.save(value.getHomeClanWarResult());
		warResultDao.save(value.getEnemyClanWarResult());
		return value;
	}
	public ClanWarEntryPojo loadWithoutTxn(Long warId) {
		CWIndex cwIndex=cwIndexDao.loadById(CWIndex.class, warId);
		if(cwIndex == null) return null;
		return loadWithoutTxn(cwIndex);
	}
	public ClanWarEntryPojo loadWithoutTxn(CWIndex cwIndex) {
		ClanWarEntryPojo value=new ClanWarEntryPojo();
		Long warId=cwIndex.getRowId();
		/// non-null
		value.setWarIndex(cwIndex);
		//value.setHomeClan(cwIndex.getHomeClan());
		//value.setEnemyClan(cwIndex.getEnemyClan());
		value.setWarBaseOrders(new ArrayList<>(warBaseOrderDao.getListByWarId(warId)));
		value.setWarDetails(new ArrayList<>(warDetailDao.getListByWarId(warId)));
		value.setHomeClanWarResult(warResultDao.getOne(warId, cwIndex.getHomeClan().getRowId()));
		value.setEnemyClanWarResult(warResultDao.getOne(warId, cwIndex.getEnemyClan().getRowId()));
		
		return value;
	}
	public ClanWarEntryPojo deleteWithoutTxn(ClanWarEntryPojo value) {
		/// non-null
		cwIndexDao.delete(value.getWarIndex());
		//clanDao.delete(value.getHomeClan());
		//clanDao.delete(value.getEnemyClan());

		warBaseOrderDao.delete(value.getWarBaseOrders());
		warDetailDao.delete(value.getWarDetails());
		warResultDao.delete(value.getHomeClanWarResult());
		warResultDao.delete(value.getEnemyClanWarResult());
		return value;
		
	}

	public List<ClanWarEntryPojo> getList(int maxResult) {
		ArrayList<CWIndex> indexList=new ArrayList<>(cwIndexDao.getList(CWIndex.class, maxResult));
		ArrayList<ClanWarEntryPojo> result=new ArrayList<ClanWarEntryPojo>();
		for(CWIndex one:indexList){
			result.add(loadWithoutTxn(one));
		}
		return result;
	}



	public ClanWarEntryPojo getByWarId(Long warId) {
		return loadWithoutTxn(warId);
	}

	public List<ClanWarEntryPojo> getListByClanTag(String tag,
			int maxResult) {

		ArrayList<ClanWarEntryPojo> result=new ArrayList<ClanWarEntryPojo>();
		List<CWIndex> indexs=cwIndexDao.getListByHomeClanTag(tag, maxResult);
		if(indexs != null){
			for(CWIndex one:indexs){
				result.add(loadWithoutTxn(one)); 
			}
		}
		return result;
	}

	public List<WarResult> getWarResultByWarId(Long warId) {
		List<WarResult> list=new ArrayList<WarResult>();
		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			list.add(full.getHomeClanWarResult());
			list.add(full.getEnemyClanWarResult());
		}
		return list;
	}

	public RpcResult updateWarResultByWarId(Long warId, WarResult homeTeam,
			WarResult enemyTeam) {
		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			full.setEnemyClanWarResult(enemyTeam);
			full.setHomeClanWarResult(homeTeam);
			addWithoutTxn(full);
			return new RpcResult();
		}
		return new RpcResult(RpcResult.ERROR_CODE.EC_FAILED,null);
	}

	public List<WarDetail> getWarDetailByWarId(Long warId) {
		List<WarDetail> list=new ArrayList<WarDetail>();

		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			list=full.getWarDetails();
		}
		return list;
	}

	public RpcResult updateWarDetailByWarId(Long warId, List<WarDetail> list) {
		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			full.setWarDetails(list);
			addWithoutTxn(full);
			return new RpcResult();
		}
		return new RpcResult(RpcResult.ERROR_CODE.EC_FAILED,null);
	}

	public List<WarBaseOrder> getWarBaseOrderByWarId(Long warId) {
		List<WarBaseOrder> list=new ArrayList<WarBaseOrder>();

		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			list= full.getWarBaseOrders();
		}
		return list;
	}

	public RpcResult updateWarBaseOrderByWarId(Long warId,
			List<WarBaseOrder> list) {
		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			full.setWarBaseOrders(list);
			addWithoutTxn(full);
			return new RpcResult();
		}
		return new RpcResult(RpcResult.ERROR_CODE.EC_FAILED,null);
	}
	public QueryPage<ClanWarEntryPojo> getPageByClanTag(String tag,
			int pageNumber){

		int rowPeerPage=10;
		QueryPage<ClanWarEntryPojo> page=new QueryPage<ClanWarEntryPojo>();
		Query<CWIndex> qry=cwIndexDao.startQry(CWIndex.class).filter("homeClan.clanTag", tag).order("-prepareDate");
		int totalRow=qry.count();
		int from=rowPeerPage*pageNumber;
		if(from<totalRow ){
			//qry.limit(rowPeerPage).offset(from);
			//List<CWIndex> indexs=qry.list();
			

			List<CWIndex> indexs=qry.limit(rowPeerPage).offset(from).list();
			if(indexs != null){
				for(CWIndex one:indexs){
					page.getResultSet().add(loadWithoutTxn(one));
				}
			}
		}
		page.setTotalRowCount(totalRow);
		page.setPageSize(rowPeerPage);
		page.setPageNumber(pageNumber);
		return page;
	}
	

}
