package org.coc.tools.client.widget;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.shared.model.ClanWarEntryPojo;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CWResultLessPanel implements IsWidget{

	private List<ClanWarEntryPojo>	cwResultList;
	private final VerticalPanel	holder;
	private final FlexTable resultTable;
	//private int borderWidth=0;
	
	public CWResultLessPanel(){
		cwResultList=new ArrayList<ClanWarEntryPojo>();
		holder=new VerticalPanel();
		resultTable=new FlexTable();
		initLayout();
	}
	public 	 void setBorderWidth(int width){
		holder.setBorderWidth(width);
	}
	public 	 void setGridBorderWidth(int width){
		resultTable.setBorderWidth(width);
	}
	public Widget asWidget(){
		return holder;
	}
	public void	addData(ClanWarEntryPojo data){

		String paddingWidth="32px";
		int nextRow=0;
		if(resultTable.getRowCount()>0){
			nextRow=resultTable.getRowCount();
		}
		GridHelper pusher=new GridHelper(resultTable,nextRow);
		CWResultUnitLess rowOne=new CWResultUnitLess();
		rowOne.updateVal(data.getWarIndex().getScope(), data.getWarIndex().getEndDate(), data.getWarIndex().getHomeClan(), data.getHomeClanWarResult().getFinalStars(), data.getWarIndex().getEnemyClan(), data.getEnemyClanWarResult().getFinalStars());
		
		pusher.pushBack(GridHelper.paddingHtml(paddingWidth,"# "+Integer.toString(nextRow+1)))
		.pushBack(rowOne.getStatusImg())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getHomeClanPanel().asWidget())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getScorePanel())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getEnemyClanPanel().asWidget())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getEndDateTxt())
		.pushBack(GridHelper.paddingHtml(paddingWidth));
		
	}
	public void	addData(ClanWarEntryPojo data,final Button btn){

		String paddingWidth="32px";
		int nextRow=0;
		if(resultTable.getRowCount()>0){
			nextRow=resultTable.getRowCount();
		}
		GridHelper pusher=new GridHelper(resultTable,nextRow);
		CWResultUnitLess rowOne=new CWResultUnitLess();
		rowOne.updateVal(data.getWarIndex().getScope(), data.getWarIndex().getEndDate(), data.getWarIndex().getHomeClan(), data.getHomeClanWarResult().getFinalStars(), data.getWarIndex().getEnemyClan(), data.getEnemyClanWarResult().getFinalStars());
		
		pusher.pushBack(GridHelper.paddingHtml(paddingWidth,"# "+Integer.toString(nextRow+1)))
		.pushBack(rowOne.getStatusImg())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getHomeClanPanel().asWidget())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getScorePanel())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getEnemyClanPanel().asWidget())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(rowOne.getEndDateTxt())
		.pushBack(GridHelper.paddingHtml(paddingWidth))
		.pushBack(btn)
		.pushBack(GridHelper.paddingHtml(paddingWidth));
		
	}
	public void addData(List<ClanWarEntryPojo> dataList){
		for(ClanWarEntryPojo data:dataList){
			addData(data);
		}
	}
	public void	clearData(){
		resultTable.clear();
	}
	private void	initLayout(){

		clearData();
		addData(cwResultList);
		holder.clear();
		holder.add(resultTable);
	}
}
