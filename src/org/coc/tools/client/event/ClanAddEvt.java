package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ClanAddEvt  extends GwtEvent<ClanAddEvtHandler>{

	public static Type<ClanAddEvtHandler> TYPE = new Type<ClanAddEvtHandler>();

	public ClanAddEvt( ) {
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClanAddEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClanAddEvtHandler handler) {
		handler.onAdd(this);
		
	}

}
