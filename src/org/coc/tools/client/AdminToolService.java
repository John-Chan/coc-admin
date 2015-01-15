package org.coc.tools.client;

import java.util.Map;

import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.RpcResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("adminToolService")
public interface AdminToolService extends RemoteService {

	RpcResult	authRootUser(String loginId,String passwrod);
	RpcData<Map<String,String>>	getImpDataInfo(String loginId,String passwrod);
	RpcResult	doImpData(String loginId,String passwrod);
}
