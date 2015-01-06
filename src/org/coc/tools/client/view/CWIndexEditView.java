package org.coc.tools.client.view;

import java.util.Date;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.misc.ResHelper;
import org.coc.tools.client.presenter.CWIndexEditPresenter;
import org.coc.tools.client.widget.ClanEditPanel;
import org.coc.tools.client.widget.ClanInfoPanel;
import org.coc.tools.client.widget.DateTimePicker;
import org.coc.tools.shared.CocConstant;
import org.coc.tools.shared.FieldVerifier;
import org.gwt.advanced.client.datamodel.ComboBoxDataModel;
import org.gwt.advanced.client.ui.widget.ComboBox;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
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
	//DateTimePicker
	//private final TextBox enemyClanTag;
	//private final TextBox enemyClanName;
	//private final TextBox enemyClanSymbol;
	private final ClanEditPanel	homeClanInfoPanel;
	private final ClanEditPanel	enemyClanInfoPanel;
	//private final TextBox scope;
	private final DateTimePicker prepareDate;
	private final ComboBox<ComboBoxDataModel> scopeBox;
	private final ComboBoxDataModel scopeDataModel;

	//private final Label homeClanTag;
	//private final Label homeClanName;
	//private final HTML homeClanSymbolImg;
	
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;
	private final DecoratorPanel contentDetailsDecorator;
	

	
	public CWIndexEditView() {
		saveButton = new Button(SAVE_BTN_STR);
		cancelButton = new Button(CANCEL_BTN_STR);
		homeClanInfoPanel= new ClanEditPanel();
		homeClanInfoPanel.setEnableEdit(false, false, false);
		enemyClanInfoPanel=  new ClanEditPanel();
		prepareDate = new DateTimePicker(new Date());
		scopeBox=new ComboBox<ComboBoxDataModel>();
		scopeDataModel=new ComboBoxDataModel();

		detailsTable = new FlexTable();
		contentDetailsDecorator = new DecoratorPanel();
		initWidget(contentDetailsDecorator);

		
		initInputComponents();
		initHanlder();
		initLayout();
	}
	
	private void	initInputComponents(){
		for(int i= CocConstant.WarCounters.MIN_PLAYER_COUNT;i<=CocConstant.WarCounters.MAX_PLAYER_COUNT;i+=CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES){
			scopeDataModel.add("scope-"+i, Integer.toString(i));
		}

		scopeBox.setModel(scopeDataModel);
		scopeBox.setCustomTextAllowed(false);
		scopeBox.setLazyRenderingEnabled(false);
	}
	private void initHanlder(){
		/*
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
		*/
	}


	/*
	private boolean checkEnemyClanSymbol(){
		String val=enemyClanSymbol.getValue();
    	return FieldVerifier.isValidClanSymbol(val).getPassed();
	}
	private boolean checkPlayerCount(){

		String val=scope.getValue();
    	return FieldVerifier.isValidCwPlayerCount(val).getPassed();
	}
	*/

	private void initLayout() {

		contentDetailsDecorator.setWidth("80%");
		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");

		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);
		
		GridHelper pusher=new GridHelper(detailsTable);
		pusher.pushBack(new Label("Home Team")).pushBack(new HTML("")).pushBack(homeClanInfoPanel.getWidget());

		pusher.nextRow();
		pusher.pushBack(new Label("Enemy Team")).pushBack(new HTML("")).pushBack(enemyClanInfoPanel.getWidget());
		
		pusher.nextRow();
		pusher.pushBack(new Label("Scope")).pushBack(new HTML("")).pushBack(scopeBox);
		
		pusher.nextRow();
		pusher.pushBack(new Label("Prepare Date")).pushBack(new HTML("")).pushBack(prepareDate);


		GridHelper.VerticalAlign.alignAllToTop(detailsTable);
		GridHelper.setColWidth(detailsTable, new String[]{"80px","20px"});

		scopeBox.setWidth("95%");
		prepareDate.setWidth("95%");
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	/*
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

	 */
	@Override
	public void setHomeClan(String tag, String name, String symbol) {
		homeClanInfoPanel.updateVal(tag, name, symbol );
	}

	@Override
	public Date getPrepareDate() {
		return prepareDate.getDate();
	}

	@Override
	public String getEnemyClanTag() {
		return enemyClanInfoPanel.getClanTag();
	}

	@Override
	public String getEnemyClanName() {
		return enemyClanInfoPanel.getClanName();
	}

	@Override
	public String getEnemyClanSymbol() {
		return enemyClanInfoPanel.getClanSymbol();
	}

	@Override
	public String getScope() {
		int scope=scopeDataModel.getSelectedIndex()*CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES+CocConstant.WarCounters.MIN_PLAYER_COUNT;
		return Integer.toString(scope);
	}

	@Override
	public void setPrepareDate(Date val) {
		prepareDate.setDate(val);
		
	}

	@Override
	public void setEnemyClanTag(String val) {
		enemyClanInfoPanel.setClanTag(val);
		
	}

	@Override
	public void setEnemyClanName(String val) {
		enemyClanInfoPanel.setClanName(val);
		
	}

	@Override
	public void setEnemyClanSymbol(String val) {
		enemyClanInfoPanel.setClanSymbol(val);
		
	}

	@Override
	public void setScope(String val) {

		int scope=Integer.parseInt(val);
		if(scope>= CocConstant.WarCounters.MIN_PLAYER_COUNT&& scope <= CocConstant.WarCounters.MAX_PLAYER_COUNT){
			scope=(scope/CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES)-(CocConstant.WarCounters.MIN_PLAYER_COUNT/scope/CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES);
			scopeDataModel.setSelectedIndex(scope);
		}
		
	}


}
