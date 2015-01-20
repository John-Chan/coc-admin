package org.coc.tools.client;

import java.util.List;

import org.coc.tools.shared.QueryPage;
import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarBaseOrder;
import org.coc.tools.shared.model.WarDetail;
import org.coc.tools.shared.model.WarResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("clanWarEntryService")
public interface ClanWarEntryService  extends RemoteService{

	//CWIndex
	//ClanWarEntry addClanWarEntry(ClanWarEntry one);

	//ArrayList<CWIndex> getCWIndexList(int maxResult);
	
	ClanWarEntryPojo	add(ClanWarEntryPojo value);
	ClanWarEntryPojo	update(ClanWarEntryPojo value);
	ClanWarEntryPojo	remove(ClanWarEntryPojo value);
	ClanWarEntryPojo	removeByCWIndex(CWIndex value);
	ClanWarEntryPojo	getByWarId(Long warId);
	List<ClanWarEntryPojo> getList(int maxResult);
	List<ClanWarEntryPojo> getListByClanTag(String tag,int maxResult);
	
	/// pageNumber start from 0
	QueryPage<ClanWarEntryPojo> getPageByClanTag(String tag,int pageNumber);

	
	/**
	 * 
	 * @param warId
	 * @return List.get(0) == homeResult  List.get(1) == enemyResult
	 */
	List<WarResult>		getWarResultByWarId(Long warId);
	RpcResult		updateWarResultByWarId(Long warId,WarResult homeTeam,WarResult enemyTeam);

	List<WarDetail>		getWarDetailByWarId(Long warId);
	RpcResult		updateWarDetailByWarId(Long warId,List<WarDetail> list);

	List<WarBaseOrder>		getWarBaseOrderByWarId(Long warId);
	RpcResult		updateWarBaseOrderByWarId(Long warId,List<WarBaseOrder> list);
	
}
