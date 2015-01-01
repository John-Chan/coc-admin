package org.coc.tools.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.coc.tools.client.CWIndexServiceAsync;
import org.coc.tools.client.ClanWarEntryServiceAsync;
import org.coc.tools.client.RpcManager;
import org.coc.tools.client.event.CWIndexAddEvt;
import org.coc.tools.client.event.ClanAddEvt;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CWIndexPresenter implements Presenter {

	public class CWIndexData{
		private CWIndex	data=null;
		private boolean	canBookBase=true;
		private boolean	canUpdateWarResult=true;
		private boolean canUpdateWarDetail=true;
		public CWIndex getData() {
			return data;
		}
		public void setData(CWIndex data) {
			this.data = data;
		}
		public boolean isCanBookBase() {
			return canBookBase;
		}
		public void setCanBookBase(boolean canBookBase) {
			this.canBookBase = canBookBase;
		}
		public boolean isCanUpdateWarResult() {
			return canUpdateWarResult;
		}
		public void setCanUpdateWarResult(boolean canUpdateWarResult) {
			this.canUpdateWarResult = canUpdateWarResult;
		}
		public boolean isCanUpdateWarDetail() {
			return canUpdateWarDetail;
		}
		public void setCanUpdateWarDetail(boolean canUpdateWarDetail) {
			this.canUpdateWarDetail = canUpdateWarDetail;
		}
		
	}
	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();
		
		HasClickHandlers getRegClanButton();

		HasClickHandlers getList();
		
		void setCwEntryList(List<CWIndexData> data);
		
		int getClickedCwEntryRow(ClickEvent event);
		

		List<Integer> getSelectedCwIndexRows();

		void setRegedClanList(List<Clan> data);
		int getSelectedRegClan();
		HasChangeHandlers	getRegedClanBox();
		
		void setRegedClan(Clan clan);
		
		Widget asWidget();
	}

	private List<ClanWarEntryPojo> clanWarEntryPojoList;
	private List<Clan> regedClanList;
	private int currentClan;
	//private final CWIndexServiceAsync rpcService;
	//private final ClanWarEntryServiceAsync rpcService;
	private final RpcManager rpcMgr;
	private final HandlerManager eventBus;
	private final Display display;


	private List<Clan>	createClansForTest(int n){
		ArrayList<Clan> list=new ArrayList<Clan>();
		for(int i=0;i<n;++i){
			Clan one=new Clan();
			one.setClanName("clanName-"+i);
			one.setClanTag("#YYI8J6U"+i);
			one.setClanSymbol(Integer.toString(i+1));
			list.add(one);
		}
		return list;
	}
	
	public CWIndexPresenter(RpcManager rpcMgr,
			HandlerManager eventBus, Display view) {
		this.rpcMgr = rpcMgr;
		this.eventBus = eventBus;
		this.display = view;
		this.currentClan=-1;
	}

	public List<ClanWarEntryPojo> getClanWarEntryPojoList() {
		return clanWarEntryPojoList;
	}

	public ClanWarEntryPojo getClanWarEntryPojo(int index) {
		return clanWarEntryPojoList.get(index);
	}

	public void setClanWarEntryPojoList(List<ClanWarEntryPojo> list) {
		this.clanWarEntryPojoList = list;
	}


	@Override
	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchRegedClans();
	}
	
	public void sortClanWarEntryPojoList() {
		//TODL: sort in server side
	}

	public void bind() {

		display.getRegedClanBox().addChangeHandler(new ChangeHandler() {
		      public void onChange(ChangeEvent event) {
		    	 currentClan=display.getSelectedRegClan();
		    	 display.setRegedClan(regedClanList.get(currentClan));
		  		 fetchCWIndexs(regedClanList.get(currentClan));
		      }
		    });
		
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CWIndexAddEvt());
			}
		});
		
		display.getRegClanButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ClanAddEvt());
			}
		});
		
		/*
		 * display.getAddButton().addClickHandler(new ClickHandler() { public
		 * void onClick(ClickEvent event) { eventBus.fireEvent(new
		 * AddContactEvent()); } });
		 * 
		 * display.getDeleteButton().addClickHandler(new ClickHandler() { public
		 * void onClick(ClickEvent event) { deleteSelectedContacts(); } });
		 * 
		 * display.getList().addClickHandler(new ClickHandler() { public void
		 * onClick(ClickEvent event) { int selectedRow =
		 * display.getClickedRow(event);
		 * 
		 * if (selectedRow >= 0) { String id =
		 * contacts.get(selectedRow).getId(); eventBus.fireEvent(new
		 * EditContactEvent(id)); } } });
		 */
	}
	private void fetchRegedClans() {
		rpcMgr.getClanServiceAsync().getRegedClanList(10, new AsyncCallback<ArrayList<Clan>>(){


			@Override
			public void onSuccess(ArrayList<Clan> result) {
				regedClanList=result;
				
			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Error fetchCWIndexs.");
				GWT.log("Error fetchCWIndexs.", caught);
			}
		});
	}
	private void fetchCWIndexs(Clan clan) {
		rpcMgr.getClanWarEntryService().getList(25, new AsyncCallback<ArrayList<ClanWarEntryPojo>>() {
			public void onSuccess(ArrayList<ClanWarEntryPojo> result) {
				clanWarEntryPojoList = result;
				sortClanWarEntryPojoList();
				List<CWIndexData> data=new ArrayList<CWIndexData>();
				

				for (int i = 0; i < result.size(); ++i) {

					CWIndex one=clanWarEntryPojoList.get(i).getWarIndex();
					CWIndexData forView=new CWIndexData();
					forView.setData(one);
					/// TODO: TIMEZEON PROBLEN
					if(new Date().after(one.getEndDate())){
						forView.setCanBookBase(false);
					}
					
					data.add(forView);
				}

				display.setCwEntryList(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetchCWIndexs.");
				GWT.log("Error fetchCWIndexs.", caught);
			}
		});
	}
	
	/*
	private void fetchCWIndexs() {
		rpcService.getCWIndexList(10, new AsyncCallback<ArrayList<CWIndex>>() {
			public void onSuccess(ArrayList<CWIndex> result) {
				cwIndexList = result;
				sortCwIndexList();
				List<List<String>> data=new ArrayList<List<String>>();
				List<String> title = new ArrayList<String>();
				/// put title
				title.add("Enemy clan tag");
				title.add("Enemy clan name");
				title.add("Enemy clan symobl");
				title.add("Prepare Date");
				data.add(title);
				/// put row
				

				for (int i = 0; i < result.size(); ++i) {

					List<String> row = new ArrayList<String>();

					row.add(cwIndexList.get(i).getEnemyClan().getClanTag());
					row.add(cwIndexList.get(i).getEnemyClan().getClanName());
					row.add(cwIndexList.get(i).getEnemyClan().getClanSymbol());
					row.add(cwIndexList.get(i).getPrepareDate().toString());
					
					data.add(row);
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetchCWIndexs.");
				GWT.log("Error fetchCWIndexs.", caught);
			}
		});
	}

	 */
	private void deleteSelectedCWIndex() {
		/*
		 * List<Integer> selectedRows = display.getSelectedRows();
		 * ArrayList<String> ids = new ArrayList<String>();
		 * 
		 * for (int i = 0; i < selectedRows.size(); ++i) {
		 * ids.add(contactDetails.get(selectedRows.get(i)).getId()); }
		 * 
		 * rpcService.deleteContacts(ids, new
		 * AsyncCallback<ArrayList<ContactDetails>>() { public void
		 * onSuccess(ArrayList<ContactDetails> result) { contactDetails =
		 * result; List<String> data = new ArrayList<String>();
		 * 
		 * for (int i = 0; i < result.size(); ++i) {
		 * data.add(contactDetails.get(i).getDisplayName()); }
		 * 
		 * display.setData(data); }
		 * 
		 * public void onFailure(Throwable caught) { ... } });
		 */
	}
}
