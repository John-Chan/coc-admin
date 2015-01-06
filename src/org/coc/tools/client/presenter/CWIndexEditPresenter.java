package org.coc.tools.client.presenter;

import java.util.Date;

import org.coc.tools.client.CWIndexServiceAsync;
import org.coc.tools.client.ClanWarEntryServiceAsync;
import org.coc.tools.client.event.CWIndexUpdateEvt;
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

@SuppressWarnings("unused")
public class CWIndexEditPresenter implements Presenter {

	public interface Display {
		
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		/*
		HasValue<Date> getPrepareDate();

		HasValue<String> getEnemyClanTag();

		HasValue<String> getEnemyClanName();

		HasValue<String> getEnemyClanSymbol();
		
		HasValue<String> getHomeClanTag();

		HasValue<String> getHomeClanName();

		HasValue<String> getHomeClanSymbol();
		HasValue<String> getScope();
		*/
		Date getPrepareDate();

		String getEnemyClanTag();

		String getEnemyClanName();

		String getEnemyClanSymbol();
		
		String getScope();

		void setPrepareDate(Date val);

		void setEnemyClanTag(String val);

		void setEnemyClanName(String val);

		void setEnemyClanSymbol(String val);
		
		void setScope(String val);
		
		void		setHomeClan(String tag,String name,String symbol);

		//HasValue<String> getHomeTeam();

		

		Widget asWidget();
	}

	private Clan homeClan;
	private ClanWarEntryPojo clanWarEntryPojo;
	private final ClanWarEntryServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public CWIndexEditPresenter(ClanWarEntryServiceAsync rpcService,
			HandlerManager eventBus, Display display,Clan homeClan) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.clanWarEntryPojo = new ClanWarEntryPojo();
		this.display = display;
		this.homeClan=homeClan;
		this.display.setHomeClan(homeClan.getClanTag(), homeClan.getClanName(), homeClan.getClanSymbol());
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
				homeClan=cwIndex.getHomeClan();
				//CWIndexEditPresenter.this.display.getDateFmtString().setValue(DateTimeHelper.SIMPLE_DATE_TIME_FMT_STRING);
				CWIndexEditPresenter.this.display.setPrepareDate(cwIndex.getPrepareDate() );
				
				
				CWIndexEditPresenter.this.display.setEnemyClanTag(
						cwIndex.getEnemyClan().getClanTag());
				CWIndexEditPresenter.this.display.setEnemyClanName(
						cwIndex.getEnemyClan().getClanName());
				CWIndexEditPresenter.this.display.setEnemyClanSymbol(
						cwIndex.getEnemyClan().getClanSymbol());
				
				CWIndexEditPresenter.this.display.setScope(
						Integer.toString(cwIndex.getScope()));
				CWIndexEditPresenter.this.display.setHomeClan(homeClan.getClanTag(), homeClan.getClanName(), homeClan.getClanSymbol());
				
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
		cwIndex.setPrepareDate(display.getPrepareDate() );
		cwIndex.getEnemyClan().setClanTag(display.getEnemyClanTag() );
		cwIndex.getEnemyClan().setClanName(display.getEnemyClanName() );
		cwIndex.getEnemyClan().setClanSymbol(display.getEnemyClanSymbol() );
		
		cwIndex.setHomeClan(homeClan);
		///
		//cwIndex.getHomeClan().setClanTag("#88888888");
		//cwIndex.getHomeClan().setClanName("nakama-ck");
		//cwIndex.getHomeClan().setClanSymbol("40");
		///
		cwIndex.setScope(Integer.parseInt(display.getScope() ) );

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
