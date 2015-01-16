package org.coc.tools.server.dataimp;

public class DataUnit<ID_TYPE,DATA_TYPE> {
	private final ID_TYPE	id;
	private final DATA_TYPE data;
	
	public DataUnit(ID_TYPE id,DATA_TYPE data){
		this.id=id;
		this.data=data;
	}

	public ID_TYPE getId() {
		return id;
	}

	public DATA_TYPE getData() {
		return data;
	}
	
}
