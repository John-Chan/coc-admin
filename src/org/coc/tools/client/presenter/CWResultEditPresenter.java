package org.coc.tools.client.presenter;

import org.coc.tools.client.RpcManager;
import org.coc.tools.client.event.CwResultUpdateEvt;
import org.coc.tools.client.event.CwResultUpdateCancelEvt;
import org.coc.tools.shared.RpcResult;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarResult;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CWResultEditPresenter implements Presenter {

	public interface Display {

		HasClickHandlers getSaveButton();

		HasClickHandlers getSaveAndLockButton();
		
		HasClickHandlers getCancelButton();

		ClanWarEntryPojo	getCwData();
		void				setCwData(ClanWarEntryPojo val);
		void				enableUpdate(boolean val);
		/*
		WarResult getHomeClanWarResult();

		WarResult getEnemyClanWarResult();

		void setHomeClanWarResult(WarResult val);

		void setEnemyClanWarResult(WarResult val);

		CWIndex	getCwIndex();
		void	setCwIndex(CWIndex val);
		 */
		Widget asWidget();
	}
	
	//private WarResult homeClanWarResult=new WarResult();
	//private WarResult enemyClanWarResult=new WarResult();
	//private CWIndex		warIndex=new CWIndex();

	ClanWarEntryPojo	currentCwData=new ClanWarEntryPojo();
	private final RpcManager rpcMgr;
	private final HandlerManager eventBus;
	private final Display display;
	
	public CWResultEditPresenter(RpcManager rpcMgr,
			HandlerManager eventBus, Display display, Long warId){
		this.rpcMgr=rpcMgr;
		this.eventBus=eventBus;
		this.display = display;
		initDataForEdit(warId);
		bind();
		
	}
	/*
	public CWResultEditPresenter(RpcManager rpcMgr,
			HandlerManager eventBus, Display display){
		this.rpcMgr=rpcMgr;
		this.eventBus=eventBus;
		this.display = display;
		bind();
		
	}
	*/
	

	private void	initDataForEdit(Long warId){
		rpcMgr.getClanWarEntryService().getByWarId(warId, new AsyncCallback<ClanWarEntryPojo>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error =>getByWarId");
				GWT.log("Error =>getByWarId", caught);
				
			}

			@Override
			public void onSuccess(ClanWarEntryPojo result) {
				currentCwData=result;
				CWResultEditPresenter.this.display.setCwData(result);
				CWResultEditPresenter.this.display.enableUpdate(canUpdate(result));
				/*
				CWResultEditPresenter.this.warIndex=result.getWarIndex();
				CWResultEditPresenter.this.homeClanWarResult=result.getHomeClanWarResult();
				CWResultEditPresenter.this.enemyClanWarResult=result.getEnemyClanWarResult();
				CWResultEditPresenter.this.display.setCwIndex(warIndex);
				CWResultEditPresenter.this.display.setHomeClanWarResult(homeClanWarResult);
				CWResultEditPresenter.this.display.setEnemyClanWarResult(enemyClanWarResult);
				*/
			}
			//
		});
	}

	private void	saveWarResult(Long warId,WarResult homeTeam,WarResult enemyTeam){
		rpcMgr.getClanWarEntryService().updateWarResultByWarId(warId, homeTeam, enemyTeam, new AsyncCallback<RpcResult>(){

			//Long Id=warId;
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error =>saveWarResult");
				GWT.log("Error =>saveWarResult", caught);
				
			}

			@Override
			public void onSuccess(RpcResult result) {
				eventBus.fireEvent( new CwResultUpdateEvt());
				
			}
			//
		});
	}
	/*
	private void	fetchWarIndex(final Long warId,CWIndex result){
		
	}
	private void	fetchWarResult(Long warId,WarResult homeTeam,WarResult enemyTeam){
		//
	}
	*/
	private void	doSave(boolean locked){
		if(!canUpdate(currentCwData)) return;
		currentCwData=display.getCwData();
		currentCwData.getHomeClanWarResult().setLocked(locked);
		currentCwData.getEnemyClanWarResult().setLocked(locked);
		saveWarResult(currentCwData.getWarIndex().getRowId(),currentCwData.getHomeClanWarResult(),currentCwData.getEnemyClanWarResult());
	}
	private boolean canUpdate(final ClanWarEntryPojo data){
		return (!currentCwData.getEnemyClanWarResult().isLocked())&&(!currentCwData.getHomeClanWarResult().isLocked());
	}
	private void	doCancel(){

		eventBus.fireEvent( new CwResultUpdateCancelEvt());
	}
	public void bind() {

		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave(false);
			}
		});
		this.display.getSaveAndLockButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave(true);
			}
		});
		
		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doCancel();
			}
		});
		
	}
	@Override
	public void go(HasWidgets container) {

		container.clear();
		container.add(display.asWidget());

	}
}
