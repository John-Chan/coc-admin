package org.coc.tools.client;

import org.coc.tools.client.event.CWIndexAddEvt;
import org.coc.tools.client.event.CWIndexAddEvtHandler;
import org.coc.tools.client.event.CWIndexUpdateEvt;
import org.coc.tools.client.event.CWIndexUpdateEvtHandler;
import org.coc.tools.client.presenter.CWIndexEditPresenter;
import org.coc.tools.client.presenter.CWIndexPresenter;
import org.coc.tools.client.presenter.Presenter;
import org.coc.tools.client.view.CWIndexEditView;
import org.coc.tools.client.view.CWIndexView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	//private CWIndexServiceAsync cwIndexService = GWT.create(CWIndexService.class);
	private ClanWarEntryServiceAsync clanWarEntryService = GWT.create(ClanWarEntryService.class);
	
	
	private HasWidgets container;

	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals(AppCmd.CMD_LIST_CWINDEX)) {
				presenter = new CWIndexPresenter(clanWarEntryService, eventBus,
						new CWIndexView());
			} else if (token.equals(AppCmd.CMD_ADD_CWINDEX)) {
				presenter = new CWIndexEditPresenter(clanWarEntryService, eventBus,
						new CWIndexEditView());
			} else if (token.equals(AppCmd.CMD_EDIT_CWINDEX)) {
				presenter = new CWIndexEditPresenter(clanWarEntryService, eventBus,
						new CWIndexEditView());
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
			History.newItem(AppCmd.CMD_LIST_CWINDEX);
		} else {
			History.fireCurrentHistoryState();
		}
	}

	private void bind() {
		History.addValueChangeHandler(this);

		/// reg evt for add 
		eventBus.addHandler(CWIndexAddEvt.TYPE, new CWIndexAddEvtHandler() {
			@Override
			public void onAdd(CWIndexAddEvt event) {
				doAddNewCWIndex();

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
				doCWIndexUpdated();
				
			}
	        });  

	}

	/// go to add 
	private void doAddNewCWIndex() {
		History.newItem(AppCmd.CMD_ADD_CWINDEX);
	}
	/// go to edit
	private void doEditCWIndex(String id) {
	    History.newItem(AppCmd.CMD_EDIT_CWINDEX, false);
	    //public CWIndexEditPresenter(CWIndexServiceAsync rpcService,HandlerManager eventBus, Display display, Long id)
	    Presenter presenter = new CWIndexEditPresenter(clanWarEntryService, eventBus, new CWIndexEditView(), Long.parseLong(id) );
	    presenter.go(container);
	  }
	
	/// cancel in edit view
	private void doEditCWIndexCancelled() {
		History.newItem(AppCmd.CMD_LIST_CWINDEX);
	}

	/// saved in edit view
	private void doCWIndexUpdated() {
		History.newItem(AppCmd.CMD_LIST_CWINDEX);
	}
}
