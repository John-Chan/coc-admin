package org.coc.tools.client.view;

import java.util.ArrayList;
import java.util.List;








//import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
//import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import org.coc.tools.client.presenter.CWIndexPresenter;
import org.coc.tools.client.presenter.CWIndexPresenter.CWIndexData;
import org.coc.tools.shared.DateTimeFmt;

//public class CWIndexView extends Composite implements CWIndexPresenter.Display {
public class CWIndexView extends BasicView implements CWIndexPresenter.Display {
	private final String	appVersion="V0.1";
	private final Button addButton;
	private final Button deleteButton;
	private FlexTable cwIndexsTable;
	private final FlexTable contentTable;


	private final FlexTable headerTable;
	private final Button createClanButton;
	private Label homeClanName;
	private Label homeClanTag;
	private ListBox homeClanList;

	
	public CWIndexView() {

		////
		headerTable=new FlexTable();
		createClanButton=new Button("Create");
		homeClanName=new Label("ClanName");
		homeClanTag=new Label("ClanTag");
		homeClanList = new ListBox(false);

		for(int i=0;i<5;++i){
			homeClanList.addItem("Clan - "+i, Integer.toString(i));
		}
		////

		addButton = new Button("Add");
		deleteButton = new Button("Delete");
		contentTable = new FlexTable();
		cwIndexsTable = new FlexTable();
		
		initMainArea();
		initHearderBar();
	}

	private void initHearderBar(){

		headerTable.setWidth("100%");
		headerTable.getCellFormatter().setWidth(0, 0, "10%");
		headerTable.getCellFormatter().setWidth(0, 1, "10%");
		//headerTable.getCellFormatter().setWidth(0, 2, "55%");
		headerTable.getCellFormatter().setWidth(0, 3, "15%");
		headerTable.getCellFormatter().setWidth(0, 4, "5%");
		
		for(int i=0;i<4;++i){

			headerTable.getFlexCellFormatter().setVerticalAlignment(0, i,
					DockPanel.ALIGN_TOP);
			/*
			headerTable.getFlexCellFormatter().setVerticalAlignment(0, 1,
					DockPanel.ALIGN_TOP);
			headerTable.getFlexCellFormatter().setVerticalAlignment(0, 2,
					DockPanel.ALIGN_TOP);
			*/
		}
		headerTable.setWidget(0, 0, homeClanName);
		headerTable.setWidget(0, 1, homeClanTag);
		//headerTable.setWidget(0, 2, bull);
		headerTable.setWidget(0, 3, homeClanList);
		headerTable.setWidget(0, 4, createClanButton);
		
		this.setHeaderSmall(headerTable);
		//this.setHeader(headerTable);//
	}

	private void initMainArea(){

		//initWidget(contentTableDecorator);
		DecoratorPanel contentTableDecorator = new DecoratorPanel();

		HorizontalPanel menuPanel = new HorizontalPanel();
		contentTableDecorator.setWidth("100%");

		contentTable.setWidth("100%");
		// TODO : contentTable.getCellFormatter().addStyleName(0, 0,
		// "contacts-ListContainer");
		//
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);

		// Create the menu
		/*
		FlowPanel menuPanel=new FlowPanel();
		menuPanel.setWidth("20%");
		*/
		
		menuPanel.setBorderWidth(0);
		menuPanel.setSpacing(0);
		//menuPanel.setWidth("100%");
		menuPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		
	
		menuPanel.add(addButton);
		menuPanel.add(deleteButton);
		//addButton.setWidth("40 pix");
		//deleteButton.setWidth("40 pix");
		
		
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(0, 0, menuPanel);

		// Create the contacts list
		//
		cwIndexsTable.setCellSpacing(0);
		cwIndexsTable.setCellPadding(0);
		cwIndexsTable.setWidth("100%");
		cwIndexsTable.addStyleName("contacts-ListContents");
		cwIndexsTable.getColumnFormatter().setWidth(0, "15px");
		contentTable.setWidget(1, 0, cwIndexsTable);

		contentTableDecorator.add(contentTable);

		this.setCenter(contentTableDecorator); 
	}
	@Override
	public HasClickHandlers getAddButton() {
		return this.addButton;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return this.deleteButton;
	}

	@Override
	public HasClickHandlers getList() {
		return this.cwIndexsTable;
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = cwIndexsTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;
	}

	@Override
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();

		for (int i = 0; i < cwIndexsTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox) cwIndexsTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}

		return selectedRows;
	}

	public Widget asWidget() {
		return this;
	}


	@Override
	public void setData(List<CWIndexData> data) {
		cwIndexsTable.removeAllRows();

		int tabRowIndex=0;
		int tabColIndex=0;
		/// set title
		cwIndexsTable.setWidget(tabRowIndex, tabColIndex++, new CheckBox());
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, ViewConstants.ValueNames.ENEMY_CLAN_TAG); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, ViewConstants.ValueNames.ENEMY_CLAN_NAME); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, ViewConstants.ValueNames.ENEMY_CLAN_SYMBOL); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, ViewConstants.ValueNames.WAR_PREPARE_DATE); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, ""); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, ""); 
		tabRowIndex++;

		
		for (int i = 0; i < data.size(); ++i) {
			tabColIndex=0;
			CWIndexData aRow=data.get(i);
			Button bookBtn=new Button("Book a base");
			Button resultBtn=new Button("View Or Edit Result");
			
			if(!aRow.isCanBookBase()){
				bookBtn.setEnabled(false);
			}

			cwIndexsTable.setWidget(tabRowIndex, tabColIndex++, new CheckBox());
			cwIndexsTable.setText(tabRowIndex, tabColIndex++, aRow.getData().getEnemyClan().getClanTag()); 
			cwIndexsTable.setText(tabRowIndex, tabColIndex++, aRow.getData().getEnemyClan().getClanName()); 
			cwIndexsTable.setText(tabRowIndex, tabColIndex++, aRow.getData().getEnemyClan().getClanSymbol()); 
			cwIndexsTable.setText(tabRowIndex, tabColIndex++, DateTimeFmt.getString(aRow.getData().getPrepareDate(),DateTimeFmt.FmtLongGmt())  ); 
			cwIndexsTable.setWidget(tabRowIndex, tabColIndex++, bookBtn); 
			cwIndexsTable.setWidget(tabRowIndex, tabColIndex++, resultBtn); 
			
			tabRowIndex++;
		}
		
	}

	/*
	@Override
	public void setData(List<List<String>> data) {
		// List<List<String>>
		cwIndexsTable.removeAllRows();
		for (int i = 0; i < data.size(); ++i) {
			List<String> aRow=data.get(i);
			int dataCellSize=aRow.size();
			int j=0;
			cwIndexsTable.setWidget(i, j, new CheckBox());
			for(;j<dataCellSize;++j){
				cwIndexsTable.setText(i, j+1, aRow.get(j));
			}
			if(i>0){
				cwIndexsTable.setWidget(i, ++j, new Button("Book a base"));
				cwIndexsTable.setWidget(i, ++j, new Button("View Or Edit Result"));
			}
		}

		
	}
	*/
}
