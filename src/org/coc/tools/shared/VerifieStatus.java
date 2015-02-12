package org.coc.tools.shared;

public class VerifieStatus {
	private String msg;
	private boolean passed;

	public static	VerifieStatus	NoError( ){
		return new VerifieStatus(true,"Ok");
	}
	
	public static	VerifieStatus	NullError(String what){
		if(what!=null && what.length()>0){
			return new VerifieStatus(false,"Null value for "+what);
		}
		return new VerifieStatus(false,"Null value");
	}
	public static	VerifieStatus	LengthError(String what){
		if(what!=null && what.length()>0){
			return new VerifieStatus(false,"Bad length of "+what);
		}
		return new VerifieStatus(false,"Bad length");
	}
	public static	VerifieStatus	IllegalCharacterError(String what){
		if(what!=null && what.length()>0){
			return new VerifieStatus(false,"Illegal Character in "+what);
		}
		return new VerifieStatus(false,"Illegal Character");
	}
	public static	VerifieStatus	IllegalArgumentError(String what){
		if(what!=null && what.length()>0){
			return new VerifieStatus(false,"Illegal Argument : "+what);
		}
		return new VerifieStatus(false,"Illegal Argument");
	}
	
	public static	VerifieStatus	RangeError(String what,int minVal,int maxVal){
		if(what!=null && what.length()>0){
			return new VerifieStatus(false,"Bad "+what+",Value must in ["+minVal+","+maxVal+"]");
		}
		return new VerifieStatus(false,"Value must in ["+minVal+","+maxVal+"]");
	}
	
	public VerifieStatus(){
		this.msg=new String("");
		this.passed=true;
	}
	
	public VerifieStatus(boolean passed){
		this.msg=new String("");
		this.passed=passed;
	}
	public VerifieStatus(boolean passed,String msg){

		this.msg=msg;
		this.passed=passed;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean getPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
}
