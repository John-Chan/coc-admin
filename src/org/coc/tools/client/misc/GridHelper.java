package org.coc.tools.client.misc;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

public class GridHelper {

	public static class VerticalAlign{

		/// align top for all Widget in a row 
		public static void alignAllToTop(final HTMLTable	ref,int row){
			int colCount=ref.getCellCount(row);
			for(int n=0;n<colCount;++n){
				//ref.getCellFormatter().setHorizontalAlignment(row, n, DockPanel.ALIGN_TOP);
				ref.getCellFormatter().setVerticalAlignment(row, n, DockPanel.ALIGN_TOP);
			}
		}
		public static void alignAllToBottom(final HTMLTable	ref,int row){
			int colCount=ref.getCellCount(row);
			for(int n=0;n<colCount;++n){
				ref.getCellFormatter().setVerticalAlignment(row, n, DockPanel.ALIGN_BOTTOM);
			}
		}
		public static void alignAllToTop(final HTMLTable	ref){
			int rowCount=ref.getRowCount();
			for(int n=0;n<rowCount;++n){
				alignAllToTop(ref,n);
			}
		}
		public static void alignAllToBottom(final HTMLTable	ref){
			int rowCount=ref.getRowCount();
			for(int n=0;n<rowCount;++n){
				alignAllToBottom(ref,n);
			}
		}
		/*
		public static void alignAllToVCenter(final HTMLTable	ref,int row){
			int colCount=ref.getCellCount(row);
			for(int n=0;n<colCount;++n){
				//ref.getCellFormatter().setHorizontalAlignment(row, n, DockPanel.ALIGN_TOP);
				ref.getCellFormatter().setVerticalAlignment(row, n, DockPanel.ALIGN_CENTER);
			}
		}
		*/
	}
	private final HTMLTable	ref;
	private int	rowIndex=0;
	private int colIndex=0;
	
	
	public static void setColWidth(final HTMLTable	ref,int row,String[] width){
		int colCount=ref.getCellCount(row);
		int current=0;
		for(String v:width){
			if(current>=colCount)
				break;
			ref.getCellFormatter().setWidth(row, current, v);
			current++;
		}
	}
	
	public static void setColWidth(final HTMLTable	ref,String[] width){
		int rowCount=ref.getRowCount();
		for(int n=0;n<rowCount;++n){
			setColWidth(ref,n,width);
		}
	}
	
	
	public GridHelper(HTMLTable	ref){
		this.ref=ref;
	}
	public GridHelper resetPos(int	rowIndex,int colIndex){
		this.rowIndex=rowIndex;
		this.colIndex=colIndex;
		return this;
	}
	public GridHelper resetPos(){
		this.rowIndex=0;
		this.colIndex=0;
		return this;
	}
	public GridHelper nextRow(){
		this.rowIndex++;
		this.colIndex=0;
		return this;
	} 

	public GridHelper nextCol(){
		this.colIndex++;
		return this;
	}
	public int getCurrentRow(){
		return this.rowIndex;
	}
	public int getCurrentCol(){
		return this.colIndex;
	}
	
	/// push back in current row
	public GridHelper pushBack(Widget widget){
		this.ref.setWidget(rowIndex, colIndex, widget);
		return nextCol();
	}
	
	
}