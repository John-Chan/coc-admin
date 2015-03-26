package org.coc.tools.client.presenter;

import java.util.List;

import org.coc.tools.client.RpcManager;
import org.coc.tools.client.event.CWIndexUpdateCancelEvt;
import org.coc.tools.client.event.CwResultEditEvt;
import org.coc.tools.client.widget.CWResultLessPanel;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.ClanWarEntryPojo;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CWResulSearchPresenter implements Presenter {


	public static  class CWResultData {
		private ClanWarEntryPojo	cwData;
		private ClickHandler	clickHandlerForEdtCwRet=null;

		public ClanWarEntryPojo getCwData() {
			return cwData;
		}

		public void setCwData(ClanWarEntryPojo cwData) {
			this.cwData = cwData;
		}

		public ClickHandler getClickHandlerForEdtCwRet() {
			return clickHandlerForEdtCwRet;
		}

		public void setClickHandlerForEdtCwRet(ClickHandler clickHandlerForEdtCwRet) {
			this.clickHandlerForEdtCwRet = clickHandlerForEdtCwRet;
		}
		
	}
	public interface Display {
		HasClickHandlers getSearchBtn();
		
		HasClickHandlers getReturnHomeBtn();
		
		void			addData(CWResultData data);
		
		void			clearData();
		
		String			getSearchTxt();
		
		void			enableSearchBtn(boolean enabled);
		void			setSearchBtnTxt(String txt);
		
		Widget asWidget();
	}
	private final String	TXT_IN_SEARCHING="Searching...";
	private final String	TXT_NOT_SEARCHING="Search";
	private final RpcManager rpcMgr;
	private final HandlerManager eventBus;
	private final Display display;
	public CWResulSearchPresenter(RpcManager rpcMgr, HandlerManager eventBus,
			Display view){
		this.rpcMgr = rpcMgr;
		this.eventBus = eventBus;
		this.display = view;
	}
	
	@Override
	public void go(HasWidgets container) {

		bind();
		container.clear();
		container.add(display.asWidget());

	}
	
	private void bind() {
		display.getSearchBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSearch();
			}
		});
		display.getReturnHomeBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CWIndexUpdateCancelEvt());
			}
		});
	}
	private ClickHandler	createClickHandlerForCwRetEdt(final CWIndex cwIndex){
		return new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CwResultEditEvt(cwIndex.getRowId()));
			}
		};
	}
	private void	doSearch(){
		String searchTxt=CWResulSearchPresenter.this.display.getSearchTxt();
		CWResulSearchPresenter.this.display.setSearchBtnTxt(TXT_IN_SEARCHING);
		CWResulSearchPresenter.this.display.enableSearchBtn(false);
		rpcMgr.getClanWarEntryService().searchWarEntry(searchTxt, new AsyncCallback<List<ClanWarEntryPojo>>(){
			//static String txt=this.display.getText1();
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error "+caught.getMessage());
				GWT.log("Error fetchCWIndexs.", caught);
				CWResulSearchPresenter.this.display.enableSearchBtn(true);
				CWResulSearchPresenter.this.display.setSearchBtnTxt(TXT_NOT_SEARCHING);
				
			}

			@Override
			public void onSuccess(List<ClanWarEntryPojo> result) {
				/*String msg= "total: "+result.size()+"\r\n";
				for(ClanWarEntryPojo one:result){
					msg+="\t"+one.getWarIndex().getHomeClan().getClanName() +" VS "+one.getWarIndex().getEnemyClan().getClanName()+",Date="+one.getWarIndex().getPrepareDate()+"\r\n";
				}
				Window.alert(msg);*/
				
				CWResulSearchPresenter.this.display.clearData();
				List<ClanWarEntryPojo> dataList=result;
				for(ClanWarEntryPojo one:dataList){
					CWResultData cwData=new CWResultData();
					cwData.setClickHandlerForEdtCwRet(createClickHandlerForCwRetEdt(one.getWarIndex()));
					cwData.setCwData(one);
					CWResulSearchPresenter.this.display.addData(cwData);
				}
				CWResulSearchPresenter.this.display.enableSearchBtn(true);
				CWResulSearchPresenter.this.display.setSearchBtnTxt(TXT_NOT_SEARCHING);
			}
			
		});
	}

}
