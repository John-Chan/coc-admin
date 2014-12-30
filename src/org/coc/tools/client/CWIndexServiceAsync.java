package org.coc.tools.client;

import java.util.ArrayList;

import org.coc.tools.shared.model.CWIndex;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CWIndexServiceAsync {
	void addCWIndex(CWIndex one,AsyncCallback<CWIndex> callback);
	// Boolean deleteCWIndex(Long id);
	// ArrayList<CWIndex> deleteContacts(ArrayList<Long> ids);
	void getCWIndexList(int maxResult,AsyncCallback<ArrayList<CWIndex>> callback);
	void getCWIndex(Long id,AsyncCallback<CWIndex> callback);
	void updateCWIndex(CWIndex one,AsyncCallback<CWIndex> callback);
}
