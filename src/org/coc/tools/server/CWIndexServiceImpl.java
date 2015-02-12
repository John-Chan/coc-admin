package org.coc.tools.server;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.client.CWIndexService;
import org.coc.tools.server.dao.CWIndexDao;
import org.coc.tools.shared.model.CWIndex;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class CWIndexServiceImpl extends RemoteServiceServlet  implements CWIndexService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9223226377633504385L;

	private		Objectify ofy=MyOfyService.ofy();
	private CWIndexDao		dao=new CWIndexDao(ofy);
	
	@Override
	public CWIndex addCWIndex(CWIndex one) {
		/*if(!one.getEnemyClan().getClanTag().equals(one.getHomeClan().getClanTag())){
			dao.save(one);
		}*/
		dao.save(one);
		return one;
	}

	@Override
	public List<CWIndex> getCWIndexList(int maxResult) {
		return new ArrayList<CWIndex>(dao.getList(CWIndex.class, maxResult));
		//long n=maxResult;
		//return TestHelper.createCWIndexForTest(n);
	}

	@Override
	public CWIndex getCWIndex(Long id) {
		return dao.loadById(CWIndex.class, id);
	}
	@Override
	public CWIndex updateCWIndex(CWIndex one) {
		dao.update(one);
		return one;
	}

}
