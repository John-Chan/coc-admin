package org.coc.tools.client.presenter;

import java.util.List;
import java.util.Map;

import org.coc.tools.client.RpcManager;
import org.coc.tools.client.presenter.ClanEditPresenter.Display;
import org.coc.tools.client.view.UiTestView;
import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.RpcResult;
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

public class UiTestPresenter implements Presenter {


	public interface Display {

		HasClickHandlers getTestRpcButton();
		String 				getText1();
		String 				getText2();
		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final Display display;
	private final RpcManager rpcMgr;
	private UiTestView	view=new UiTestView();
	
	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

	}

	public UiTestPresenter(RpcManager rpcMgr,HandlerManager eventBus, Display display){
		this.rpcMgr=rpcMgr;
		this.eventBus=eventBus;
		this.display=display;
	}
	public void bind() {

		this.display.getTestRpcButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//doImpData();
				//doSearch();
				doReputData();
			}
		});
		
		
	}
	private void	doReputData(){
		
		rpcMgr.getAdminToolServiceAsync().doRePutData(this.display.getText1(), this.display.getText2(), new AsyncCallback<RpcResult>(){

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Error "+caught.getMessage());
				
			}

			@Override
			public void onSuccess(RpcResult result) {
				String msg=result.getMsg();
				Window.alert(msg);
				
			}
			
		});
	}
	private void	doImpData(){
		rpcMgr.getAdminToolServiceAsync().doImpData(this.display.getText1(), this.display.getText2(), new AsyncCallback<RpcResult>(){

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Error "+caught.getMessage());
				
			}

			@Override
			public void onSuccess(RpcResult result) {
				String msg=result.getMsg();
				Window.alert(msg);
				
			}
			
		});
	}
	private void	doSearch(){
		String searchTxt=this.display.getText1();
		/*AsyncCallback<List<ClanWarEntryPojo>> callback=new AsyncCallback<List<ClanWarEntryPojo>>(){
			static String txt=this.display.getText1();
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error "+caught.getMessage());
				GWT.log("Error fetchCWIndexs.", caught);
				
			}

			@Override
			public void onSuccess(List<ClanWarEntryPojo> result) {
				String msg= "search "+txt +",total: "+result.size();
				for(ClanWarEntryPojo one:result){
					msg+="\r\n"+one.getWarIndex().getHomeClan().getClanName() +" VS "+one.getWarIndex().getEnemyClan().getClanName()+",Date="+one.getWarIndex().getPrepareDate();
				}
				
			}
			
		};*/
		rpcMgr.getClanWarEntryService().searchWarEntry(searchTxt, new AsyncCallback<List<ClanWarEntryPojo>>(){
			//static String txt=this.display.getText1();
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error "+caught.getMessage());
				GWT.log("Error fetchCWIndexs.", caught);
				
			}

			@Override
			public void onSuccess(List<ClanWarEntryPojo> result) {
				String msg= "total: "+result.size()+"\r\n";
				for(ClanWarEntryPojo one:result){
					msg+="\t"+one.getWarIndex().getHomeClan().getClanName() +" VS "+one.getWarIndex().getEnemyClan().getClanName()+",Date="+one.getWarIndex().getPrepareDate()+"\r\n";
				}
				Window.alert(msg);
			}
			
		});
	}
	private void	doGetImpDataInfo(){
		rpcMgr.getAdminToolServiceAsync().getImpDataInfo(this.display.getText1(), this.display.getText2(), new AsyncCallback<RpcData<Map<String,String>>>(){

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Error "+caught.getMessage());
				GWT.log("Error fetchCWIndexs.", caught);
				
			}

			@Override
			public void onSuccess(RpcData<Map<String, String>> result) {

				String msg=result.getMsg();
				Map<String, String> more=result.getData();
				//Map<String, String>
				if(more != null){
					msg += more.toString();
				}
				Window.alert(msg);
				//GWT.log("Error fetchCWIndexs.", caught);
				
			}
			//
		});
	}
}
