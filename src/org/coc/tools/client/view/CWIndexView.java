package org.coc.tools.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import org.coc.tools.client.misc.DateTimeFmt;
import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.presenter.CWIndexPresenter;
import org.coc.tools.client.presenter.CWIndexPresenter.CWIndexData;
import org.coc.tools.client.widget.ClanInfoPanelEx;
import org.coc.tools.client.widget.ClanInfoWidget;
import org.coc.tools.shared.model.Clan;


public class CWIndexView extends BasicView implements CWIndexPresenter.Display {
	public final static String MENU_BAR_ELEM_HEIGHT="32px";
	

	private final Button postWarEntryButton;
	private final Button deleteButton;
	private final Button listAllButton;
	private FlexTable cwIndexsTable;
	private final FlexTable contentTable;


	private final FlexTable headerTable;
	private final Button regClanButton;
	private final ClanInfoWidget homeClanPanel;

	private ListBox homeClanBox;

	//private	List<Clan>	homeClanList;
	
	public CWIndexView() {

		headerTable=new FlexTable();
		regClanButton=new Button("Create");
		//homeClanPanel=new ClanInfoPanelEx(ClanInfoPanelEx.SIZE_STYLE.SMALL);
		homeClanPanel=new ClanInfoPanelEx(ClanInfoPanelEx.SIZE_STYLE.MIDDLE);

		homeClanBox = new ListBox(false);

		postWarEntryButton = new Button("Add");
		deleteButton = new Button("Delete");
		listAllButton= new Button("See All");
		contentTable = new FlexTable();
		cwIndexsTable = new FlexTable();
		
		initMainArea();
		initHearderBar();
		//initHanlder();
	}

	private void initHearderBar(){

		GridHelper pusher=new GridHelper(headerTable);
		pusher.pushBack(homeClanPanel.asWidget()).pushBack(new HTML("")).pushBack(homeClanBox).pushBack(new HTML("")).pushBack(regClanButton);


		headerTable.setWidth("100%");
		
		GridHelper.VerticalAlign.alignAllToTop(headerTable);
		GridHelper.setColWidth(headerTable, new String[]{"250px","60%","150px","2px","32px"});
		homeClanBox.setHeight(MENU_BAR_ELEM_HEIGHT);
		regClanButton.setHeight(MENU_BAR_ELEM_HEIGHT);
		regClanButton.setTitle("All informations are listed and managed by [Home Clan],if you need a new one,this button works for you!");
		
		homeClanPanel.setBorderWidth(0);
		this.setHeaderSmall(headerTable);
		//this.setHeader(headerTable);//
		
	}

	private void initMainArea(){

		HorizontalPanel containerPanel = new HorizontalPanel();

		HorizontalPanel menuPanel = new HorizontalPanel();
		//contentTableDecorator.setWidth("100%");
		containerPanel.setWidth("100%");

		contentTable.setWidth("100%");
		//
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);

		
		menuPanel.setBorderWidth(0);
		menuPanel.setSpacing(0);
		//menuPanel.setWidth("100%");
		menuPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		
	
		menuPanel.add(postWarEntryButton);
		menuPanel.add(deleteButton);
		menuPanel.add(listAllButton);
		
		contentTable.setWidget(0, 0, menuPanel);

		// Create the contacts list
		//
		cwIndexsTable.setCellSpacing(0);
		cwIndexsTable.setCellPadding(0);
		cwIndexsTable.setWidth("100%");
		cwIndexsTable.addStyleName("contacts-ListContents");
		cwIndexsTable.getColumnFormatter().setWidth(0, "15px");
		//cwIndexsTable.setBorderWidth(1);
		contentTable.setWidget(1, 0, cwIndexsTable);

		containerPanel.add(contentTable);
		containerPanel.setBorderWidth(1);
		
		//contentTableDecorator.add(contentTable);

		//this.setCenter(contentTableDecorator); 
		this.setCenter(containerPanel); 
	}

	@Override
	public HasClickHandlers getAddButton() {
		return this.postWarEntryButton;
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
	public int getClickedCwEntryRow(ClickEvent event) {
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
	public List<Integer> getSelectedCwIndexRows() {
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
	public void setCwEntryList(List<CWIndexData> data) {
		cwIndexsTable.removeAllRows();

		String paddingWidth="32px";
		GridHelper pusher=new GridHelper(cwIndexsTable);
	
		for (int i = 0; i < data.size(); ++i) {
			if(i==0){
				// title
				pusher.pushBack( new HTML(UiStrConstants.ValueNames.WAR_INDEX_ID))
				.pushBack(GridHelper.paddingHtml(paddingWidth))
				.pushBack(new HTML(UiStrConstants.ValueNames.ENEMY_CLAN))
				.pushBack(GridHelper.paddingHtml(paddingWidth))
				.pushBack(new HTML(UiStrConstants.ValueNames.WAR_PREPARE_DATE))
				.pushBack(GridHelper.paddingHtml(paddingWidth))
				.pushBack(GridHelper.paddingHtml(paddingWidth))
				.pushBack(GridHelper.paddingHtml(paddingWidth));
				pusher.nextRow();
			}

			CWIndexData aRow=data.get(i);
			Button bookBtn=new Button("Book a base");
			Button resultBtn=new Button("View Or Edit Result");
			resultBtn.addClickHandler(aRow.getClickHandlerForEdtCwRet());
			bookBtn.setEnabled(aRow.isCanBookBase());
			
			//ClanInfoWidget clanPanel=new ClanInfoPanel();
			ClanInfoWidget clanPanel=new ClanInfoPanelEx(ClanInfoPanelEx.SIZE_STYLE.SMALL);
			clanPanel.showTag(false);
			clanPanel.update(aRow.getData().getEnemyClan().getClanTag(), aRow.getData().getEnemyClan().getClanName(), aRow.getData().getEnemyClan().getClanSymbol());

			// data
			String warId=Long.toString(aRow.getData().getRowId());
			//String warId=Long.toHexString(aRow.getData().getRowId());
			pusher.pushBack( new HTML(warId ))
			.pushBack(GridHelper.paddingHtml(paddingWidth))
			.pushBack(clanPanel.asWidget())
			.pushBack(GridHelper.paddingHtml(paddingWidth))
			.pushBack(new HTML(DateTimeFmt.getString(aRow.getData().getPrepareDate(),DateTimeFmt.FmtLongGmt())))
			.pushBack(GridHelper.paddingHtml(paddingWidth))
			.pushBack(bookBtn)
			.pushBack(resultBtn);
			pusher.nextRow();
			
			
		}
		
	}

	@Override
	public HasClickHandlers getRegClanButton() {
		return regClanButton;
	}

	@Override
	public void setRegedClanList(List<Clan> data) {
		updateClanBox(data);
	}
	
	private void	updateClanBox(List<Clan> list){
		homeClanBox.clear();
		//homeClanBox.setVisibleItemCount(1);
		for(Clan one:list){
			homeClanBox.addItem(one.getClanName()+ " - "+ one.getClanTag(), one.getClanTag());
		}
	}
	@Override
	public int getSelectedRegClan() {
		return homeClanBox.getSelectedIndex();
	}
	@Override
	public HasChangeHandlers getRegedClanBox() {
		return homeClanBox;
	}
	@Override
	public void setRegedClan(Clan clan) {

		homeClanPanel.update(clan);
	}

	@Override
	public HasClickHandlers getListAllButton() {
		return listAllButton;
	}


}
