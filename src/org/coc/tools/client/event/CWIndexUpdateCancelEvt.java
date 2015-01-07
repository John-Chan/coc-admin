package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CWIndexUpdateCancelEvt    extends GwtEvent<CWIndexUpdateCancelEvtHandler>{

	public static Type<CWIndexUpdateCancelEvtHandler> TYPE = new Type<CWIndexUpdateCancelEvtHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CWIndexUpdateCancelEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CWIndexUpdateCancelEvtHandler handler) {
		handler.onCancel(this);
		
	}

}
