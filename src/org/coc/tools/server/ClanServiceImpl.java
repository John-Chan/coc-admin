package org.coc.tools.server;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.client.ClanService;
import org.coc.tools.server.dao.ClanDao;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.Clan.REG_STATUS;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class ClanServiceImpl extends RemoteServiceServlet  implements ClanService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1988771299566054548L;

	private		Objectify ofy=MyOfyService.ofy();
	private ClanDao		dao=new ClanDao(ofy);
	
	@Override
	public Clan addClan(Clan clan) {
		dao.save(clan);
		return clan;
	}

	@Override
	public Clan updateClan(Clan clan) {
		dao.update(clan);
		return clan;
	}

	@Override
	public Clan removeClan(Clan clan) {
		dao.delete(clan);
		return clan;
	}

	@Override
	public Clan addClan(String clanTag, String clanName, String clanSymbol,
			REG_STATUS registered) {
		Clan clan=new Clan(clanTag,clanName,clanSymbol,registered);
		return addClan(clan);
	}

	@Override
	public Clan removeClanByTag(String clanTag) {
		Clan clan=dao.getClanByTag(clanTag);
		if(clan != null){
			dao.delete(clan);
		}
		return clan;
	}

	@Override
	public List<Clan> getClanList(int maxResult) {
		return new ArrayList<Clan>( dao.getList(Clan.class, maxResult));
	}

	@Override
	public List<Clan> getClanListByName(String clanName, int maxResult) {
		return  new ArrayList<Clan>( dao.getClanByName(clanName));
	}

	@Override
	public List<Clan> getRegedClanList(int maxResult) {
		return  new ArrayList<Clan>( dao.getRegedClan(maxResult));
	}

}
