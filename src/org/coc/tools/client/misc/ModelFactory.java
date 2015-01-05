package org.coc.tools.client.misc;

import org.coc.tools.shared.CocConstant;
import org.gwt.advanced.client.datamodel.ComboBoxDataModel;
import org.gwt.advanced.client.datamodel.IconItem;

public class ModelFactory {

	public enum CLAN_SYMBOL_IMG_SZIE{SZ_32_PIX,SZ_48_PIX,SZ_64_PIX}
	
	//public static final ClanSymbolModel32
	
	//itemIdPrefix can bu null or empty string,in this case a default value ('Clan-Symbol-Item-') used
	public static ComboBoxDataModel	createClanSymbolModel(CLAN_SYMBOL_IMG_SZIE sz,String itemIdPrefix){
		String prefix="Clan-Symbol-Item-";
		String labelPrefix="Symbol-";
		if(itemIdPrefix != null && itemIdPrefix.length()>0){
			prefix=itemIdPrefix;
		}
		ComboBoxDataModel model = new ComboBoxDataModel();
		switch(sz){
		case SZ_32_PIX:

			for(int i=CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE;i<=CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE;++i){
				String imgUrl=ResHelper.getClanSymbolIco32AbsUrl(Integer.toString(i));
				IconItem ico=new IconItem(imgUrl, labelPrefix+i); 
				model.add(prefix+i,ico );
			}
			break;
		case SZ_48_PIX:

			for(int i=CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE;i<=CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE;++i){
				String imgUrl=ResHelper.getClanSymbolIco48AbsUrl(Integer.toString(i));
				IconItem ico=new IconItem(imgUrl, labelPrefix+i); 
				model.add(prefix+i,ico );
			}
			break;
		case SZ_64_PIX:

			for(int i=CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE;i<=CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE;++i){
				String imgUrl=ResHelper.getClanSymbolIco64AbsUrl(Integer.toString(i));
				IconItem ico=new IconItem(imgUrl, labelPrefix+i); 
				model.add(prefix+i,ico );
			}
			break;
		}
		
		return model;
	}
	
}
