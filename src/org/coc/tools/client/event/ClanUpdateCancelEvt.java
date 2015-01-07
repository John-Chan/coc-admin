package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ClanUpdateCancelEvt    extends GwtEvent<ClanUpdateCancelEvtHandler>{

	public static Type<ClanUpdateCancelEvtHandler> TYPE = new Type<ClanUpdateCancelEvtHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClanUpdateCancelEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClanUpdateCancelEvtHandler handler) {
		handler.onCancel(this);
		
	}

}
