package org.coc.tools.client.widget;

import java.util.Date;

import org.coc.tools.client.misc.DateTimeFmt;
import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.misc.ResHelper;
import org.coc.tools.shared.model.Clan;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CWResultUnitLess {

	public static final String STATUS_IMG_SIZE="24PX";
	
	private	final HTML statusImg;
	private final ClanInfoWidget	homeClanPanel;
	private final ClanInfoWidget	enemyClanPanel;
	private final Panel				scorePanel;
	private final HTML				scopeTxt;
	private final HTML				scoreTxt;
	private final HTML				endDateTxt;
	public CWResultUnitLess(){
		Date now=new Date();
		homeClanPanel=new ClanInfoPanelEx(ClanInfoPanelEx.SIZE_STYLE.SMALL);
		enemyClanPanel=new ClanInfoPanelEx(ClanInfoPanelEx.SIZE_STYLE.SMALL);
		scorePanel=new VerticalPanel();
		scopeTxt=new HTML("15 vs 15");
		scoreTxt=new HTML("0:0");
		endDateTxt=new HTML(DateTimeFmt.getString(now, DateTimeFmt.FmtDate()));
		statusImg=new HTML();
		
		initLayout();
	}
	
	
	public HTML getStatusImg() {
		return statusImg;
	}
	public ClanInfoWidget getHomeClanPanel() {
		return homeClanPanel;
	}
	public ClanInfoWidget getEnemyClanPanel() {
		return enemyClanPanel;
	}
	public Panel getScorePanel() {
		return scorePanel;
	}
	public HTML getScopeTxt() {
		return scopeTxt;
	}
	public HTML getScoreTxt() {
		return scoreTxt;
	}
	public HTML getEndDateTxt() {
		return endDateTxt;
	}
	
	public void updateVal(int scope,Date endDate,final Clan homeClan, int homeClanScore,final Clan enemyClan,int enemyClanScore){

		homeClanPanel.update(homeClan); 
		enemyClanPanel.update(enemyClan);
		scopeTxt.setHTML(scope+" vs "+scope);
		scoreTxt.setHTML(homeClanScore+" : "+enemyClanScore);
		endDateTxt.setHTML(DateTimeFmt.getString(endDate, DateTimeFmt.FmtDate()));
		if(homeClanScore > enemyClanScore){
			statusImg.setHTML(ResHelper.makeImgHtml(ResHelper.getCwWonImgAbsUrl(),STATUS_IMG_SIZE,STATUS_IMG_SIZE));
		}else if(homeClanScore < enemyClanScore){
			statusImg.setHTML(ResHelper.makeImgHtml(ResHelper.getCwLostImgAbsUrl(),STATUS_IMG_SIZE,STATUS_IMG_SIZE));
		}else{
			statusImg.setHTML(ResHelper.makeImgHtml(ResHelper.getCwDrawImgAbsUrl(),STATUS_IMG_SIZE,STATUS_IMG_SIZE));
		}
	}
	
	private void	initLayout(){
		scorePanel.clear();
		FlexTable txtTable=new FlexTable();
		GridHelper pusher=new GridHelper(txtTable);
		pusher.pushBack(scopeTxt).nextRow().pushBack(scoreTxt);
		GridHelper.HorizontalAlign.alignAllMirrorCenter(txtTable);
		scorePanel.add(txtTable);
	}
	
}
