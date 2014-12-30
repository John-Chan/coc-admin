package org.coc.tools.client.presenter;

import java.util.Date;

import org.coc.tools.client.ClanServiceAsync;
import org.coc.tools.client.ClanWarEntryServiceAsync;
import org.coc.tools.client.presenter.CWIndexEditPresenter.Display;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ClanEditPresenter implements Presenter {

	public interface Display {

		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getClanTag();

		HasValue<String> getClanName();

		HasValue<String> getClanSymbol();

		Widget asWidget();
	}

	private Clan clan;
	private final ClanServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public ClanEditPresenter(ClanServiceAsync rpcService,
			HandlerManager eventBus, Display display) {

		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.clan = new Clan();
		this.display = display;
		bind();
	}
			
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub

	}
	
	public void bind() {
	}
	

}
