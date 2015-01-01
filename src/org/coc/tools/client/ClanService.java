package org.coc.tools.client;

import java.util.ArrayList;

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
	ArrayList<Clan> getRegedClanList(int maxResult);
	ArrayList<Clan> getClanList(int maxResult);
	ArrayList<Clan> getClanListByName(String	clanName,int maxResult);
	
	
	
}
