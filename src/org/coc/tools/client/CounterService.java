package org.coc.tools.client;

import java.util.List;
import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.model.CounterOpponent;
import org.coc.tools.shared.model.CounterWarResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("counterService")
public interface CounterService extends RemoteService {
	RpcData<CounterWarResult> countWarResult(String clanTag);
	RpcData<List<CounterOpponent>> counterOpponent(String clanTag);
}
