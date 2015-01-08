package org.coc.tools.client.widget;

import java.util.List;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.shared.CocConstant;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CWResultPanel {

	private final ClanInfoPanelMiddle	homeClanPanel;
	private final ClanInfoPanelMiddle	enemyClanPanel;
	private final VerticalPanel	introPanel;
	////
	private final CWResultInputGroup homeInputGroup;
	private final CWResultInputGroup enemyInputGroup;
	
	//private final HorizontalPanel	holder;
	private final VerticalPanel	holder;
	private final FlexTable inputTable;
	private final HTML introMsg;
	private int scope=CocConstant.WarCounters.MIN_PLAYER_COUNT;
	
	public CWResultPanel(){
		homeClanPanel=new ClanInfoPanelMiddle();
		enemyClanPanel=new ClanInfoPanelMiddle();
		introPanel= new VerticalPanel();
		homeInputGroup=new CWResultInputGroup();
		enemyInputGroup=new CWResultInputGroup();
		holder=new VerticalPanel();
		inputTable=new FlexTable();
		introMsg=new HTML(scope +" vs "+scope);
		initLayout();
	}
	private void	initLayout(){

		holder.clear();
		homeClanPanel.resetHorizontalStyle(ClanInfoPanelMiddle.HorizontalStyle.RIGHT_SIDE_ALIGN);
		enemyClanPanel.resetHorizontalStyle(ClanInfoPanelMiddle.HorizontalStyle.LEFT_SIDE_ALIGN);
		introPanel.add(introMsg);
		GridHelper pusher=new GridHelper(inputTable);
		pusher.pushBack(GridHelper.paddingHtml());
		pusher.pushBack(homeClanPanel.getWidget()).pushBack(introPanel).pushBack(enemyClanPanel.getWidget());
		pusher.pushBack(GridHelper.paddingHtml());
		
		List<Widget> homeInputs=homeInputGroup.getInputWidgets();
		List<Widget> enemyInputs=enemyInputGroup.getInputWidgets();
		for(int i=0;i<homeInputs.size();++i){
			pusher.nextRow();
			Widget input1=homeInputs.get(i);
			Widget input2=enemyInputs.get(i);
			pusher.pushBack(GridHelper.paddingHtml());
			pusher.pushBack(input1).pushBack(new HTML(input2.getTitle())).pushBack(input2);
			pusher.pushBack(GridHelper.paddingHtml());
		}
		
		GridHelper.HorizontalAlign.alignAllMirrorCenter(inputTable);
		homeInputGroup.setTxtAlignRightForAll( );
		enemyInputGroup.setTxtAlignLeftForAll( );
		//GridHelper.setColWidth(inputTable, new String[]{"50%","120px","280px","120px","50%"});
		GridHelper.setColWidth(inputTable, new String[]{"20%","20%","280px","20%","20%"});
		
		
		inputTable.setWidth("100%");
		holder.setWidth("100%");
		holder.add(inputTable);
	}

	public Widget getWidget(){
		return holder;
	}
	public ClanInfoPanelMiddle getHomeClanPanel() {
		return homeClanPanel;
	}

	public ClanInfoPanelMiddle getEnemyClanPanel() {
		return enemyClanPanel;
	}

	public CWResultInputGroup getHomeInputGroup() {
		return homeInputGroup;
	}

	public CWResultInputGroup getEnemyInputGroup() {
		return enemyInputGroup;
	}
	public HTML getIntroMsg() {
		return introMsg;
	}
	public int getScope() {
		return scope;
	}
	public void setScope(int scope) {
		this.scope = scope;
	}
	public void	update(){
		introMsg.setHTML(scope +" vs "+scope);
		homeInputGroup.reset(scope);
		enemyInputGroup.reset(scope);
	}
	 
	
	
}
