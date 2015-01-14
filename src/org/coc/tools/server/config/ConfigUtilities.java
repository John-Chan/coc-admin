package org.coc.tools.server.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class ConfigUtilities {
	//private static final ConfigUtilities INSTANCE = new ConfigUtilities();

	//PermissionDefine.properties .properties
	
	public static final String KEY_ROOT_USER_ID="rootUser.loginId";
	public static final String KEY_ROOT_USER_PWD="rootUser.password";
	
	public	static ConfigUtilities loadPermissionDefines(){
		return new ConfigUtilities("org/coc/tools/server/config/PermissionDefine");
	}
	public	static ConfigUtilities loadDefaultRoot(){
		return new ConfigUtilities("org/coc/tools/server/config/DefaultRootUser");
	}
	
	
	private ResourceBundle resourceBundle;

	//baseName - the base name of the resource bundle, a fully qualified class name 
	public ConfigUtilities(String baseName) {
		//resourceBundle = ResourceBundle.getBundle("com/your/package/Config");
		try{
			resourceBundle = ResourceBundle.getBundle(baseName);
		}catch(MissingResourceException e){
			resourceBundle=null;
		}catch(Exception e){
			resourceBundle=null;
		}
	}
	
	public boolean loaded(){
		return resourceBundle!=null;
	}
	public  int getConfigIntValue(String name) {
		return Integer.parseInt(getConfigStringValue(name));
	}

	public Set<String>		getKeySet(){
		return resourceBundle.keySet();
	}
	public  String getConfigStringValue(String name) {	
		return resourceBundle.getString(name);
	}


}
