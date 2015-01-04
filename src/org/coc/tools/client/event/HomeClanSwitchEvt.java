package org.coc.tools.client.event;

import org.coc.tools.shared.model.Clan;

import com.google.gwt.event.shared.GwtEvent;

public class HomeClanSwitchEvt  extends GwtEvent<HomeClanSwitchEvtHandler>{
	public static Type<HomeClanSwitchEvtHandler> TYPE = new Type<HomeClanSwitchEvtHandler>();

	private Clan	newClan;
	public HomeClanSwitchEvt( Clan newOne) {
		this.newClan=newOne;
	}
	public	Clan getClan(){
		return this.newClan;
	}
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<HomeClanSwitchEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(HomeClanSwitchEvtHandler handler) {
		handler.onSwitch(this);
		
	}

}
