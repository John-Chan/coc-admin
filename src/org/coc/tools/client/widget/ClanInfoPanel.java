package org.coc.tools.client.widget;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.view.UiSizeConstants;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ClanInfoPanel extends ClanInfoWidget {
	
	private	Clan clan=null;
	private HorizontalPanel	holder=new HorizontalPanel();
	private FlexTable container=new FlexTable();
	private Label lableClanTag=new Label("N/A");
	private Label lableClanName=new Label("N/A");
	private HTML imgClanSymbol=new HTML( getSymbolImg32Html(null));
	public ClanInfoPanel(){
		update(clan);
	}
	public ClanInfoPanel(Clan clan){
		update(clan);
	}
	public ClanInfoPanel(String tag,String name,String symbol){
		update( tag, name, symbol);
	}
	@Override
	public	void update(Clan clan){

		this.clan=clan;
		initLayout();
		initData();
	}
	@Override
	public	void update(String tag,String name,String symbol){

		this.clan=new Clan();
		this.clan.setClanTag(tag);
		this.clan.setClanName(name);
		this.clan.setClanSymbol(symbol);
		initLayout();
		initData();
	}
	@Override
	public Widget getWidget(){
		return holder;
	}
	@Override
	public Clan getClan(){
		return this.clan;
	}
	private void	initData(){
		if(clan != null){
			lableClanName.setText(clan.getClanName());
			lableClanTag.setText(clan.getClanTag());
			imgClanSymbol.setHTML(getSymbolImg32Html(clan.getClanSymbol()));
		}
	}
	private void	initLayout(){

		holder.clear();
		GridHelper pusher=new GridHelper(container);
		pusher.pushBack(imgClanSymbol).pushBack(new HTML(""))
		.pushBack(lableClanName).pushBack(new HTML(""))
		.pushBack(lableClanTag);

		container.setWidth("100%");
		
		GridHelper.VerticalAlign.alignAllToTop(container);
		GridHelper.setColWidth(container, new String[]{UiSizeConstants.Px.IMG_WIDTH_32,"2px",UiSizeConstants.Px.MAX_CLAN_NAME_WIDTH,"2px",UiSizeConstants.Px.MAX_CLAN_TAG_WIDTH});

		holder.add(container);
		
	}
}
