package org.coc.tools.client.view;

import java.util.Date;

import org.coc.tools.client.widget.DateTimePicker;
import org.coc.tools.shared.DateTimeFmt;
import org.gwt.advanced.client.ui.widget.Calendar;
import org.gwt.advanced.client.ui.widget.DatePicker;

public class UiTestView extends BasicView {

	public UiTestView(){
		//showCalendar();
		//showDatePicker();
		showDateTimePicker();
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
}
