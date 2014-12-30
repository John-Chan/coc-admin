package org.coc.tools.client.view;

import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import org.coc.tools.client.presenter.ClanEditPresenter;

public class ClanEditView extends Composite implements
		ClanEditPresenter.Display {

	private static final String SAVE_BTN_STR = "Save";
	private static final String CANCEL_BTN_STR = "Cancel";

	private final TextBox clanTag;
	private final TextBox clanName;
	private final TextBox clanSymbol;

	private final Button saveButton;
	private final Button cancelButton;

	private final FlexTable detailsTable;

	public ClanEditView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("80%");
		initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		// Create the contacts list
		//
		detailsTable = new FlexTable();
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		// TODO :detailsTable.addStyleName("contacts-ListContainer");
		// TODO :detailsTable.getColumnFormatter().addStyleName(1,
		// "add-contact-input");

		clanTag = new TextBox();
		clanName = new TextBox();
		clanSymbol = new TextBox();

		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button(SAVE_BTN_STR);
		cancelButton = new Button(CANCEL_BTN_STR);
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);

		setValueRule();
		initHanlder();
	}

	private void initDetailsTable() {

		detailsTable.setWidget(0, 0, new Label("clanTag"));
		detailsTable.setWidget(0, 1, clanTag);
		detailsTable.setWidget(1, 0, new Label("clanName"));
		detailsTable.setWidget(1, 1, clanName);
		detailsTable.setWidget(2, 0, new Label("clanSymbol"));
		detailsTable.setWidget(2, 1, clanSymbol);
		// prepareDate.setFocus(true);
	}

	private void setupLayout() {

	}

	private void setValueRule() {

	}

	private void initHanlder() {

	}

	@Override
	public HasClickHandlers getSaveButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasValue<String> getClanTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasValue<String> getClanName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasValue<String> getClanSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

}
