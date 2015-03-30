package org.coc.tools.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.coc.tools.client.AdminToolService;
import org.coc.tools.server.dataimp.XmlDataImpoter;
import org.coc.tools.server.misc.DateTimeHelper;
import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarResult;
import org.coc.tools.server.config.InMemoryUser;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class AdminToolServiceImpl extends RemoteServiceServlet implements
		AdminToolService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8891635212735822317L;
	private static final Logger log = Logger.getLogger(AdminToolServiceImpl.class.getName());

	
	private		Objectify ofy=MyOfyService.ofy();
	private 	WarDataManager	dataMager=new WarDataManager(ofy);

	
	private 	boolean checkRootUser(final String userName,final String pwd,RpcResult ret){
		if( InMemoryUser.RootAdmin.getLoginId().equals(userName) && InMemoryUser.RootAdmin.checkPassword(pwd)){
			ret.setErrorCode(RpcResult.ERROR_CODE.EC_NO_ERR);
			ret.setMsg(null);
			return true;
		}else{
			ret.setErrorCode(RpcResult.ERROR_CODE.EC_FAILED);
			ret.setMsg("wrong user name or password");
			return false;
		}
	}
	@Override
	public RpcResult authRootUser(String loginId, String passwrod) {
		return new RpcResult();
	}

	@Override
	public RpcData<Map<String, String>> getImpDataInfo(String loginId,
			String passwrod) {
		RpcData<Map<String, String>> rsp = new RpcData<Map<String, String>>();
		Map<String, String> moreInfo = new HashMap<String, String>();
		if(!checkRootUser(loginId,passwrod,rsp)){
			return rsp;
		}

		try {
			XmlDataImpoter imp=new XmlDataImpoter(getServletContext());
			imp.load();
			
			moreInfo.put("Clan info for imp", " count:"+imp.getClanTab().size());
			moreInfo.put("War result info for imp", " count:"+imp.getWarResultTab().size());
			moreInfo.put("War index info for imp", " count:"+imp.getWarLogTab().size());
		} catch (Exception e) {
			rsp.setErrorCode(RpcResult.ERROR_CODE.EC_FAILED);
			moreInfo.put("error :", e.getMessage());
			// rsp.setMsg("miss file:"+IMP_DATA_DIR+DATA_FILE_CLAN_INFO);
			e.printStackTrace();
		}
		rsp.setData(moreInfo);
		return rsp;
	}

	@Override
	public RpcResult doImpData(String loginId, String passwrod) {
		
		/// TODO:no odd data
		RpcResult rsp=new RpcResult();
		if(!checkRootUser(loginId,passwrod,rsp)){
			return rsp;
		}
		XmlDataImpoter imp=new XmlDataImpoter(getServletContext());
		int countImpedClan=0;
		int countImpedWarResult=0;
		
		//int countImpedClan=0;
		try {
			imp.load();
			Map<String, Map<String,String>>	warLogTab = imp.getWarLogTab();
			Map<String, Clan>	clanTab=imp.getClanTab();
			Map<String, WarResult>	warResultTab=imp.getWarResultTab();
			for (Entry<String, Map<String,String>> entry: warLogTab.entrySet()) {

				String index=entry.getKey();
				log.info("import =>"+index);
				Map<String,String> linkInfo=entry.getValue();
				ClanWarEntryPojo fullData=new ClanWarEntryPojo();
		
				Date	endDate=DateTimeHelper.parseDate(linkInfo.get("END_DATE"), DateTimeHelper.FmtLong());
				
				fullData.getWarIndex().setPrepareDate(DateTimeHelper.addDay(endDate, -2));
				fullData.getWarIndex().setHomeClan(clanTab.get(linkInfo.get("HOME_CLAN_ID")));
				fullData.getWarIndex().setEnemyClan(clanTab.get(linkInfo.get("ENEMY_CLAN_ID")));
				fullData.getWarIndex().setScope(Integer.parseInt(linkInfo.get("PLAYER_COUNT")));
				WarResult homeClanWarResult=warResultTab.get(linkInfo.get("HOME_CLAN_WAR_DETAIL_ID") );
				WarResult enemyClanWarResult=warResultTab.get(linkInfo.get("ENEMY_CLAN_WAR_DETAIL_ID") );
				homeClanWarResult.setLocked(true);
				enemyClanWarResult.setLocked(true);
				fullData.setHomeClanWarResult(homeClanWarResult);
				fullData.setEnemyClanWarResult(enemyClanWarResult);
				dataMager.addWithoutTxn(fullData);
				countImpedClan+=2;
				countImpedWarResult+=2;
			}
			String msg= "imported clan:"+countImpedClan+",imported war result:"+countImpedWarResult;
			rsp.setMsg(msg);
			log.info("import done =>"+msg);
		} catch (Exception e) {
			rsp.setErrorCode(RpcResult.ERROR_CODE.EC_FAILED);
			//moreInfo.put(DATA_FILE_CLAN_INFO, e.getMessage());
			rsp.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return rsp;
	}
	@Override
	public RpcResult doRePutData(String loginId, String passwrod) {
		RpcResult rsp=new RpcResult();
		if(!checkRootUser(loginId,passwrod,rsp)){
			return rsp;
		}

		List<String>	opt_steps=new ArrayList<String>();

		int clan_count=reputAllClan();
		int cw_ret_count=reputAllCwResult();
		int cw_index_count=reputAllCwIndex();
		opt_steps.add("re-put for [Clan],count ="+clan_count+"\r\n");
		opt_steps.add("re-put for [CwResult],count ="+cw_ret_count+"\r\n");
		opt_steps.add("re-put for [CwIndex],count ="+cw_index_count+"\r\n");
		rsp.setMsg(opt_steps.toString());
		return rsp;
	}

	private int reputAllClan(){
		int maxResult=65535;
		int clan_count=0;
		
		List<Clan> all_clan=dataMager.getClanDao().getList(Clan.class, maxResult);
		clan_count=all_clan.size();
		for(Clan one:all_clan){
			dataMager.getClanDao().update(one);
		}

		return clan_count;
	}
	private int reputAllCwResult(){

		int maxResult=65535;
		int cw_ret_count=0;
		List<WarResult> all=dataMager.getWarResultDao().getList(WarResult.class, maxResult);
		cw_ret_count=all.size();
		for(WarResult one:all){
			dataMager.getWarResultDao().update(one);
		}
		return cw_ret_count;
	}
	private int reputAllCwIndex(){

		int maxResult=65535;
		int cw_index_count=0;

		List<CWIndex> all=dataMager.getCwIndexDao().getList(CWIndex.class, maxResult);
		cw_index_count=all.size();
		for(CWIndex one:all){
			dataMager.getCwIndexDao().update(one);
		}
		return cw_index_count;
	}
	
	
}
