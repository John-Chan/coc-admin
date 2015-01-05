package org.coc.tools.client.widget;

import org.coc.tools.client.misc.ResHelper;
import org.coc.tools.client.view.UIConstants;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ClanInfoPanel {
	
	private	Clan clan=null;
	private HorizontalPanel	holder=new HorizontalPanel();
	private FlexTable container=new FlexTable();
	private Label lableClanTag=new Label("N/A");
	private Label lableClanName=new Label("N/A");
	private HTML imgClanSymbol=new HTML( ResHelper.makeImgHtml( ResHelper.getDefClanSymbolAbsUrl(),UIConstants.Px.IMG_WIDTH_32,UIConstants.Px.IMG_HIGHT_32 ));
	public ClanInfoPanel(){
		update(clan);
	}
	public ClanInfoPanel(Clan clan){
		update(clan);
	}
	public ClanInfoPanel(String tag,String name,String symbol){
		update( tag, name, symbol);
	}
	public	void update(Clan clan){

		this.clan=clan;
		initLayout();
		initData();
	}
	public	void update(String tag,String name,String symbol){

		this.clan=new Clan();
		this.clan.setClanTag(tag);
		this.clan.setClanName(name);
		this.clan.setClanSymbol(symbol);
		initLayout();
		initData();
	}
	public Widget getWidget(){
		return holder;
	}
	public Clan getClan(){
		return this.clan;
	}
	private void	initData(){
		if(clan != null){
			lableClanName.setText(clan.getClanName());
			lableClanTag.setText(clan.getClanTag());
			imgClanSymbol.setHTML(ResHelper.makeImgHtml( ResHelper.getDefClanSymbolAbsUrl(),UIConstants.Px.IMG_WIDTH_32,UIConstants.Px.IMG_HIGHT_32 ));
		}
	}
	private void	initLayout(){
		///////////////////////////////////////////////////
		/// layout 1 img;2 padding;3 
		///  img
		///  clan-name
		///  padding
		///  clan tag
		///
		///////////////////////////////////////////////////

		int rowIndex=0;
		container.setWidget(rowIndex, 0, imgClanSymbol);
		container.setWidget(rowIndex, 1, lableClanName);
		container.setWidget(rowIndex, 2, new HTML(""));
		container.setWidget(rowIndex, 3, lableClanTag);
		
		container.setWidth("100%");
		container.getCellFormatter().setWidth(rowIndex, 0, UIConstants.Px.IMG_WIDTH_32);
		container.getCellFormatter().setWidth(rowIndex, 1, UIConstants.Px.MAX_CLAN_NAME_WIDTH);
		container.getCellFormatter().setWidth(rowIndex, 2, "5px");
		container.getCellFormatter().setWidth(rowIndex, 3, UIConstants.Px.MAX_CLAN_TAG_WIDTH);
		
		for(int i=0;i<container.getCellCount(rowIndex);++i){

			container.getFlexCellFormatter().setVerticalAlignment(rowIndex, i,
					DockPanel.ALIGN_TOP);
			container.getWidget(rowIndex, i).setHeight("32px");
		}
		
		holder.add(container);
		
	}
}
