package org.coc.tools.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.presenter.CWResultEditPresenter;
import org.coc.tools.client.widget.CWResultPanel;
import org.coc.tools.shared.model.ClanWarEntryPojo;

public class CWResultEditView  extends BasicView implements CWResultEditPresenter.Display{


	private final Button saveButton;
	private final Button saveAndLockButton;
	private final Button cancelButton;
	private final CWResultPanel	cwResultPanel;
	
	private ClanWarEntryPojo	currentData=null;
	
	public CWResultEditView(){
		saveButton=new Button(UiStrConstants.TitleTxt.SAVE_BTN_STR);
		cancelButton=new Button(UiStrConstants.TitleTxt.CANCEL_BTN_STR);
		saveAndLockButton=new Button(UiStrConstants.TitleTxt.SAVE_LOCK_BTN_STR);
		saveAndLockButton.setTitle("save and disable edit");
		cwResultPanel=new CWResultPanel();
		//currentData=new ClanWarEntryPojo();
		initLayout();
	}
	private void	initLayout(){
		HorizontalPanel containerPanel = new HorizontalPanel();
		containerPanel.setWidth("100%");

		HorizontalPanel menuPanel = new HorizontalPanel();
		FlexTable contentTable=new FlexTable();
		contentTable.setWidth("100%");

		contentTable.clear();
		GridHelper pusher=new GridHelper(contentTable);
		pusher.pushBack(cwResultPanel.asWidget());
		pusher.nextRow().pushBack(menuPanel);
		GridHelper.HorizontalAlign.alignAllMirrorCenter(contentTable, 1);

		menuPanel.setBorderWidth(0);
		menuPanel.setSpacing(0);

		menuPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		
	
		menuPanel.add(saveButton);
		menuPanel.add(saveAndLockButton);
		menuPanel.add(cancelButton);
		saveButton.setWidth("96px");
		saveAndLockButton.setWidth("96px");
		cancelButton.setWidth("96px");
		//contentTable.add();
		containerPanel.add(contentTable);
		this.setCenter(containerPanel); 
	}
	private void	updateData(ClanWarEntryPojo data){
		cwResultPanel.getHomeInputGroup().fillWarResultVal(data.getHomeClanWarResult());
		cwResultPanel.getEnemyInputGroup().fillWarResultVal(data.getEnemyClanWarResult());
	}
	private void	updateUi(final ClanWarEntryPojo data){
		cwResultPanel.setScope(data.getWarIndex().getScope());
		if(null != data.getWarIndex().getHomeClan())
			cwResultPanel.getHomeClanPanel().update(data.getWarIndex().getHomeClan());
		if(null != data.getWarIndex().getEnemyClan())
			cwResultPanel.getEnemyClanPanel().update(data.getWarIndex().getEnemyClan());
		if(null != data.getHomeClanWarResult())
			cwResultPanel.getHomeInputGroup().updateDisplayVal(data.getHomeClanWarResult());
		if(null != data.getEnemyClanWarResult())
			cwResultPanel.getEnemyInputGroup().updateDisplayVal(data.getEnemyClanWarResult());
		
		cwResultPanel.update();
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
	public ClanWarEntryPojo getCwData() {
		updateData(currentData);
		return currentData;
	}

	@Override
	public void setCwData(ClanWarEntryPojo val) {
		currentData= val;
		updateUi(currentData);
	}

	@Override
	public void enableUpdate(boolean val) {
		saveButton.setEnabled(val);
		
	}
	@Override
	public HasClickHandlers getSaveAndLockButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
