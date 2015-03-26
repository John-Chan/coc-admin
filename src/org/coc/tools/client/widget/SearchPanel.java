package org.coc.tools.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchPanel  implements IsWidget{

	private	final TextBox		searchTxt;
	private final Button searchBtn;
	private final HorizontalPanel	holder;
	public	SearchPanel(){
		searchTxt=new TextBox();
		searchTxt.setTitle("Clan Name/Clan Tag");
		searchBtn=new Button("Search War Result");
		holder=new HorizontalPanel();
		initLayout();
	}
	@Override
	public Widget asWidget() {
		return holder;
	}
	public TextBox getSearchTxt() {
		return searchTxt;
	}
	public Button getSearchBtn() {
		return searchBtn;
	}
	private void	initLayout(){
		holder.clear();
		holder.setWidth("100%");
		holder.add(searchTxt);
		holder.add(searchBtn);
	}
}
