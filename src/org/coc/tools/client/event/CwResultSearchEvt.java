package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CwResultSearchEvt extends GwtEvent<CwResultSearchEvtHandler>{

	public static Type<CwResultSearchEvtHandler> TYPE = new Type<CwResultSearchEvtHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CwResultSearchEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CwResultSearchEvtHandler handler) {
		handler.onSearch(this);
		
	}
}
