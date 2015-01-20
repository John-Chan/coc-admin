package org.coc.tools.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CwResultListEvt extends GwtEvent<CwResultListEvtHandler>{

	public static Type<CwResultListEvtHandler> TYPE = new Type<CwResultListEvtHandler>();
	private  String clanTag=null;
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CwResultListEvtHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CwResultListEvtHandler handler) {
		handler.onListCwResult(this);
		
	}
	public CwResultListEvt(){
	}
	public CwResultListEvt(String clanTag){
		this.clanTag=clanTag;
	}
	
	public String	getClanTag(){
		return this.clanTag;
	}
	public void		setClanTag(String clanTag){
		this.clanTag=clanTag;
	}
}
