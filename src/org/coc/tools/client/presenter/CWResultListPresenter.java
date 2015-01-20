package org.coc.tools.client.presenter;

import java.util.List;

import org.coc.tools.client.RpcManager;
import org.coc.tools.client.event.CWIndexUpdateCancelEvt;
import org.coc.tools.client.event.CwResultEditEvt;
import org.coc.tools.shared.QueryPage;
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

public class CWResultListPresenter implements Presenter {

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
		HasClickHandlers getLoadMoreBtn();

		HasClickHandlers getReLoadBtn();
		
		HasClickHandlers getReturnHomeBtn();
		
		void			addData(CWResultData data);
		
		void			clearData();
		
		void			enableLoadMore(boolean b);
		
		Widget asWidget();
	}
	
	private final RpcManager rpcMgr;
	private final HandlerManager eventBus;
	private final Display display;
	private final String clanTag;
	private int pageNumber=0;
	
	public CWResultListPresenter(RpcManager rpcMgr, HandlerManager eventBus,
			Display view,String clanTag){
		this.rpcMgr = rpcMgr;
		this.eventBus = eventBus;
		this.display = view;
		this.clanTag=clanTag;
	}
	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		loadNextPage();

	}
	private void bind() {
		display.getLoadMoreBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadNextPage();
			}
		});
		display.getReLoadBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pageNumber=0;
				CWResultListPresenter.this.display.enableLoadMore(true);
				CWResultListPresenter.this.display.clearData();
				loadNextPage();
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
	private void loadNextPage(){
		CWResultListPresenter.this.display.enableLoadMore(false);
		rpcMgr.getClanWarEntryService().getPageByClanTag(clanTag, pageNumber, new AsyncCallback<QueryPage<ClanWarEntryPojo>>(){

			@Override
			public void onFailure(Throwable caught) {
				CWResultListPresenter.this.display.enableLoadMore(true);
				Window.alert("Rpc Error.");
				GWT.log("Rpc Error.", caught);
			}

			@Override
			public void onSuccess(QueryPage<ClanWarEntryPojo> result) {
				// TODO Auto-generated method stub
				CWResultListPresenter.this.pageNumber++;
				List<ClanWarEntryPojo> dataList=result.getResultSet();
				for(ClanWarEntryPojo one:dataList){
					CWResultData cwData=new CWResultData();
					cwData.setClickHandlerForEdtCwRet(createClickHandlerForCwRetEdt(one.getWarIndex()));
					cwData.setCwData(one);
					CWResultListPresenter.this.display.addData(cwData);
				}
				CWResultListPresenter.this.display.enableLoadMore(result.moreRow());
				
			}
			//
		});
	}

}
