package org.coc.tools.client;

import java.util.List;

import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarBaseOrder;
import org.coc.tools.shared.model.WarDetail;
import org.coc.tools.shared.model.WarResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClanWarEntryServiceAsync {	
	void	add(ClanWarEntryPojo value,AsyncCallback<ClanWarEntryPojo> callback);
	void	update(ClanWarEntryPojo value,AsyncCallback<ClanWarEntryPojo> callback);
	void	remove(ClanWarEntryPojo value,AsyncCallback<ClanWarEntryPojo> callback);
	void	removeByCWIndex(CWIndex value,AsyncCallback<ClanWarEntryPojo> callback);
	void	getByWarId(Long warId,AsyncCallback<ClanWarEntryPojo> callback);
	void 	getList(int maxResult,AsyncCallback<List<ClanWarEntryPojo>> callback);
	void 	getListByClanTag(String tag,int maxResult,AsyncCallback<List<ClanWarEntryPojo>> callback);

	/**
	 * 
	 * @param warId
	 * @return List.get(0) == homeResult  List.get(1) == enemyResult
	 */
	void		getWarResultByWarId(Long warId,AsyncCallback<List<WarResult>> callback);
	void		updateWarResultByWarId(Long warId,WarResult homeTeam,WarResult enemyTeam,AsyncCallback<RpcResult> callback);

	void		getWarDetailByWarId(Long warId,AsyncCallback<List<WarDetail>> callback);
	void		updateWarDetailByWarId(Long warId,List<WarDetail> list,AsyncCallback<RpcResult> callback);

	void		getWarBaseOrderByWarId(Long warId,AsyncCallback<List<WarBaseOrder>> callback);
	void		updateWarBaseOrderByWarId(Long warId,List<WarBaseOrder> list,AsyncCallback<RpcResult> callback);
	
}
