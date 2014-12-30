package org.coc.tools.client.presenter;

import java.util.Date;

import org.coc.tools.client.CWIndexServiceAsync;
import org.coc.tools.client.ClanWarEntryServiceAsync;
import org.coc.tools.client.event.CWIndexUpdateEvt;
import org.coc.tools.shared.model.CWIndex;
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

public class CWIndexEditPresenter implements Presenter {

	public interface Display {
		
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<Date> getPrepareDate();
		
		//HasValue<String> getDateFmtString();

		HasValue<String> getEnemyClanTag();

		HasValue<String> getEnemyClanName();

		HasValue<String> getEnemyClanSymbol();

		//HasValue<String> getHomeTeam();

		HasValue<String> getScope();

		Widget asWidget();
	}

	private ClanWarEntryPojo clanWarEntryPojo;
	private final ClanWarEntryServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public CWIndexEditPresenter(ClanWarEntryServiceAsync rpcService,
			HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.clanWarEntryPojo = new ClanWarEntryPojo();
		this.display = display;
		bind();
	}

	public CWIndexEditPresenter(ClanWarEntryServiceAsync rpcService,
			HandlerManager eventBus, Display display, Long id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getByWarId(id, new AsyncCallback<ClanWarEntryPojo>() {
			public void onSuccess(ClanWarEntryPojo result) {
				clanWarEntryPojo = result;
				CWIndex cwIndex=result.getWarIndex();
				//CWIndexEditPresenter.this.display.getDateFmtString().setValue(DateTimeHelper.SIMPLE_DATE_TIME_FMT_STRING);
				CWIndexEditPresenter.this.display.getPrepareDate().setValue(cwIndex.getPrepareDate() );
				
				
				CWIndexEditPresenter.this.display.getEnemyClanTag().setValue(
						cwIndex.getEnemyClan().getClanTag());
				CWIndexEditPresenter.this.display.getEnemyClanName().setValue(
						cwIndex.getEnemyClan().getClanName());
				CWIndexEditPresenter.this.display.getEnemyClanSymbol().setValue(
						cwIndex.getEnemyClan().getClanSymbol());
				
				CWIndexEditPresenter.this.display.getScope().setValue(
						Integer.toString(cwIndex.getScope()));
			}

			public void onFailure(Throwable caught) {

				Window.alert("Error getCWIndex.");
				GWT.log("Error getCWIndex.", caught);
			}

		});

	}

	public void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
			}
		});

		/*
		 * this.display.getCancelButton().addClickHandler(new ClickHandler() {
		 * public void onClick(ClickEvent event) { eventBus.fireEvent(new
		 * EditContactCancelledEvent()); } });
		 */
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

	}

	private void doSave() {

		CWIndex cwIndex=clanWarEntryPojo.getWarIndex();
		cwIndex.setPrepareDate(display.getPrepareDate().getValue());
		cwIndex.getEnemyClan().setClanTag(display.getEnemyClanTag().getValue());
		cwIndex.getEnemyClan().setClanName(display.getEnemyClanName().getValue());
		cwIndex.getEnemyClan().setClanSymbol(display.getEnemyClanSymbol().getValue());
		///
		cwIndex.getHomeClan().setClanTag("#88888888");
		cwIndex.getHomeClan().setClanName("nakama-ck");
		cwIndex.getHomeClan().setClanSymbol("40");
		///
		cwIndex.setScope(Integer.parseInt(display.getScope().getValue()) );

		rpcService.update(clanWarEntryPojo, new AsyncCallback<ClanWarEntryPojo>() {
			public void onSuccess(ClanWarEntryPojo result) {
				eventBus.fireEvent(new CWIndexUpdateEvt(result));
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error =>rpcService.update");
				GWT.log("Error =>rpcService.update", caught);
			}
		});
	}

}
