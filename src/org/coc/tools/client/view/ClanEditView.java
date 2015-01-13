package org.coc.tools.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.coc.tools.client.presenter.ClanEditPresenter;
import org.coc.tools.client.widget.ClanEditPanel;

public class ClanEditView extends Composite implements
		ClanEditPresenter.Display {

	private static final String SAVE_BTN_STR = UiStrConstants.TitleTxt.SAVE_BTN_STR;
	private static final String CANCEL_BTN_STR = UiStrConstants.TitleTxt.CANCEL_BTN_STR;

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
		contentDetailsPanel.add(clanEditPanel.asWidget());

		HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);

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
	public String getClanSymbol() {
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
	public void setClanSymbol(String val) {
		clanEditPanel.setClanSymbol(val);
		
	}

}
