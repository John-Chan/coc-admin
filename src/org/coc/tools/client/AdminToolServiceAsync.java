package org.coc.tools.client;

import java.util.Map;

import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.RpcResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminToolServiceAsync {

	
	void	authRootUser(String loginId,String passwrod,AsyncCallback<RpcResult> callback);
	void	getImpDataInfo(String loginId,String passwrod,AsyncCallback<RpcData<Map<String,String>>> callback);
	void	doImpData(String loginId,String passwrod,AsyncCallback<RpcResult> callback);
	
}
