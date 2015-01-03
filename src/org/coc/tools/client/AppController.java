package org.coc.tools.client;

import org.coc.tools.client.event.CWIndexAddEvt;
import org.coc.tools.client.event.CWIndexAddEvtHandler;
import org.coc.tools.client.event.CWIndexUpdateEvt;
import org.coc.tools.client.event.CWIndexUpdateEvtHandler;
import org.coc.tools.client.event.ClanAddEvt;
import org.coc.tools.client.event.ClanAddEvtHandler;
import org.coc.tools.client.event.ClanUpdateEvt;
import org.coc.tools.client.event.ClanUpdateEvtHandler;
import org.coc.tools.client.presenter.CWIndexEditPresenter;
import org.coc.tools.client.presenter.CWIndexPresenter;
import org.coc.tools.client.presenter.ClanEditPresenter;
import org.coc.tools.client.presenter.Presenter;
import org.coc.tools.client.view.CWIndexEditView;
import org.coc.tools.client.view.CWIndexView;
import org.coc.tools.client.view.ClanEditView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	//private CWIndexServiceAsync cwIndexService = GWT.create(CWIndexService.class);
	private RpcManager	rpcMgr=new RpcManager();
	
	private HasWidgets container;

	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	/// URL driven
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;
			/*if (token.equals(AppCmd.CMD_LIST_REGED_CLAN)) {
				presenter = new CWIndexPresenter(rpcMgr, eventBus,
						new CWIndexView());
			} else */
			if (token.equals(AppCmd.CMD_LIST_CW_ENTRY)) {
				presenter = new CWIndexPresenter(rpcMgr, eventBus,
						new CWIndexView());
			} else if (token.equals(AppCmd.CMD_ADD_CW_ENTRY)) {
				presenter = new CWIndexEditPresenter(rpcMgr.getClanWarEntryService(), eventBus,
						new CWIndexEditView());
			} else if (token.equals(AppCmd.CMD_EDIT_CW_ENTRY)) {
				presenter = new CWIndexEditPresenter(rpcMgr.getClanWarEntryService(), eventBus,
						new CWIndexEditView());
			} else if (token.equals(AppCmd.CMD_ADD_REGED_CLAN)) {
				
				presenter = new ClanEditPresenter(rpcMgr.getClanServiceAsync(), eventBus,
						new ClanEditView());
				//presenter = new CWIndexPresenter(rpcMgr, eventBus,
				//		new CWIndexView());
			}

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
	    
		/// reg evt for add 
		eventBus.addHandler(CWIndexAddEvt.TYPE, new CWIndexAddEvtHandler() {
			@Override
			public void onAdd(CWIndexAddEvt event) {
				doAddCwEntry();

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


		
		/// reg evt for update&cancle
	    eventBus.addHandler(EditContactCancelledEvent.TYPE,
	        new EditContactCancelledEventHandler() {
	          public void onEditContactCancelled(EditContactCancelledEvent event) {
	            doEditContactCancelled();
	          }
	        });  
		
	    */
	    

		/// reg evt for update&save
	    eventBus.addHandler(CWIndexUpdateEvt.TYPE,
	        new CWIndexUpdateEvtHandler() {
			@Override
			public void onUpdate(CWIndexUpdateEvt event) {
				doListCwEntry();
				
			}
	        });  

	}
	/// going to reg clan 
	private void doAddClan() {
		History.newItem(AppCmd.CMD_ADD_REGED_CLAN);
	}
	/// going to add 
	private void doAddCwEntry() {
		History.newItem(AppCmd.CMD_ADD_CW_ENTRY);
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
	/// saved or update has been done
	private void doListClan() {
		History.newItem(AppCmd.CMD_LIST_CW_ENTRY);
	}
}
