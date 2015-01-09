package org.coc.tools.client.widget;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ClanInfoPanelEx extends ClanInfoWidget{

	public enum SIZE_STYLE{SMALL,MIDDLE,BIG};
	public enum HORIZONTAL_STYLE{LEFT_SIDE_ALIGN,RIGHT_SIDE_ALIGN};
	
	private	Clan clan=null;
	private HorizontalPanel	holder=new HorizontalPanel();
	private FlexTable txtContainer=new FlexTable();
	private Label lableClanTag=new Label("N/A");
	private Label lableClanName=new Label("N/A");
	private HTML imgClanSymbol=new HTML( getSymbolImg64Html(null));
	private HORIZONTAL_STYLE horizontalStyle=HORIZONTAL_STYLE.LEFT_SIDE_ALIGN;
	private SIZE_STYLE		sizeStyle=SIZE_STYLE.MIDDLE;
	private boolean showTag=true;
	public ClanInfoPanelEx(){
		update(clan);
	}
	public ClanInfoPanelEx(SIZE_STYLE sizeStyle){
		this.sizeStyle=sizeStyle;
		update(clan);
	}
	public ClanInfoPanelEx(Clan clan){
		update(clan);
	}
	public ClanInfoPanelEx(String tag,String name,String symbol){
		update( tag, name, symbol);
	}
	
	public SIZE_STYLE getSizeStyle() {
		return sizeStyle;
	}
	public void setSizeStyle(SIZE_STYLE sizeStyle) {
		this.sizeStyle = sizeStyle;
	}
	@Override
	public void update() {
		update(this.clan);
		
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
	public Widget asWidget() {
		return holder;
	}

	@Override
	public Clan getClan() {
		return this.clan;
	}

	@Override
	public void setBorderWidth(int size) {
		holder.setBorderWidth(size);
		
	}
	
	private String getSymbolImg(String symbol){
		//sizeStyle getSymbolImg64Html(clan.getClanSymbol())
		if(SIZE_STYLE.BIG == this.sizeStyle){
			return getSymbolImg64Html(symbol);
		}else if(SIZE_STYLE.SMALL == this.sizeStyle){
			return getSymbolImg32Html(symbol);
		}else{
			return getSymbolImg48Html(symbol);
		}
	}
	
	private void	initData(){
		if(clan != null){
			lableClanName.setText(clan.getClanName());
			lableClanTag.setText(clan.getClanTag());
			imgClanSymbol.setHTML(getSymbolImg(clan.getClanSymbol()));
		}
	}
	private void	initLayout(){

		holder.clear();
		txtContainer.clear();
		GridHelper pusher=new GridHelper(txtContainer);
		/*pusher.pushBack(imgClanSymbol).pushBack(new HTML(""))
		.pushBack(lableClanName).pushBack(new HTML(""))
		.pushBack(lableClanTag);*/
		pusher.pushBack(lableClanName);
		if(this.showTag){
			if(SIZE_STYLE.BIG == this.sizeStyle /*|| SIZE_STYLE.MIDDLE == this.sizeStyle*/){
				pusher.nextRow().pushBack(new HTML(""));
			}
			pusher.nextRow().pushBack(lableClanTag);
		}

		txtContainer.setWidth("100%");
		
		//GridHelper.VerticalAlign.alignAllToTop(container);
		//GridHelper.setColWidth(container, new String[]{UiSizeConstants.Px.IMG_WIDTH_32,"2px",UiSizeConstants.Px.MAX_CLAN_NAME_WIDTH,"2px",UiSizeConstants.Px.MAX_CLAN_TAG_WIDTH});

		//
		if(HORIZONTAL_STYLE.LEFT_SIDE_ALIGN == horizontalStyle){
			holder.add(imgClanSymbol);
			holder.add(txtContainer);
			lableClanName.setHorizontalAlignment(DockPanel.ALIGN_LEFT);
			lableClanTag.setHorizontalAlignment(DockPanel.ALIGN_LEFT);
			/*if(this.showTag){
				lableClanTag.setHorizontalAlignment(DockPanel.ALIGN_LEFT);
			}*/
		}else{
			holder.add(txtContainer);
			holder.add(imgClanSymbol);
			lableClanName.setHorizontalAlignment(DockPanel.ALIGN_RIGHT);
			lableClanTag.setHorizontalAlignment(DockPanel.ALIGN_RIGHT);
			/*if(this.showTag){
				lableClanTag.setHorizontalAlignment(DockPanel.ALIGN_RIGHT);
			}*/
		}
		
	}
	
	//return old value HorizontalStyle.LEFT_SIDE_ALIGN == horizontalStyle
	public HORIZONTAL_STYLE	resetHorizontalStyle(HORIZONTAL_STYLE horizontalStyle){
		HORIZONTAL_STYLE old=this.horizontalStyle;
		this.horizontalStyle=horizontalStyle;
		update(this.clan);
		return old;
	}
	@Override
	public boolean showTag(boolean show) {
		boolean old=this.showTag;
		this.showTag=show;
		return old;
	}

}
