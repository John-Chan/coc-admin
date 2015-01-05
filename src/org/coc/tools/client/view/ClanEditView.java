package org.coc.tools.client.view;

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

import org.coc.tools.client.misc.ModelFactory;
import org.coc.tools.client.misc.ResHelper;
import org.coc.tools.client.presenter.ClanEditPresenter;
import org.coc.tools.client.widget.ClanEditPanel;
import org.gwt.advanced.client.datamodel.ComboBoxDataModel;
import org.gwt.advanced.client.datamodel.IconItem;
import org.gwt.advanced.client.datamodel.ListDataModel;
import org.gwt.advanced.client.ui.widget.ComboBox;

public class ClanEditView extends Composite implements
		ClanEditPresenter.Display {

	private static final String SAVE_BTN_STR = "Save";
	private static final String CANCEL_BTN_STR = "Cancel";

	private final Button saveButton;
	private final Button cancelButton;

	private final ClanEditPanel clanEditPanel;

	public ClanEditView() {

		clanEditPanel=new ClanEditPanel();
		saveButton = new Button(SAVE_BTN_STR);
		cancelButton = new Button(CANCEL_BTN_STR);
		
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("80%");
		initWidget(contentDetailsDecorator);
		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.add(clanEditPanel.getWidget());

		HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);

	}


	private void initHanlder() {

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
	public String getClanTag() {
		return clanEditPanel.getClanTag();
	}
	@Override
	public String getClanName() {
		return clanEditPanel.getClanName();
	}
	@Override
	public int getClanSymbol() {
		return clanEditPanel.getClanSymbol();
	}
	@Override
	public void setClanTag(String val) {
		clanEditPanel.setClanTag(val);
		
	}
	@Override
	public void setClanName(String val) {
		clanEditPanel.setClanName(val);
		
	}
	@Override
	public void setClanSymbol(int val) {
		clanEditPanel.setClanSymbol(val);
		
	}

}
