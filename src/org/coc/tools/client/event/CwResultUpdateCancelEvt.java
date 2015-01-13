package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CwResultUpdateCancelEvt   extends GwtEvent<CwResultUpdateCancelEvtHandler>{
	public static Type<CwResultUpdateCancelEvtHandler> TYPE = new Type<CwResultUpdateCancelEvtHandler>();

	public CwResultUpdateCancelEvt( ) {
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CwResultUpdateCancelEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CwResultUpdateCancelEvtHandler handler) {
		handler.onCancel(this);
		
	}

}
