package org.coc.tools.client.event;

import org.coc.tools.shared.model.ClanWarEntryPojo;

import com.google.gwt.event.shared.GwtEvent;

public class CWIndexUpdateEvt  extends GwtEvent<CWIndexUpdateEvtHandler>{

	  public static Type<CWIndexUpdateEvtHandler> TYPE = new Type<CWIndexUpdateEvtHandler>();
	  private final ClanWarEntryPojo forUpdate;
	  
	  public CWIndexUpdateEvt(ClanWarEntryPojo forUpdate) {
	    this.forUpdate = forUpdate;
	  }
	  
	  public ClanWarEntryPojo getUpdatedClanWarEntryPojo() { return forUpdate; }
	  

	  @Override
	  public Type<CWIndexUpdateEvtHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(CWIndexUpdateEvtHandler handler) {
	    handler.onUpdate(this);
	  }
	  

}
