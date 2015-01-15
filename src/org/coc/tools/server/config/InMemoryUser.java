package org.coc.tools.server.config;

//import java.util.logging.Logger;

import org.coc.tools.shared.model.SysUser;
import org.mindrot.jbcrypt.BCrypt;

public class InMemoryUser {

	//private static final Logger log = Logger.getLogger(InMemoryUser.class.getName());

	public static final InMemoryUser RootAdmin=loadRootUser();


	private static final InMemoryUser	loadRootUser(){
		ConfigUtilities cfg= ConfigUtilities.loadDefaultRoot();
		String id=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_ID);
		String password=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_PWD);
		return new InMemoryUser(id,password,"web-app administrator");
	}
	
	private final SysUser user;
	
	private InMemoryUser(String id,String plainPwdTxt,String displayName){
		user=new SysUser();
		user.setDisplayName("web-app administrator");
		user.setLoginId(id);
		String pwdHash=BCrypt.hashpw(plainPwdTxt, BCrypt.gensalt());
		user.setPassword(pwdHash);
	}
	public boolean	checkPassword(String plainPwdTxt){
		return BCrypt.checkpw(plainPwdTxt,user.getPassword());
	}
	public String getLoginId() {
		return user.getLoginId();
	}
	public String getDisplayName() {
		return user.getDisplayName();
	}
	
}
