package org.coc.tools.shared;

public class RpcData<DataType> extends  RpcResult {

	private DataType	data=null;
	

	public RpcData(ERROR_CODE ec,String msg,DataType data){
		this.setErrorCode(ec);
		this.setMsg(msg);
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
