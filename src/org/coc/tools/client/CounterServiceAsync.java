package org.coc.tools.client;

import java.util.List;

import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.model.CounterOpponent;
import org.coc.tools.shared.model.CounterWarResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CounterServiceAsync {

	void	countWarResult(String clanTag, AsyncCallback<RpcData<CounterWarResult>> callback);

	void	counterOpponent(String clanTag, AsyncCallback<RpcData<List<CounterOpponent>>> callback);


}
