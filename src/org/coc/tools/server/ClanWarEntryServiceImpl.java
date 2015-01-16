package org.coc.tools.server;

import java.util.List;

import org.coc.tools.client.ClanWarEntryService;
import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.CWIndex;
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
	private 	WarDataManager	dataMager=new WarDataManager(ofy);

	@Override
	public ClanWarEntryPojo add(ClanWarEntryPojo value) {
		return dataMager.addWithoutTxn(value);
	}

	@Override
	public ClanWarEntryPojo update(ClanWarEntryPojo value) {
		return dataMager.addWithoutTxn(value);
	}

	@Override
	public ClanWarEntryPojo remove(ClanWarEntryPojo value) {
		return dataMager.deleteWithoutTxn(value);
	}

	@Override
	public ClanWarEntryPojo removeByCWIndex(CWIndex value) {
		ClanWarEntryPojo one=dataMager.loadWithoutTxn(value.getRowId());
		return remove(one);
	}

	@Override
	public List<ClanWarEntryPojo> getList(int maxResult) {
		return dataMager.getList(maxResult);
	}


	@Override
	public ClanWarEntryPojo getByWarId(Long warId) {
		return dataMager.loadWithoutTxn(warId);
	}
	@Override
	public List<ClanWarEntryPojo> getListByClanTag(String tag,
			int maxResult) {
		return dataMager.getListByClanTag(tag, maxResult);
	}
	@Override
	public List<WarResult> getWarResultByWarId(Long warId) {
		return dataMager.getWarResultByWarId(warId);
	}
	@Override
	public RpcResult updateWarResultByWarId(Long warId, WarResult homeTeam,
			WarResult enemyTeam) {
		return dataMager.updateWarResultByWarId(warId, homeTeam,enemyTeam) ;
	}
	@Override
	public List<WarDetail> getWarDetailByWarId(Long warId) {
		
		return dataMager.getWarDetailByWarId(warId ) ;
	}
	@Override
	public RpcResult updateWarDetailByWarId(Long warId, List<WarDetail> list) {
		return dataMager.updateWarDetailByWarId( warId, list);
	}
	@Override
	public List<WarBaseOrder> getWarBaseOrderByWarId(Long warId) {
		return dataMager.getWarBaseOrderByWarId( warId);
	}
	@Override
	public RpcResult updateWarBaseOrderByWarId(Long warId,
			List<WarBaseOrder> list) {
		return dataMager.updateWarBaseOrderByWarId(warId, list);
	}

}
