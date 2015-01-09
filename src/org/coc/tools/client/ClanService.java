package org.coc.tools.client;

import java.util.List;

import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("clanService")
public interface ClanService extends RemoteService{

	Clan	addClan(Clan clan);
	Clan	updateClan(Clan clan);
	Clan	removeClan(Clan clan);
	Clan	addClan(String	clanTag,String	clanName,String	clanSymbol,Clan.REG_STATUS registered);

	Clan	removeClanByTag(String	clanTag);
	List<Clan> getRegedClanList(int maxResult);
	List<Clan> getClanList(int maxResult);
	List<Clan> getClanListByName(String	clanName,int maxResult);
	
	
	
}
