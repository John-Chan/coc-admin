package org.coc.tools.client.view;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.presenter.CWResulSearchPresenter;
import org.coc.tools.client.presenter.CWResulSearchPresenter.CWResultData;
import org.coc.tools.client.widget.CWResultLessPanel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
public class CWResulSearchView extends BasicView implements CWResulSearchPresenter.Display{

	private final CWResultLessPanel	dataPanel;
	//private final HorizontalPanel	btnContainer;
	private final VerticalPanel	btnContainer;
	private final VerticalPanel	containerPanel;
	private final HorizontalPanel	menuPanel;
	private	final TextBox		searchTxt;
	private final Button 		searchBtn;
	private final Button 		btnReturnHome;
	private final FlexTable 	btnTable;
	public CWResulSearchView(){
		dataPanel=new CWResultLessPanel();
		btnContainer=new VerticalPanel();
		menuPanel=new HorizontalPanel();
		containerPanel=new VerticalPanel();
		btnTable=new FlexTable();
		searchTxt=new TextBox();
		searchTxt.setTitle("Clan Name/Clan Tag");
		searchBtn=new Button("Search");
		btnReturnHome=new Button("Return Home");
		initLayout();
	}
	private void initLayout(){
		GridHelper pusher=new GridHelper(btnTable);
		//pusher.pushBack(searchTxt).pushBack(searchBtn).pushBack(new HTML("")).pushBack(new HTML("")).pushBack(btnReturnHome);
		pusher.pushBack(searchTxt).pushBack(searchBtn).pushBack(btnReturnHome);
		//GridHelper.HorizontalAlign.alignAllMirrorCenter(btnTable, 0);
		GridHelper.setColWidth(btnTable, new String[]{"100%","150px","150px"});
		menuPanel.add(btnTable);
		menuPanel.setWidth("100%");
		btnReturnHome.setWidth("150px");
		searchTxt.setWidth("350px");
		searchBtn.setWidth("150px");
		
		containerPanel.add(btnContainer);
		containerPanel.add(menuPanel);
		containerPanel.add(dataPanel);
		super.setCenter(containerPanel);
	}

	public CWResultLessPanel getDataPanel(){
		return this.dataPanel;
	}
	
	@Override
	public HasClickHandlers getSearchBtn() {
		return searchBtn;
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
		//Window.scrollTo(0, Window.getScrollTop() + Window.getClientHeight());
		
	}

	@Override
	public void clearData() {
		dataPanel.clearData();
		
	}

	@Override
	public String getSearchTxt() {
		return searchTxt.getValue();
	}
	@Override
	public void enableSearchBtn(boolean enabled) {
		searchBtn.setEnabled(enabled);
		
	}
	@Override
	public void setSearchBtnTxt(String txt) {
		searchBtn.setText(txt);
		
	}

}
