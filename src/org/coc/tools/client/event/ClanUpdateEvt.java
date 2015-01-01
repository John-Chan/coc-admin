package org.coc.tools.client.event;

import org.coc.tools.shared.model.Clan;
import com.google.gwt.event.shared.GwtEvent;

public class ClanUpdateEvt   extends GwtEvent<ClanUpdateEvtHandler>{
	
	public static Type<ClanUpdateEvtHandler> TYPE = new Type<ClanUpdateEvtHandler>();
	private final Clan forUpdate;
	
	public ClanUpdateEvt(Clan forUpdate){
		this.forUpdate=forUpdate;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClanUpdateEvtHandler> getAssociatedType() {
		return TYPE;
	}

	
	@Override
	protected void dispatch(ClanUpdateEvtHandler handler) {
		handler.onUpdate(this);
		
	}

	public Clan getData() { return forUpdate; }
}
