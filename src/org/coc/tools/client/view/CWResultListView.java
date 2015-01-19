package org.coc.tools.client.view;

import org.coc.tools.client.widget.CWResultPanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CWResultListView extends BasicView {

	private final CWResultPanel	dataPanel;
	//private final HorizontalPanel	btnContainer;
	private final VerticalPanel	btnContainer;
	private final VerticalPanel	containerPanel;
	private final Button 		btnLoadMore;
	public CWResultListView(){
		dataPanel=new CWResultPanel();
		btnContainer=new VerticalPanel();
		containerPanel=new VerticalPanel();
		btnLoadMore=new Button("Load More Data");
		initLayout();
	}
	
	private void initLayout(){
		//btnLoadMore.setWidth("90%");
		btnContainer.add(btnLoadMore);
		containerPanel.add(dataPanel);
		containerPanel.add(btnContainer);
		super.setCenter(containerPanel);
	}
	
	public CWResultPanel getDataPanel(){
		return this.dataPanel;
	}
}
