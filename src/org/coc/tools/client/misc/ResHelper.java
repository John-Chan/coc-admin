package org.coc.tools.client.misc;

import com.google.gwt.core.client.GWT;

public class ResHelper {

	public static String getClanSymbolAbsUrl(String number){
		//// http://127.0.0.1:8888/res/img/clan-symbols/Symbol_1.png
		return GWT.getHostPageBaseURL()+"res/img/clan-symbols/Symbol_"+number+".png";
	}
	
	//default.png
	public static String getDefClanSymbolAbsUrl(){
		//// http://127.0.0.1:8888/res/img/clan-symbols/Symbol_1.png
		return GWT.getHostPageBaseURL()+"res/img/clan-symbols/"+"default.png";
	}
	
	/// e.g. makeImgHtml("xxx.png","32px","32px")
	public static String makeImgHtml(String imgUrl,String width,String height){
		//return "<img src='" + imgUrl + "' style='width:304px;height:228px'>";
		return "<img src='" + imgUrl + "' style='width:"+width+";height:"+height+"'>";
		//picSpace.setHTML("<img src='" + result.get(i).getUrl() + "' style='width:304px;height:228px'>");
	}
}
