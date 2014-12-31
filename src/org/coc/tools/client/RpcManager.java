package org.coc.tools.client;

import com.google.gwt.core.client.GWT;

public class RpcManager {

	private ClanWarEntryServiceAsync clanWarEntryService = GWT.create(ClanWarEntryService.class);
	private ClanServiceAsync clanServiceAsync = GWT.create(ClanService.class);
	
	public ClanWarEntryServiceAsync getClanWarEntryService() {
		return clanWarEntryService;
	}
	public void setClanWarEntryService(ClanWarEntryServiceAsync clanWarEntryService) {
		this.clanWarEntryService = clanWarEntryService;
	}
	public ClanServiceAsync getClanServiceAsync() {
		return clanServiceAsync;
	}
	public void setClanServiceAsync(ClanServiceAsync clanServiceAsync) {
		this.clanServiceAsync = clanServiceAsync;
	}
	
	
}
