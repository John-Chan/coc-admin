package org.coc.tools.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class BasicView extends Composite{
	public static final String DEFAULT_HEIGHT="100%";
	public static final String DEFAULT_WIDTH="100%";
	
	private final DockPanel rootPanel;

	private String width=DEFAULT_WIDTH;
	private String height=DEFAULT_HEIGHT;
	
	private Widget	header=new HTML("header");
	private Widget	headerSmall=new HTML("headerSmall");
	private Widget	footer=new HTML("footer");
	private Widget	footerSmall=new HTML("footerSmall");
	private Widget	left=new HTML("left");
	private Widget	right=new HTML("right");
	private Widget	center=new HTML("center");
	
	public BasicView(){
		rootPanel = new DockPanel();
		initLauout();
	}
	public BasicView(String width, String height){
		this.width=width;
		this.height=height;
		rootPanel = new DockPanel();
		initLauout();
	}
	
	private void	resetPanel(){
		rootPanel.clear();
		//rootPanel.setVerticalAlignment(DockPanel.ALIGN_CENTER);
		rootPanel.add(header, DockPanel.NORTH);
		rootPanel.add(footer, DockPanel.SOUTH);
		//rootPanel.add(footer, DockPanel.LINE_END);
		rootPanel.add(right, DockPanel.EAST);
		rootPanel.add(left, DockPanel.WEST);
		rootPanel.add(center, DockPanel.CENTER);
		rootPanel.add(headerSmall, DockPanel.NORTH);
		rootPanel.add(footerSmall, DockPanel.SOUTH);

		String debug="header index="+rootPanel.getWidgetIndex(header)+";";
		debug+="footer index="+rootPanel.getWidgetIndex(footer)+";";
		debug+="right index="+rootPanel.getWidgetIndex(right)+";";
		debug+="left index="+rootPanel.getWidgetIndex(left)+";";
		debug+="center index="+rootPanel.getWidgetIndex(center)+";";
		debug+="headerSmall index="+rootPanel.getWidgetIndex(headerSmall)+";";
		debug+="footerSmall index="+rootPanel.getWidgetIndex(footerSmall)+";";
		footerSmall.setTitle(debug);
	}
	
	private void initLauout(){

		initWidget(rootPanel);
		rootPanel.setSize(width,height);
		rootPanel.setSpacing(2);
		rootPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		//rootPanel.setHorizontalAlignment(DockPanel.ALIGN_LEFT);
		resetPanel();
		
		//
	}
	/*
	public void setHeader(IsWidget child){
		
	}
	*/

	
	public Widget getHeaderSmall() {
		return headerSmall;
	}
	public Widget getFooterSmall() {
		return footerSmall;
	}
	public Widget getLeft() {
		return left;
	}
	public Widget getRight() {
		return right;
	}
	public Widget getHeader() {
		return header;
	}
	public Widget getFooter() {
		return footer;
	}
	public Widget getCenter() {
		return center;
	}

	public void setHeader(Widget header) {
		this.header = header;
		resetPanel();
	}
	public void setHeaderSmall(Widget headerSmall) {
		this.headerSmall = headerSmall;
		resetPanel();
	}
	public void setFooterSmall(Widget footerSmall) {
		this.footerSmall = footerSmall;
		resetPanel();
	}
	public void setLeft(Widget left) {
		this.left = left;
		resetPanel();
	}
	public void setRight(Widget right) {
		this.right = right;
		resetPanel();
	}
	public void setCenter(Widget center) {
		//
		//rootPanel.remove(this.center);
		//this.center = center;
		//rootPanel.add(center, DockPanel.CENTER);
		
		this.center = center;
		resetPanel();
	}
	
	
	
}
