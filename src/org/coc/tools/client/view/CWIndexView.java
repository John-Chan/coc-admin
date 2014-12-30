package org.coc.tools.client.view;

import java.util.ArrayList;
import java.util.List;





import com.google.gwt.core.client.GWT;
//import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import org.coc.tools.client.presenter.CWIndexPresenter;
import org.coc.tools.client.presenter.CWIndexPresenter.CWIndexData;
import org.coc.tools.shared.DateTimeFmt;

public class CWIndexView extends Composite implements CWIndexPresenter.Display {

	private final String	appVersion="V0.1";
	private final Button addButton;
	private final Button deleteButton;
	private FlexTable cwIndexsTable;
	private final FlexTable contentTable;

	private void doLayout1(){

		
	}
	private void doLayout2(){

		/*
		DockPanel rootPanel = new DockPanel();
		rootPanel.setSize("80%", "100%");
		rootPanel.setSpacing(2);
		rootPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		
		///////
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		//contentTableDecorator.setWidth("64em");
		//contentTableDecorator.setWidth("80%");
		//contentTableDecorator.getParent().

		//align="center"
		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		// TODO : contentTable.getCellFormatter().addStyleName(0, 0,
		// "contacts-ListContainer");
		//
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);

		// Create the menu
		//
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Add");
		hPanel.add(addButton);
		deleteButton = new Button("Delete");
		hPanel.add(deleteButton);
		
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		// Create the contacts list
		//
		cwIndexsTable = new FlexTable();
		cwIndexsTable.setCellSpacing(0);
		cwIndexsTable.setCellPadding(0);
		cwIndexsTable.setWidth("100%");
		cwIndexsTable.addStyleName("contacts-ListContents");
		cwIndexsTable.getColumnFormatter().setWidth(0, "15px");
		contentTable.setWidget(1, 0, cwIndexsTable);

		contentTableDecorator.add(contentTable);

		ScrollPanel scroller = new ScrollPanel(contentTableDecorator);
	    scroller.setSize("100%", "100%");
	    
		//Hyperlink HomeLink=new Hyperlink ();
		// Add text all around
		rootPanel.add(new HTML(GWT.getModuleName()+"  "+ appVersion), DockPanel.NORTH);
		//rootPanel.add(new HTML(constants.cwDockPanelSouth1()), DockPanel.SOUTH);
		//rootPanel.add(new HTML(constants.cwDockPanelEast()), DockPanel.EAST);
		//rootPanel.add(new HTML(constants.cwDockPanelWest()), DockPanel.WEST);
		rootPanel.add(scroller, DockPanel.CENTER);
		//rootPanel.add(new HTML(constants.cwDockPanelNorth2()), DockPanel.NORTH);
		rootPanel.add(new HTML(GWT.getHostPageBaseURL()), DockPanel.SOUTH);
		*/
	}
	public CWIndexView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("80%");
		//contentTableDecorator.setWidth("64em");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		// TODO : contentTable.getCellFormatter().addStyleName(0, 0,
		// "contacts-ListContainer");
		//
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);

		// Create the menu
		//
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Add");
		hPanel.add(addButton);
		deleteButton = new Button("Delete");
		hPanel.add(deleteButton);
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		// Create the contacts list
		//
		cwIndexsTable = new FlexTable();
		cwIndexsTable.setCellSpacing(0);
		cwIndexsTable.setCellPadding(0);
		cwIndexsTable.setWidth("100%");
		cwIndexsTable.addStyleName("contacts-ListContents");
		cwIndexsTable.getColumnFormatter().setWidth(0, "15px");
		contentTable.setWidget(1, 0, cwIndexsTable);

		contentTableDecorator.add(contentTable);
		
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
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, "Enemy clan tag"); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, "Enemy clan name"); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, "Enemy clan symobl"); 
		cwIndexsTable.setText(tabRowIndex, tabColIndex++, "Prepare Day"); 
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
