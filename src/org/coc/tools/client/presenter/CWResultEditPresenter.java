package org.coc.tools.client.presenter;

import org.coc.tools.client.ClanWarEntryServiceAsync;
import org.coc.tools.client.presenter.CWIndexEditPresenter.Display;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarResult;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class CWResultEditPresenter implements Presenter {

	private WarResult homeClanWarResult=new WarResult();
	private WarResult enemyClanWarResult=new WarResult();
	public CWResultEditPresenter(ClanWarEntryServiceAsync rpcService,
			HandlerManager eventBus, Display display, Long warId){
		//
	}
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub

	}

}
