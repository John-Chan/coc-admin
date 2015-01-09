package org.coc.tools.client;

import java.util.List;

import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClanServiceAsync {

	
	void	addClan(Clan clan,AsyncCallback<Clan> callback);
	void	updateClan(Clan clan,AsyncCallback<Clan> callback);
	void	removeClan(Clan clan,AsyncCallback<Clan> callback);
	void	addClan(String	clanTag,String	clanName,String	clanSymbol,Clan.REG_STATUS registered,AsyncCallback<Clan> callback);

	void	removeClanByTag(String	clanTag,AsyncCallback<Clan> callback);
	void 	getRegedClanList(int maxResult,AsyncCallback<List<Clan>> callback);
	void 	getClanList(int maxResult,AsyncCallback<List<Clan>> callback);
	void 	getClanListByName(String	clanName,int maxResult,AsyncCallback<List<Clan>> callback);
}
