package org.coc.tools.client.view;

import java.util.Date;
import java.util.List;

import org.coc.tools.client.misc.DateTimeFmt;
import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.widget.CWResultInputGroup;
import org.coc.tools.client.widget.CWResultLessPanel;
import org.coc.tools.client.widget.CWResultPanel;
import org.coc.tools.client.widget.ClanEditPanel;
import org.coc.tools.client.widget.ClanInfoPanelEx;
import org.coc.tools.client.widget.DateTimePicker;
import org.coc.tools.shared.TestHelper;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.gwt.advanced.client.ui.widget.Calendar;
import org.gwt.advanced.client.ui.widget.DatePicker;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import org.coc.tools.client.presenter.UiTestPresenter;

public class UiTestView extends BasicView implements 	UiTestPresenter.Display{

	private final Button testRpcButton;
	private final TextBox txt1;
	private final TextBox txt2;
	private final HorizontalPanel	inputPanel;
	public UiTestView(){
		testRpcButton=new Button("testRpc");
		txt1=new TextBox();
		txt2=new TextBox();
		txt1.setWidth("220px");
		txt2.setWidth("220px");
		inputPanel=new HorizontalPanel();
		inputPanel.add(new Label("txt1 : "));
		inputPanel.add(txt1);
		inputPanel.add(new Label("txt2 : "));
		inputPanel.add(txt2);
		super.setHeader(testRpcButton);
		super.setHeaderSmall(inputPanel);
		//showCalendar();
		//showDatePicker();
		//showDateTimePicker();
		//showClanEditPanel();
		//showClanInfoPanelMiddle();
		//showCWResultInputGroup();
		//showCWResultPanel();
		
		//showCWResultLessPanel();
	}
	public void showDateTimePicker(){

		DateTimePicker picker = new DateTimePicker(new Date());
		picker.setFormat(DateTimeFmt.DATE_TIME_LONG_GMT);
		picker.setWidth("100%");
		picker.setTimeVisible(true);
		super.setFooterSmall(picker);
	}
	
	public void showDatePicker(){

		DatePicker picker = new DatePicker(new Date());
		picker.setFormat(DateTimeFmt.DATE_TIME_LONG_GMT);
		picker.setWidth("100%");
		picker.setTimeVisible(true);
		super.setFooterSmall(picker);
	}
	public void showCalendar(){
		Calendar ca= new Calendar();
		ca.setWidth("100%");
		ca.setShowTime(true);
		super.setFooterSmall(ca.asWidget());
	}
	public void showClanEditPanel(){
		ClanEditPanel edtPanel= new ClanEditPanel();
		edtPanel.setReadOnly(true, true, true);
		super.setCenter(edtPanel.asWidget());
	}
	public void showClanInfoPanelMiddle(){
		ClanInfoPanelEx panel= new ClanInfoPanelEx("#UI0PUQE","nakama ck","40");
		panel.setBorderWidth(1);
		//edtPanel.setReadOnly(true, true, true);
		super.setCenter(panel.asWidget());
	}
	//
	public void	showCWResultInputGroup(){
		int scope=45;
		CWResultInputGroup group=new CWResultInputGroup();
		group.resetScope(scope,true);
		List<Widget> inputs=group.getInputWidgets();
		FlexTable container=new FlexTable();
		GridHelper pusher=new GridHelper(container);
		pusher.pushBack(new HTML(" ")).pushBack(new HTML(Integer.toString(scope) )).pushBack(new HTML(" "));
		
		for(Widget w:inputs){
			pusher.nextRow();
			pusher.pushBack(new Label(w.getTitle())).pushBack(new HTML(":")).pushBack(w);
		}
		super.setCenter(container);
	}
	
	public void showCWResultPanel(){
		CWResultPanel panel=new CWResultPanel();
		panel.setScope(45);
		panel.getHomeClanPanel().update("#YUIOP", "NAKAMA CK", "40");
		panel.getEnemyClanPanel().update("#R5YEQ3", "NAKAMA UNION", "32");
		panel.update();
		//panel.getWidget().setWidth("90%");
		super.setCenter(panel.asWidget());
		super.setHeaderSmall(txt1);
	}
	
	public void showCWResultLessPanel(){
		CWResultLessPanel panel=new CWResultLessPanel();
		panel.setBorderWidth(1);
		//panel.setGridBorderWidth(1);
		List<ClanWarEntryPojo> datas=	TestHelper.makeClanWarEntryPojoList(20,"#R5YEQ3", "NAKAMA UNION", "32");
		panel.addData(datas);
		super.setCenter(panel.asWidget());
	}

	@Override
	public HasClickHandlers getTestRpcButton() {
		return testRpcButton;
	}
	@Override
	public String getText1() {
		return txt1.getValue();
	}
	@Override
	public String getText2() {
		return txt2.getValue();
	}
}
