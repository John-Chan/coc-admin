package org.coc.tools.server;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.client.ClanWarEntryService;
import org.coc.tools.server.dao.*;
import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarBaseOrder;
import org.coc.tools.shared.model.WarDetail;
import org.coc.tools.shared.model.WarResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class ClanWarEntryServiceImpl  extends RemoteServiceServlet  implements ClanWarEntryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8745465169429849051L;

	private		Objectify ofy=MyOfyService.ofy();
	//private ClanWarEntryDao dao=new ClanWarEntryDao();
	private ClanDao	clanDao=new ClanDao(ofy);
	private ClanWarEntryDao clanWarEntryDao=new ClanWarEntryDao(ofy);
	private CWIndexDao cwIndexDao=new CWIndexDao(ofy);
	private WarBaseOrderDao warBaseOrderDao=new WarBaseOrderDao(ofy);
	private WarDetailDao warDetailDao=new WarDetailDao(ofy);
	private WarResultDao warResultDao=new WarResultDao(ofy);
	
	private ClanWarEntryPojo	setupLinks(CWIndex cwIndex,ClanWarEntryPojo val){
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
	private ClanWarEntryPojo addWithoutTxn(ClanWarEntryPojo value) {
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
	private ClanWarEntryPojo loadWithoutTxn(Long warId) {
		CWIndex cwIndex=cwIndexDao.loadById(CWIndex.class, warId);
		if(cwIndex == null) return null;
		return loadWithoutTxn(cwIndex);
	}
	private ClanWarEntryPojo loadWithoutTxn(CWIndex cwIndex) {
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
	private ClanWarEntryPojo deleteWithoutTxn(ClanWarEntryPojo value) {
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
	@Override
	public ClanWarEntryPojo add(ClanWarEntryPojo value) {
		return addWithoutTxn(value);
	}

	@Override
	public ClanWarEntryPojo update(ClanWarEntryPojo value) {
		return addWithoutTxn(value);
	}

	@Override
	public ClanWarEntryPojo remove(ClanWarEntryPojo value) {
		return deleteWithoutTxn(value);
	}

	@Override
	public ClanWarEntryPojo removeByCWIndex(CWIndex value) {
		ClanWarEntryPojo one=loadWithoutTxn(value.getRowId());
		return remove(one);
	}

	@Override
	public List<ClanWarEntryPojo> getList(int maxResult) {
		ArrayList<CWIndex> indexList=new ArrayList<>(cwIndexDao.getList(CWIndex.class, maxResult));
		ArrayList<ClanWarEntryPojo> result=new ArrayList<ClanWarEntryPojo>();
		for(CWIndex one:indexList){
			result.add(loadWithoutTxn(one));
		}
		return result;
	}


	@Override
	public ClanWarEntryPojo getByWarId(Long warId) {
		return loadWithoutTxn(warId);
	}
	@Override
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
	@Override
	public List<WarResult> getWarResultByWarId(Long warId) {
		List<WarResult> list=new ArrayList<WarResult>();
		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			list.add(full.getHomeClanWarResult());
			list.add(full.getEnemyClanWarResult());
		}
		return list;
	}
	@Override
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
	@Override
	public List<WarDetail> getWarDetailByWarId(Long warId) {
		List<WarDetail> list=new ArrayList<WarDetail>();

		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			list=full.getWarDetails();
		}
		return list;
	}
	@Override
	public RpcResult updateWarDetailByWarId(Long warId, List<WarDetail> list) {
		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			full.setWarDetails(list);
			addWithoutTxn(full);
			return new RpcResult();
		}
		return new RpcResult(RpcResult.ERROR_CODE.EC_FAILED,null);
	}
	@Override
	public List<WarBaseOrder> getWarBaseOrderByWarId(Long warId) {
		List<WarBaseOrder> list=new ArrayList<WarBaseOrder>();

		ClanWarEntryPojo full= getByWarId(warId);
		if(full != null ){
			list= full.getWarBaseOrders();
		}
		return list;
	}
	@Override
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

}
