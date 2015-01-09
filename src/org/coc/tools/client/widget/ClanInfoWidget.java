package org.coc.tools.client.widget;

import org.coc.tools.client.misc.ResHelper;
import org.coc.tools.client.view.UiSizeConstants;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.ui.IsWidget;

public abstract class ClanInfoWidget implements IsWidget{

	public	abstract void update();
	public	abstract void update(Clan clan);
	public	abstract void update(String tag,String name,String symbol);
	public	abstract boolean showTag(boolean show);
	//public 	abstract Widget getWidget();
	public 	abstract Clan getClan();
	public 	abstract void setBorderWidth(int size);
	 
	public static String getSymbolImg32Html(String clanSymbol){
		if(clanSymbol==null || clanSymbol.length()<=0){
			return ResHelper.makeImgHtml( ResHelper.getDefClanSymbolAbsUrl(),UiSizeConstants.Px.IMG_WIDTH_32,UiSizeConstants.Px.IMG_HIGHT_32 );
		}
		return ResHelper.makeImgHtml( ResHelper.getClanSymbolIco32AbsUrl(clanSymbol),UiSizeConstants.Px.IMG_WIDTH_32,UiSizeConstants.Px.IMG_HIGHT_32 );
	}
	public static String getSymbolImg64Html(String clanSymbol){
		if(clanSymbol==null || clanSymbol.length()<=0){
			return ResHelper.makeImgHtml( ResHelper.getDefClanSymbolAbsUrl(),UiSizeConstants.Px.IMG_WIDTH_64,UiSizeConstants.Px.IMG_HIGHT_64 );
		}
		return ResHelper.makeImgHtml( ResHelper.getClanSymbolIco64AbsUrl(clanSymbol),UiSizeConstants.Px.IMG_WIDTH_64,UiSizeConstants.Px.IMG_HIGHT_64 );
	}
	public static String getSymbolImg48Html(String clanSymbol){
		if(clanSymbol==null || clanSymbol.length()<=0){
			return ResHelper.makeImgHtml( ResHelper.getDefClanSymbolAbsUrl(),UiSizeConstants.Px.IMG_WIDTH_48,UiSizeConstants.Px.IMG_HIGHT_48 );
		}
		return ResHelper.makeImgHtml( ResHelper.getClanSymbolIco48AbsUrl(clanSymbol),UiSizeConstants.Px.IMG_WIDTH_48,UiSizeConstants.Px.IMG_HIGHT_48 );
	}
}
