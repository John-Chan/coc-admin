package org.coc.tools.client.widget;

import java.util.Date;

import org.gwt.advanced.client.ui.resources.CalendarConstants;
import org.gwt.advanced.client.ui.widget.DatePicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;

public class DateTimePicker extends DatePicker {


	public DateTimePicker(){
		super.setTimeVisible(true);
		//addEvtHandler();
	}
	public DateTimePicker(Date initialDate){
		super.setDate(initialDate);
		super.setTimeVisible(true);
		//addEvtHandler();
	}
	
	@SuppressWarnings("deprecation")
	private void doUpdate(){
		/*
		Date dt=this.getDate();
		dt.setHours(this.getCalendar().getHours().getSelectedIndex());
		dt.setMinutes(this.getCalendar().getMinutes().getSelectedIndex());
		dt.setSeconds(this.getCalendar().getSeconds().getSelectedIndex());
		*/
		int hours = 0;
        int minutes = 0;
        int seconds = 0;

        CalendarConstants constants =
                (CalendarConstants) GWT.create(CalendarConstants.class);
        String hoursCircleBasis=constants.hoursCircleBasis();//this.getCalendar().constants.hoursCircleBasis()
        if (/*isShowTime()*/ this.getCalendar().isShowTime()) {
            hours = Integer.valueOf(this.getCalendar().getHours().getValue(this.getCalendar().getHours().getSelectedIndex()));
            if (
                "12".equals(hoursCircleBasis)
                && "PM".equals(this.getCalendar().getAmPmMarker().getValue(this.getCalendar().getAmPmMarker().getSelectedIndex()))
                && hours != 12
            ) {
                hours+=12;
            }

            if (
                "12".equals(hoursCircleBasis)
                && "AM".equals(this.getCalendar().getAmPmMarker().getValue(this.getCalendar().getAmPmMarker().getSelectedIndex()))
                && hours == 12
            ) {
                hours=0;
            }

            minutes = Integer.valueOf(this.getCalendar().getMinutes().getValue(this.getCalendar().getMinutes().getSelectedIndex()));
            seconds = Integer.valueOf(this.getCalendar().getSeconds().getValue(this.getCalendar().getSeconds().getSelectedIndex()));
            
    		Date dt=this.getDate();
    		dt.setHours(hours);
    		dt.setMinutes(minutes);
    		dt.setSeconds(seconds);
    		
        }
	}

	@SuppressWarnings("unused")
	private void	addEvtHandler(){
		super.getCalendar().getMinutes().addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				
				DateTimePicker.this.doUpdate();
				Window.alert("Minutes onChange");
			}
			//
		});
	}
	//
}
