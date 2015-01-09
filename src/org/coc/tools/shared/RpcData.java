package org.coc.tools.shared;

public class RpcData<DataType> extends  RpcResult {

	private DataType	data=null;
	
	public RpcData(){
		super();
	}
	public RpcData(ERROR_CODE ec,String msg){
		super(ec,msg);
	}

	public RpcData(ERROR_CODE ec,String msg,DataType data){
		super(ec,msg);
		this.setData(data);
	}
	public boolean haveData(){
		return this.data!=null;
	}

	public DataType getData() {
		return data;
	}
	public void setData(DataType data) {
		this.data = data;
	}
}
