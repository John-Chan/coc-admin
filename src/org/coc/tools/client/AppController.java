package org.coc.tools.client;

import org.coc.tools.client.event.CWIndexAddEvt;
import org.coc.tools.client.event.CWIndexAddEvtHandler;
import org.coc.tools.client.event.CWIndexUpdateCancelEvt;
import org.coc.tools.client.event.CWIndexUpdateCancelEvtHandler;
import org.coc.tools.client.event.CWIndexUpdateEvt;
import org.coc.tools.client.event.CWIndexUpdateEvtHandler;
import org.coc.tools.client.event.ClanAddEvt;
import org.coc.tools.client.event.ClanAddEvtHandler;
import org.coc.tools.client.event.ClanUpdateCancelEvt;
import org.coc.tools.client.event.ClanUpdateCancelEvtHandler;
import org.coc.tools.client.event.ClanUpdateEvt;
import org.coc.tools.client.event.ClanUpdateEvtHandler;
import org.coc.tools.client.event.CwResultEditEvt;
import org.coc.tools.client.event.CwResultEditEvtHandler;
import org.coc.tools.client.event.CwResultListEvt;
import org.coc.tools.client.event.CwResultListEvtHandler;
import org.coc.tools.client.event.CwResultSearchEvt;
import org.coc.tools.client.event.CwResultSearchEvtHandler;
import org.coc.tools.client.event.CwResultUpdateCancelEvt;
import org.coc.tools.client.event.CwResultUpdateCancelEvtHandler;
import org.coc.tools.client.event.CwResultUpdateEvt;
import org.coc.tools.client.event.CwResultUpdateEvtHandler;
import org.coc.tools.client.event.HomeClanSwitchEvt;
import org.coc.tools.client.event.HomeClanSwitchEvtHandler;
import org.coc.tools.client.misc.CookieHelper;
import org.coc.tools.client.presenter.CWIndexEditPresenter;
import org.coc.tools.client.presenter.CWIndexPresenter;
import org.coc.tools.client.presenter.CWResulSearchPresenter;
import org.coc.tools.client.presenter.CWResultListPresenter;
import org.coc.tools.client.presenter.ClanEditPresenter;
import org.coc.tools.client.presenter.CWResultEditPresenter;
import org.coc.tools.client.presenter.Presenter;
import org.coc.tools.client.presenter.UiTestPresenter;
import org.coc.tools.client.view.CWIndexEditView;
import org.coc.tools.client.view.CWIndexView;
import org.coc.tools.client.view.CWResulSearchView;
import org.coc.tools.client.view.CWResultListView;
import org.coc.tools.client.view.ClanEditView;
import org.coc.tools.client.view.CWResultEditView;
import org.coc.tools.client.view.UiTestView;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	//private CWIndexServiceAsync cwIndexService = GWT.create(CWIndexService.class);
	private RpcManager	rpcMgr=new RpcManager();
	
	private HasWidgets container;

	private final Clan	homeClan;
	
	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		homeClan=new Clan();
		bind();
	}

	private boolean homeClanSetted(){
		if(
				homeClan.getClanTag() == null || 
				homeClan.getClanTag().isEmpty() ||
				homeClan.getClanName() == null || 
				homeClan.getClanName().isEmpty()
				){
			return false;
		}
		return true;
	}
	/// URL driven
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;
			if (token.equals(AppCmd.CMD_REDIRECT_TO_HOME)) {
				presenter = new CWIndexPresenter(rpcMgr, eventBus,
						new CWIndexView());
			} else if (token.equals(AppCmd.CMD_LIST_CW_ENTRY)) {
				presenter = new CWIndexPresenter(rpcMgr, eventBus,
						new CWIndexView());
			} 
			else if (token.equals(AppCmd.CMD_ADD_CW_ENTRY) && homeClanSetted()) {
				presenter = new CWIndexEditPresenter(rpcMgr.getClanWarEntryService(), eventBus,
						new CWIndexEditView(),homeClan);
			} else if (token.equals(AppCmd.CMD_EDIT_CW_ENTRY) && homeClanSetted()) {
				presenter = new CWIndexEditPresenter(rpcMgr.getClanWarEntryService(), eventBus,
						new CWIndexEditView(),homeClan);
			}else if (token.equals(AppCmd.CMD_ADD_REGED_CLAN)) {
				
				presenter = new ClanEditPresenter(rpcMgr.getClanServiceAsync(), eventBus,
						new ClanEditView());
			}else if (token.equals(AppCmd.CMD_SEARCH_WAR_RESULT)) {
				
				presenter = new CWResulSearchPresenter(rpcMgr, eventBus,
						new CWResulSearchView());
			}else if (token.equals(AppCmd.CMD_DEBUG_UI)) {
				
				presenter = new UiTestPresenter(rpcMgr,eventBus,new UiTestView());
			}else{
				///
				presenter = new CWIndexPresenter(rpcMgr, eventBus,
						new CWIndexView());
			}
			/*
			else if (token.equals(AppCmd.CMD_EDIT_CW_RESULT)) {
				presenter = new CWResultEditPresenter(rpcMgr, eventBus,new CWResultEditView());
			}
			*/
			//
			if (presenter != null) {
				presenter.go(container);
			}
		}

	}

	@Override
	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem(AppCmd.CMD_LIST_CW_ENTRY);
		} else {
			History.fireCurrentHistoryState();
		}
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(CwResultListEvt.TYPE, new CwResultListEvtHandler() {

			@Override
			public void onListCwResult(CwResultListEvt event) {
				if(event.getClanTag()==null){
					event.setClanTag(AppController.this.homeClan.getClanTag());
					doListCwResultLess(event.getClanTag());
				}
				
			}
		});
		
		eventBus.addHandler(HomeClanSwitchEvt.TYPE, new HomeClanSwitchEvtHandler() {
			@Override
			public void onSwitch(HomeClanSwitchEvt event) {
				//homeClan
				AppController.this.homeClan.copyFull(event.getClan()) ;
				CookieHelper.ensureHomeClanTagSaved(AppController.this.homeClan.getClanTag());
				doListCwEntry();
			}
		});
		
		/// reg evt for reg clan ClanAddEvt  extends GwtEvent<ClanAddEvtHandler>
		eventBus.addHandler(ClanAddEvt.TYPE, new ClanAddEvtHandler() {
			@Override
			public void onAdd(ClanAddEvt event) {
				doAddClan();

			}
		});
		
		/// reg evt for update&save
	    eventBus.addHandler(ClanUpdateEvt.TYPE,
	        new ClanUpdateEvtHandler() {
			@Override
			public void onUpdate(ClanUpdateEvt event) {
				doListCwEntry();
				
			}
	        });  
	    /// reg evt for update&cancle
	    eventBus.addHandler(ClanUpdateCancelEvt.TYPE,
	        new ClanUpdateCancelEvtHandler() {
	    	@Override  
	    	public void onCancel(ClanUpdateCancelEvt event) {
	        	  doListCwEntry();
	          }
	        }); 
	    
		/// reg evt for add 
		eventBus.addHandler(CWIndexAddEvt.TYPE, new CWIndexAddEvtHandler() {
			@Override
			public void onAdd(CWIndexAddEvt event) {
				doAddCwEntry();

			}
		});
		
		/// reg evt for cw log search 
		eventBus.addHandler(CwResultSearchEvt.TYPE, new CwResultSearchEvtHandler() {

			@Override
			public void onSearch(CwResultSearchEvt event) {
				goSearch();
				
			}
		});
		
		/*
		/// reg evt for update
	    eventBus.addHandler(CWIndexUpdateEvt.TYPE,
	        new CWIndexUpdateEvtHandler() {
			@Override
			public void onUpdate(CWIndexUpdateEvt event) {
				doCWIndexUpdated(event.getId());
			}
	        });  


		 */
		/// reg evt for update&cancle
	    eventBus.addHandler(CWIndexUpdateCancelEvt.TYPE,
	        new CWIndexUpdateCancelEvtHandler() {
	    	@Override  
	    	public void onCancel(CWIndexUpdateCancelEvt event) {
	        	  doListCwEntry();
	          }
	        });  
		
	   
	    

		/// reg evt for update&save
	    eventBus.addHandler(CWIndexUpdateEvt.TYPE,
	        new CWIndexUpdateEvtHandler() {
			@Override
			public void onUpdate(CWIndexUpdateEvt event) {
				doListCwEntry();
				
			}
	        }); 

		/// reg evt for do edit
	    eventBus.addHandler(CwResultEditEvt.TYPE,
	        new CwResultEditEvtHandler() {

			@Override
			public void onEdit(CwResultEditEvt event) {
				doEditCwResult(event.getWarId());
				
			}
	        }); 
	    
		/// reg evt for update&save
	    eventBus.addHandler(CwResultUpdateEvt.TYPE,
	        new CwResultUpdateEvtHandler() {
			@Override
			public void onUpdate(CwResultUpdateEvt event) {
				doListCwEntry();
				
			}
	        }); 
		/// reg evt for update&cancel
	    eventBus.addHandler(CwResultUpdateCancelEvt.TYPE,
	        new CwResultUpdateCancelEvtHandler() {
			@Override
			public void onCancel(CwResultUpdateCancelEvt event) {
				doListCwEntry();
				
			}
	        }); 

	}
	/// going to reg clan 
	private void doAddClan() {
		History.newItem(AppCmd.CMD_ADD_REGED_CLAN);
	}

	/// going to edit cw result 
	private void doEditCwResult(Long warId) {
		History.newItem(AppCmd.CMD_EDIT_CW_RESULT,false);
		Presenter presenter= new CWResultEditPresenter(rpcMgr, eventBus, new CWResultEditView(), warId);
		presenter.go(container);
	}
	/// going to add 
	private void doAddCwEntry() {
		History.newItem(AppCmd.CMD_ADD_CW_ENTRY);
	}

	private void goSearch() {
		History.newItem(AppCmd.CMD_SEARCH_WAR_RESULT);
	}

	/*
	/// going to edit
	private void doEditCWIndex(String id) {
	    History.newItem(AppCmd.CMD_EDIT_CWINDEX, false);
	    //public CWIndexEditPresenter(CWIndexServiceAsync rpcService,HandlerManager eventBus, Display display, Long id)
	    Presenter presenter = new CWIndexEditPresenter(rpcMgr.getClanWarEntryService(), eventBus, new CWIndexEditView(), Long.parseLong(id) );
	    presenter.go(container);
	  }
	
	/// saved or update has been canceled
	private void doEditCWIndexCancelled() {
		History.newItem(AppCmd.CMD_LIST_CWINDEX);
	}
	*/
	/// saved or update has been done
	private void doListCwEntry() {
		History.newItem(AppCmd.CMD_LIST_CW_ENTRY);
	}
	private void doListCwResultLess(String clanTag) {
		History.newItem(AppCmd.CMD_LIST_ALL_WAR_RESULT,false);
		Presenter presenter= new CWResultListPresenter(rpcMgr, eventBus, new CWResultListView(), clanTag);
		presenter.go(container);
	}
	/// saved or update has been done
	//private void doListClan() {
	//	History.newItem(AppCmd.CMD_LIST_CW_ENTRY);
	//}
}
