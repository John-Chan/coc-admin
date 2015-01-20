package org.coc.tools.client.view;

import org.coc.tools.client.widget.CWResultLessPanel;
//import org.coc.tools.client.widget.CWResultPanel;




import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.presenter.CWResultListPresenter;
import org.coc.tools.client.presenter.CWResultListPresenter.CWResultData;

public class CWResultListView extends BasicView implements CWResultListPresenter.Display{

	private final CWResultLessPanel	dataPanel;
	//private final HorizontalPanel	btnContainer;
	private final VerticalPanel	btnContainer;
	private final VerticalPanel	containerPanel;
	private final HorizontalPanel	menuPanel;
	private final Button 		btnLoadMore;
	private final Button 		btnReload;
	private final Button 		btnReturnHome;
	private final FlexTable btnTable;
	public CWResultListView(){
		dataPanel=new CWResultLessPanel();
		btnContainer=new VerticalPanel();
		menuPanel=new HorizontalPanel();
		containerPanel=new VerticalPanel();
		btnTable=new FlexTable();
		btnLoadMore=new Button("Load More Data");
		btnReload=new Button("Reload");
		btnReturnHome=new Button("Return Home");
		initLayout();
	}
	
	private void initLayout(){
		//btnTable.add(btnReturnHome);
		//btnTable.add(btnReload);
		//btnTable.add(btnLoadMore);
		GridHelper pusher=new GridHelper(btnTable);
		pusher.pushBack(new HTML("")).pushBack(btnReturnHome).pushBack(btnReload).pushBack(btnLoadMore).pushBack(new HTML(""));
		//GridHelper.HorizontalAlign.alignAllMirrorCenter(btnTable, 0);
		GridHelper.setColWidth(btnTable, new String[]{"50%","250px","250px","250px","50%"});
		menuPanel.add(btnTable);
		menuPanel.setWidth("100%");
		//menuPanel.add(btnReturnHome);
		//menuPanel.add(new HTML("    "));
		//menuPanel.add(btnReload);
		//menuPanel.add(new HTML("    "));
		//menuPanel.add(btnLoadMore);
		//btnContainer.add(btnLoadMore);
		btnReturnHome.setWidth("150px");
		btnReload.setWidth("150px");
		btnLoadMore.setWidth("150px");
		//btnReturnHome.setWidth("33%");
		//btnReload.setWidth("33%");
		//btnLoadMore.setWidth("33%");
		
		containerPanel.add(menuPanel);
		containerPanel.add(dataPanel);
		containerPanel.add(btnContainer);
		super.setCenter(containerPanel);
	}
	
	public CWResultLessPanel getDataPanel(){
		return this.dataPanel;
	}

	@Override
	public HasClickHandlers getLoadMoreBtn() {
		return btnLoadMore;
	}

	@Override
	public HasClickHandlers getReLoadBtn() {
		return btnReload;
	}

	@Override
	public HasClickHandlers getReturnHomeBtn() {
		return btnReturnHome;
	}

	@Override
	public void addData(CWResultData data) {
		Button btn=new Button("Detail");
		btn.addClickHandler(data.getClickHandlerForEdtCwRet());
		dataPanel.addData(data.getCwData(),btn);
		
	}

	@Override
	public void clearData() {
		dataPanel.clearData();
		
	}

	@Override
	public void enableLoadMore(boolean b) {
		btnLoadMore.setEnabled(b);
		
	}
}
