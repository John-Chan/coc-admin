package org.coc.tools.client;

import java.util.ArrayList;




import org.coc.tools.shared.model.CWIndex;

//import com.google.gwt.sample.contacts.shared.Contact;
//import com.google.gwt.sample.contacts.shared.ContactDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("cwIndexService")
public interface CWIndexService extends RemoteService {
	CWIndex addCWIndex(CWIndex one);

	// Boolean deleteCWIndex(Long id);
	// ArrayList<CWIndex> deleteContacts(ArrayList<Long> ids);
	ArrayList<CWIndex> getCWIndexList(int maxResult);
	CWIndex getCWIndex(Long id);
	CWIndex updateCWIndex(CWIndex one);
}
