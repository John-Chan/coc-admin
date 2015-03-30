package org.coc.tools.client.presenter;

import org.coc.tools.client.ClanServiceAsync;
import org.coc.tools.client.event.ClanUpdateCancelEvt;
import org.coc.tools.client.event.ClanUpdateEvt;
import org.coc.tools.shared.FieldVerifier;
import org.coc.tools.shared.VerifieStatus;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ClanEditPresenter implements Presenter {

	public interface Display {

		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();
		
		void	enableSave(boolean enable);

		String getClanTag();

		String getClanName();

		String getClanSymbol();

		void setClanTag(String val);

		void setClanName(String val);

		void setClanSymbol(String val);

		Widget asWidget();
	}

	private Clan clan;
	private final ClanServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public ClanEditPresenter(ClanServiceAsync rpcService,
			HandlerManager eventBus, Display display) {

		this.clan=new Clan();
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.clan = new Clan();
		this.display = display;
		
	}
			
	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

	}
	
	public void bind() {

		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
		
		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doCancel();
			}
		});
		
	}
	private void doSave() {


		VerifieStatus stat=checkInput();
		if(!stat.getPassed()){
			Window.alert(stat.getMsg());
			return;
		}
		
		ClanEditPresenter.this.display.enableSave(false);
		
		clan.setClanTag(display.getClanTag());
		clan.setClanName(display.getClanName());
		clan.setClanSymbol(display.getClanSymbol());
		rpcService.addClan(clan.getClanTag(), clan.getClanName(), clan.getClanSymbol(), Clan.REG_STATUS.REGED, new AsyncCallback<Clan>() {
			public void onSuccess(Clan result) {
				ClanEditPresenter.this.display.enableSave(true);
				eventBus.fireEvent(new ClanUpdateEvt(result));
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error =>rpcService.update");
				GWT.log("Error =>rpcService.update", caught);
				ClanEditPresenter.this.display.enableSave(true);
			}
		});
	}
	private VerifieStatus checkInput(){
		VerifieStatus stat=new VerifieStatus();
		stat.setPassed(true);
		String clanName=display.getClanName();
		String clantag=display.getClanTag();
		String clanSymbol=display.getClanSymbol();
	
		stat=FieldVerifier.isValidClanTag(clantag);
		
		if(stat.getPassed()){
			stat=FieldVerifier.isValidClanName(clanName);
		}
		if(stat.getPassed()){
			stat=FieldVerifier.isValidClanSymbol(clanSymbol);
		}
	
		
		return stat;

	}
	
	private void doCancel(){
		eventBus.fireEvent(new ClanUpdateCancelEvt());
	}

}
