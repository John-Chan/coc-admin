package org.coc.tools.client.widget;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ClanInfoPanelMiddle extends ClanInfoWidget{

	private	Clan clan=null;
	private HorizontalPanel	holder=new HorizontalPanel();
	private FlexTable txtContainer=new FlexTable();
	private Label lableClanTag=new Label("N/A");
	private Label lableClanName=new Label("N/A");
	private HTML imgClanSymbol=new HTML( getSymbolImg64Html(null));
	private boolean useLeftSideAlign=true;
	public ClanInfoPanelMiddle(){
		update(clan);
	}
	public ClanInfoPanelMiddle(Clan clan){
		update(clan);
	}
	public ClanInfoPanelMiddle(String tag,String name,String symbol){
		update( tag, name, symbol);
	}
	
	@Override
	public void update(Clan clan) {
		this.clan=clan;
		initLayout();
		initData();
	}

	@Override
	public void update(String tag, String name, String symbol) {
		this.clan=new Clan();
		this.clan.setClanTag(tag);
		this.clan.setClanName(name);
		this.clan.setClanSymbol(symbol);
		update(this.clan);
		
	}

	@Override
	public Widget getWidget() {
		return holder;
	}

	@Override
	public Clan getClan() {
		return this.clan;
	}
	
	private void	initData(){
		if(clan != null){
			lableClanName.setText(clan.getClanName());
			lableClanTag.setText(clan.getClanTag());
			imgClanSymbol.setHTML(getSymbolImg64Html(clan.getClanSymbol()));
		}
	}
	private void	initLayout(){

		holder.clear();
		
		GridHelper pusher=new GridHelper(txtContainer);
		/*pusher.pushBack(imgClanSymbol).pushBack(new HTML(""))
		.pushBack(lableClanName).pushBack(new HTML(""))
		.pushBack(lableClanTag);*/
		pusher.pushBack(lableClanName);
		pusher.nextRow().pushBack(new HTML(""));
		pusher.nextRow().pushBack(lableClanTag);

		txtContainer.setWidth("100%");
		
		//GridHelper.VerticalAlign.alignAllToTop(container);
		//GridHelper.setColWidth(container, new String[]{UiSizeConstants.Px.IMG_WIDTH_32,"2px",UiSizeConstants.Px.MAX_CLAN_NAME_WIDTH,"2px",UiSizeConstants.Px.MAX_CLAN_TAG_WIDTH});

		//
		if(useLeftSideAlign){
			holder.add(imgClanSymbol);
			holder.add(txtContainer);
			lableClanName.setHorizontalAlignment(DockPanel.ALIGN_LEFT);
			lableClanTag.setHorizontalAlignment(DockPanel.ALIGN_LEFT);
		}else{
			holder.add(txtContainer);
			holder.add(imgClanSymbol);
			lableClanName.setHorizontalAlignment(DockPanel.ALIGN_RIGHT);
			lableClanTag.setHorizontalAlignment(DockPanel.ALIGN_RIGHT);
		}
		
	}
	
	//return old value
	public boolean	resetHorizontalStyle(boolean useLeftSideAlign){
		boolean old=this.useLeftSideAlign;
		this.useLeftSideAlign=useLeftSideAlign;
		update(this.clan);
		return old;
	}

}
