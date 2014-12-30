package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CWIndexAddEvt extends GwtEvent<CWIndexAddEvtHandler> {

	public static Type<CWIndexAddEvtHandler> TYPE = new Type<CWIndexAddEvtHandler>();
	//private final String id;

	public CWIndexAddEvt(/*String id*/) {
		//this.id = id;
	}

	//public String getId() {
	//	return id;
	//}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CWIndexAddEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CWIndexAddEvtHandler handler) {
		handler.onAdd(this);

	}

}
