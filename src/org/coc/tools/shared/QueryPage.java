package org.coc.tools.shared;

import java.util.List;

public class QueryPage<DataElem> {

	/// the index of this page
	private	int	pageNumber=0;
	
	/// total row count for query
	private int totalRowCount=0;
	
	/// how many rows in this page
	private	int pageSize=1;
	
	/// datas
	List<DataElem>	resultSet=null;

	public QueryPage()
	{
		//
	}
	public QueryPage(int	pageNumber,int pageSize,int totalRowCount,List<DataElem>	resultSet)
	{
		this.pageNumber=pageNumber;
		this.totalRowCount=totalRowCount;
		this.pageSize=pageSize;
		this.resultSet=resultSet;
	}
	
	/*
	 * total row count for query
	 */
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	/**
	 * the index of this page
	 */
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	/*
	 * how many rows in this page
	 */
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<DataElem> getResultSet() {
		return resultSet;
	}
	public void setResultSet(List<DataElem> resultSet) {
		this.resultSet = resultSet;
	}
	public	int	rowFetched()
	{
		if(resultSet==null)
			return 0;
		return resultSet.size();
	}
	public	boolean	moreRow()
	{
		return remainRow()>0;
	}
	
	public	int	remainRow()
	{
		return this.totalRowCount - (this.pageNumber * this.pageSize);
	}
}
