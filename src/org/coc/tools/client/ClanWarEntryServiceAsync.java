package org.coc.tools.client;

import java.util.ArrayList;

import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.ClanWarEntryPojo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClanWarEntryServiceAsync {	
	void	add(ClanWarEntryPojo value,AsyncCallback<ClanWarEntryPojo> callback);
	void	update(ClanWarEntryPojo value,AsyncCallback<ClanWarEntryPojo> callback);
	void	remove(ClanWarEntryPojo value,AsyncCallback<ClanWarEntryPojo> callback);
	void	removeByCWIndex(CWIndex value,AsyncCallback<ClanWarEntryPojo> callback);
	void	getByWarId(Long warId,AsyncCallback<ClanWarEntryPojo> callback);
	void 	getList(int maxResult,AsyncCallback<ArrayList<ClanWarEntryPojo>> callback);
	void 	getListByClanTag(String tag,int maxResult,AsyncCallback<ArrayList<ClanWarEntryPojo>> callback);

}
