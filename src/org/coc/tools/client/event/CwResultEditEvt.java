package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

/// go edit
public class CwResultEditEvt   extends GwtEvent<CwResultEditEvtHandler>{

	public static Type<CwResultEditEvtHandler> TYPE = new Type<CwResultEditEvtHandler>();
	private final Long warId;
	public CwResultEditEvt(Long warId ) {
		this.warId=warId;
	}

	public Long getWarId(){
		return this.warId;
	}
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CwResultEditEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CwResultEditEvtHandler handler) {
		handler.onEdit(this);
		
	}

}
