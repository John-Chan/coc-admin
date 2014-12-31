package org.coc.tools.client.presenter;

import org.coc.tools.client.ClanServiceAsync;
import org.coc.tools.client.event.CWIndexUpdateEvt;
import org.coc.tools.client.event.ClanAddEvt;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	//private final RpcManager rpcMgr;
	private final HandlerManager eventBus;
	private final Display display;
	
	public ClanEditPresenter(ClanServiceAsync rpcService,
			HandlerManager eventBus, Display display) {

		this.clan=new Clan();
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.clan = new Clan();
		this.display = display;
		bind();
	}
			
	@Override
	public void go(HasWidgets container) {

		/*bind();
		container.clear();
		container.add(display.asWidget());*/

		container.clear();
		container.add(display.asWidget());

	}
	
	public void bind() {

		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
		
	}
	private void doSave() {

		clan.setClanTag(display.getClanTag().getValue());
		clan.setClanName(display.getClanName().getValue());
		clan.setClanSymbol(display.getClanSymbol().getValue());
		rpcService.addClan(clan.getClanTag(), clan.getClanName(), clan.getClanSymbol(), Clan.REG_STATUS.REGED, new AsyncCallback<Clan>() {
			public void onSuccess(Clan result) {
				eventBus.fireEvent(new ClanAddEvt());
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error =>rpcService.update");
				GWT.log("Error =>rpcService.update", caught);
			}
		});
	}

}
