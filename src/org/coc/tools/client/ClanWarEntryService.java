package org.coc.tools.client;

import java.util.ArrayList;

import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.ClanWarEntryPojo;

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
	ArrayList<ClanWarEntryPojo> getList(int maxResult);
	ArrayList<ClanWarEntryPojo> getListByCWIndex(CWIndex value,int maxResult);
	
	
}
