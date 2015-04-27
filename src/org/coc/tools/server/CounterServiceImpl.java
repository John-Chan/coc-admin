package org.coc.tools.server;

import java.util.List;

import org.coc.tools.client.CounterService;
import org.coc.tools.server.misc.WarResultCounterBuilder;
import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.CounterOpponent;
import org.coc.tools.shared.model.CounterWarResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class CounterServiceImpl  extends RemoteServiceServlet  implements  CounterService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779157996710825136L;

	private		Objectify ofy=MyOfyService.ofy();
	private 	WarDataManager	dataMager=new WarDataManager(ofy);
	@Override
	public RpcData<CounterWarResult> countWarResult(String clanTag) {
		RpcData<CounterWarResult> rspData=new RpcData<CounterWarResult>();
		Clan clanForCount=dataMager.getClanDao().getClanByTag(clanTag);
		if(clanForCount !=null){
			int countWin=0;int countLose=0;int countDraw=0;
			CounterWarResult data=WarResultCounterBuilder.makeCounterWarResult(countWin, countLose, countDraw);
			data.setClan(clanForCount);
			rspData.setData(data);
		}else{
			rspData.setErrorCode(RpcResult.ERROR_CODE.EC_FAILED);
			rspData.setMsg("clan not found");
		}
		return rspData;
	}

	@Override
	public RpcData<List<CounterOpponent>> counterOpponent(String clanTag) {
		// TODO Auto-generated method stub
		return null;
	}

}
