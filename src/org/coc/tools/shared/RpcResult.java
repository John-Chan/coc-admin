package org.coc.tools.shared;

import java.io.Serializable;

public class RpcResult  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3464174576947875481L;
	
	public enum ERROR_CODE{EC_NO_ERR,EC_FAILED};
	public static String defaultErrmsg(ERROR_CODE ec){
		switch(ec){
		case EC_NO_ERR:return "Successed";
		case EC_FAILED:return "Failed";
		default:return "Unknow error code";
		}
	}
	private ERROR_CODE errorCode=ERROR_CODE.EC_NO_ERR;
	private String msg=defaultErrmsg(ERROR_CODE.EC_NO_ERR);
	
	public RpcResult(){
		//
	}
	public RpcResult(ERROR_CODE ec,String msg){
		this.setErrorCode(ec);
		this.setMsg(msg);
	}
	public boolean good(){
		return this.errorCode==ERROR_CODE.EC_NO_ERR;
	}
	public ERROR_CODE getErrorCode() {
		return errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setErrorCode(ERROR_CODE errorCode) {
		this.errorCode = errorCode;
	}
	public void setMsg(String msg) {
		if(msg==null){
			this.msg=defaultErrmsg(errorCode);
		}else{
			this.msg=msg;
		}
	}

	
}
