package org.coc.tools.client.misc;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

public class GridHelper {

	public static HTML paddingHtml(){
		return new HTML("");
	}
	public static HTML paddingHtml(String width){
		HTML padding= new HTML("");
		padding.setWidth(width);
		return padding;
	}
	public static HTML paddingHtml(String width,String txt){
		HTML padding= new HTML(txt);
		padding.setWidth(width);
		return padding;
	}
	//
	public static class HorizontalAlign{

		public static void alignAllMirrorCenter(final HTMLTable	ref,int row){
			int colCount=ref.getCellCount(row);

			//  colcount==4 : left [0~2) right[2,4)
			//  colcount==5 : left [0~2) right[3,5) center=2
			//
			int center=colCount/2;
			int leftEnd=center;  
			int rightStart= ((colCount%2)==0)? center:center+1 ; 

			for(int colIndex =0;colIndex<colCount;++colIndex){
				//[0,2)
				if(colIndex >= 0 && colIndex < leftEnd){
					ref.getCellFormatter().setHorizontalAlignment(row, colIndex, DockPanel.ALIGN_RIGHT);
					continue;
				}
				//[3,5)
				if(colIndex >= rightStart && colIndex < colCount){
					ref.getCellFormatter().setHorizontalAlignment(row, colIndex, DockPanel.ALIGN_LEFT);
					continue;
				}
				// 2
				ref.getCellFormatter().setHorizontalAlignment(row, colIndex, DockPanel.ALIGN_CENTER);	
			}

			/*
			for(int n=0;n<colCount;++n){
				ref.getCellFormatter().setVerticalAlignment(row, n, DockPanel.ALIGN_BOTTOM);
			}
			*/
		}
		public static void alignAllMirrorCenter(final HTMLTable	ref){
			int rowCount=ref.getRowCount();
			for(int n=0;n<rowCount;++n){
				alignAllMirrorCenter(ref,n);
			}
		}
	}
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
	
	
	public GridHelper(HTMLTable	ref,int startRowIndex){
		this.ref=ref;
		resetPos(startRowIndex,0);
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
