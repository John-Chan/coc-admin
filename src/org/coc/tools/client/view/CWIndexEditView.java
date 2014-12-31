package org.coc.tools.client.view;

import java.util.Date;

import org.coc.tools.client.presenter.CWIndexEditPresenter;
import org.coc.tools.shared.FieldVerifier;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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

public class CWIndexEditView extends Composite implements
		CWIndexEditPresenter.Display {

	private static final String SAVE_BTN_STR="Save";
	private static final String CANCEL_BTN_STR="Cancel";
	
	// private final ListBox scope;
	//private final TextBox homeClanId;
	private final DatePicker prepareDate;
	private final TextBox enemyClanTag;
	private final TextBox enemyClanName;
	private final TextBox enemyClanSymbol;
	private final TextBox scope;
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	public CWIndexEditView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("18em");
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

		prepareDate = new DatePicker();
		prepareDate.setValue(new Date(), false);
		

		enemyClanTag = new TextBox();
		enemyClanName = new TextBox();
		enemyClanSymbol = new TextBox();
		scope = new TextBox();
		// scope= new ListBox();
		initScopeList();
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button(SAVE_BTN_STR);
		cancelButton = new Button(CANCEL_BTN_STR);
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);
		
		initInputComponents();
		initHanlder();
	}
	
	private void	initInputComponents(){
		scope.setMaxLength(2);
		
		enemyClanSymbol.setMaxLength(2);
		enemyClanName.setMaxLength(32);
		enemyClanTag.setMaxLength(12);
	}

	private boolean checkEnemyClanSymbol(){
		String val=enemyClanSymbol.getValue();
    	return FieldVerifier.isValidClanSymbol(val).getPassed();
	}
	private boolean checkPlayerCount(){

		String val=scope.getValue();
    	return FieldVerifier.isValidCwPlayerCount(val).getPassed();
	}
	private void initHanlder(){
		enemyClanSymbol.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	
		    	boolean good=checkEnemyClanSymbol();
		    	if(good){
		    		saveButton.setText(SAVE_BTN_STR);
		    	}else{
		    		saveButton.setText("please check ClanSymbol");
		    	}
	    		saveButton.setEnabled(good);

		    }
		});
		
		scope.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	boolean good= checkPlayerCount();
		    	if(good){
		    		saveButton.setText(SAVE_BTN_STR);
		    	}else{
		    		saveButton.setText("please check player count");
		    	}
	    		saveButton.setEnabled(good);

		    }
		});
	}
	private void initScopeList() {

		/*
		 * for(int i=10;i<=50;i+=5){ scope.addItem(Integer.toString(i),
		 * Integer.toString(i)); }
		 */
	}

	private void initDetailsTable() {
		
		detailsTable.setWidget(0, 0, new Label(ViewConstants.ValueNames.WAR_PLAYER_COUNT));
		detailsTable.setWidget(0, 1, scope);
		detailsTable.setWidget(1, 0, new Label(ViewConstants.ValueNames.ENEMY_CLAN_TAG));
		detailsTable.setWidget(1, 1, enemyClanTag);
		detailsTable.setWidget(2, 0, new Label(ViewConstants.ValueNames.ENEMY_CLAN_NAME));
		detailsTable.setWidget(2, 1, enemyClanName);
		detailsTable.setWidget(3, 0, new Label(ViewConstants.ValueNames.ENEMY_CLAN_SYMBOL));
		detailsTable.setWidget(3, 1, enemyClanSymbol);
		detailsTable.setWidget(4, 0, new Label(ViewConstants.ValueNames.WAR_PREPARE_DATE));
		detailsTable.setWidget(4, 1, prepareDate);
		// prepareDate.setFocus(true);
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasValue<Date> getPrepareDate() {
		return prepareDate;
	}

	@Override
	public HasValue<String> getScope() {
		return scope;
	}

	@Override
	public HasValue<String> getEnemyClanTag() {
		return enemyClanTag;
	}

	@Override
	public HasValue<String> getEnemyClanName() {
		return enemyClanName;
	}

	@Override
	public HasValue<String> getEnemyClanSymbol() {
		return enemyClanSymbol;
	}

}
