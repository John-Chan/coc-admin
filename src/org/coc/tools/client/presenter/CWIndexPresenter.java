package org.coc.tools.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.coc.tools.client.RpcManager;
import org.coc.tools.client.event.CWIndexAddEvt;
import org.coc.tools.client.event.ClanAddEvt;
import org.coc.tools.client.event.CwResultEditEvt;
import org.coc.tools.client.event.CwResultListEvt;
import org.coc.tools.client.event.CwResultSearchEvt;
import org.coc.tools.client.event.HomeClanSwitchEvt;
import org.coc.tools.client.misc.CookieHelper;
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

	public static  class CWIndexData {
		/*public interface DataWorker{
			void	doEditCwResult();
		}*/
		private CWIndex data = null;
		private boolean canBookBase = true;
		private boolean canUpdateWarResult = true;
		private boolean canUpdateWarDetail = true;
		private ClickHandler	clickHandlerForEdtCwRet=null;

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

		public ClickHandler getClickHandlerForEdtCwRet() {
			return clickHandlerForEdtCwRet;
		}

		public void setClickHandlerForEdtCwRet(ClickHandler clickHandlerForEdtCwRet) {
			this.clickHandlerForEdtCwRet = clickHandlerForEdtCwRet;
		}
		

		/*public DataWorker getEvtHandler() {
			return dataWorker;
		}

		public void setEvtHandler(DataWorker dataWorker) {
			this.dataWorker = dataWorker;
		}*/
		

	}

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getSearchButton();
		
		HasClickHandlers getListAllButton();

		HasClickHandlers getRegClanButton();

		HasClickHandlers getList();

		void setCwEntryList(List<CWIndexData> data);

		int getClickedCwEntryRow(ClickEvent event);

		List<Integer> getSelectedCwIndexRows();

		void setRegedClanList(List<Clan> data);

		int getSelectedRegClan();

		HasChangeHandlers getRegedClanBox();

		void setRegedClan(Clan clan);

		Widget asWidget();
	}

	private List<ClanWarEntryPojo> clanWarEntryPojoList;
	private List<Clan> regedClanList;

	private final RpcManager rpcMgr;
	private final HandlerManager eventBus;
	private final Display display;

	@SuppressWarnings("unused")
	private List<Clan> createClansForTest(int n) {
		ArrayList<Clan> list = new ArrayList<Clan>();
		for (int i = 0; i < n; ++i) {
			Clan one = new Clan();
			one.setClanName("clanName-" + i);
			one.setClanTag("#YYI8J6U" + i);
			one.setClanSymbol(Integer.toString(i + 1));
			list.add(one);
		}
		return list;
	}

	public CWIndexPresenter(RpcManager rpcMgr, HandlerManager eventBus,
			Display view) {
		this.rpcMgr = rpcMgr;
		this.eventBus = eventBus;
		this.display = view;
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
		// TODL: sort in server side
	}

	public void bind() {

		display.getListAllButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CwResultListEvt());
			}
		});

		
		display.getRegedClanBox().addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int index = display.getSelectedRegClan();
				if(index >=0 && index < regedClanList.size()){
					switchHomeClan(regedClanList.get(index),true,true);
				}
				
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

		display.getSearchButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CwResultSearchEvt());
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

	
	private void setDefaultHomeClan() {
		String cookie=CookieHelper.getHomeClanTag();
		Clan forSwitch=null;
		if(regedClanList.size() > 0){
			if(cookie==null ){
				forSwitch=regedClanList.get(0);
			}else{
				int found=CookieHelper.tagExists(cookie, regedClanList);
				if(found==-1){
					forSwitch=regedClanList.get(0);
					//
				}else{
					forSwitch=regedClanList.get(found);
				}
			}
		}

		if(forSwitch!=null){
			switchHomeClan(forSwitch,true,true);
		}
		/*if (HOME_CLAN_NOT_SELECTED == currentClan && regedClanList.size() > 0) {
			currentClan = 0;
			switchHomeClan(currentClan,true,true);
		}*/
	}

	private void fetchRegedClans() {
		rpcMgr.getClanServiceAsync().getRegedClanList(10,
				new AsyncCallback<List<Clan>>() {
					@Override
					public void onSuccess(List<Clan> result) {
						regedClanList = result;
						display.setRegedClanList(regedClanList);
						setDefaultHomeClan();
					}

					@Override
					public void onFailure(Throwable caught) {

						Window.alert("Rpc Error.");
						GWT.log("Rpc Error.", caught);
					}
				});
	}

	//
	private void fetchCWIndexs(Clan clan) {
		rpcMgr.getClanWarEntryService().getListByClanTag(clan.getClanTag(), 10,
				new AsyncCallback<List<ClanWarEntryPojo>>() {
					public void onSuccess(List<ClanWarEntryPojo> result) {
						clanWarEntryPojoList = result;
						sortClanWarEntryPojoList();
						List<CWIndexData> data = new ArrayList<CWIndexData>();

						for (int i = 0; i < result.size(); ++i) {

							ClanWarEntryPojo fullInfo=clanWarEntryPojoList.get(i);
							CWIndex one = fullInfo.getWarIndex();
							CWIndexData forView = new CWIndexData();
							forView.setClickHandlerForEdtCwRet(createClickHandlerForCwRetEdt(one));
							boolean canUpdate=fullInfo.getEnemyClanWarResult().isLocked()&&fullInfo.getHomeClanWarResult().isLocked();
							forView.setCanUpdateWarResult(!canUpdate);
							forView.setData(one);
							// / TODO: TIMEZEON PROBLEN
							if (new Date().after(one.getEndDate())) {
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

	/*private void fetchCWIndexs() {
		rpcMgr.getClanWarEntryService().getList(25,
				new AsyncCallback<List<ClanWarEntryPojo>>() {
					public void onSuccess(List<ClanWarEntryPojo> result) {
						clanWarEntryPojoList = result;
						sortClanWarEntryPojoList();
						List<CWIndexData> data = new ArrayList<CWIndexData>();

						for (int i = 0; i < result.size(); ++i) {

							final CWIndex one = clanWarEntryPojoList.get(i)
									.getWarIndex();
							CWIndexData forView = new CWIndexData();
							forView.setClickHandlerForEdtCwRet(createClickHandlerForCwRetEdt(one));
							forView.setData(one);
							// / TODO: TIMEZEON PROBLEN
							if (new Date().after(one.getEndDate())) {
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
	}*/

	private void switchHomeClan(Clan clan , boolean fireEvt, boolean updateCwList) {

		display.setRegedClan(clan);
		if (updateCwList)
			fetchCWIndexs(clan);
		if (fireEvt)
			eventBus.fireEvent(new HomeClanSwitchEvt(clan));
	}

	private ClickHandler	createClickHandlerForCwRetEdt(final CWIndex cwIndex){
		return new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CwResultEditEvt(cwIndex.getRowId()));
			}
		};
	}
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
