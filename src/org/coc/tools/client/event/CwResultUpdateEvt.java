package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

/// update has been done
public class CwResultUpdateEvt   extends GwtEvent<CwResultUpdateEvtHandler>{
	public static Type<CwResultUpdateEvtHandler> TYPE = new Type<CwResultUpdateEvtHandler>();

	public CwResultUpdateEvt( ) {

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CwResultUpdateEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CwResultUpdateEvtHandler handler) {
		handler.onUpdate(this);
		
	}

}
