package org.coc.tools.server;

import java.util.logging.Logger;

import org.coc.tools.server.model.*;
import org.coc.tools.shared.model.*;

import javax.servlet.ServletException;

import org.coc.tools.server.config.ConfigUtilities;
import org.coc.tools.server.dao.SysUserDao;
//import org.coc.tools.shared.model.SysUser;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFilter;
//import com.googlecode.objectify.Objectify;
//import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.util.Closeable;

public class AppInit  extends RemoteServiceServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2035948506395614921L;
	private static final Logger log = Logger.getLogger(AppInit.class.getName());

	//private		Objectify ofy=MyOfyService.ofy();
	//private 	SysUserDao	userDao=new  SysUserDao(ofy);
	
	@Override
    public void init() throws ServletException {
		String user_home=System.getProperty("user.home");
		log.info("user.home is :"+user_home);
		log.info("init Started ");
        super.init();
        //log.info("importRootUser");
        //importRootUser();
        //updateRootUser();
        log.info("init Done");
    }
	private void	regEntitys(){
/*
		ObjectifyService.register(Clan.class);
		ObjectifyService.register(CWIndex.class);
		ObjectifyService.register(WarBaseOrder.class);
		ObjectifyService.register(WarDetail.class);
		ObjectifyService.register(WarResult.class);
		ObjectifyService.register(ClanWarEntryPojo.class);
		ObjectifyService.register(SysUser.class);
		ObjectifyService.register(PermissionList.class);
        //
        
        
        
        
        //// server end only entitys
		ObjectifyService.register(ClanWarEntry.class);
        
		log.info("Objectify Entity register end");*/
	}
	private void	importRootUser() throws ServletException{
/*
		ConfigUtilities cfg= ConfigUtilities.loadDefaultRoot();
		if(cfg.loaded()){
			String id=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_ID);
			String password=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_PWD);
			if(id!=null && id.length()>0 && password!=null && password.length()>0 ){
				SysUser userInDb=userDao.loadOne(SysUser.class, "loginId", id);
				if(userInDb==null){
					String pwdHash=BCrypt.hashpw(password, BCrypt.gensalt());
					if(!BCrypt.checkpw(password, pwdHash)){
						throw new ServletException("test failed:password hash does not match the plain txt");
					}
					userInDb=new SysUser();
					userInDb.setDisplayName("root");
					userInDb.setLoginId(id);
					userInDb.setPassword(pwdHash);
					userDao.save(userInDb);
					
				}
			}
		}*/
	}
	private void	updateRootUser(){
		/*
		SysUser userInDb=new SysUser();
		userInDb.setDisplayName("root");
		userInDb.setLoginId("root");
		userInDb.setPassword("root");
		Objectify ofy = ObjectifyService.ofy();
		ofy.save().entity(userInDb).now();
		*/
		/*Closeable closeable = ObjectifyService.begin();
		closeable.*/
		//Objectify ofy = ObjectifyService.begin();
		
		/*Query<SysUser> queryusers = ofy.query(SysUser.class);
		for (User user : queryusers) {
			user.resetContacts();
			user.resetPetionContacts();
			ofy.put(user);
		}*/
	}
	private void	importRootUser(int x) throws ServletException{
		
		/*ConfigUtilities cfg= ConfigUtilities.loadDefaultRoot();
		if(cfg.loaded()){
			String id=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_ID);
			String password=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_PWD);
			if(id!=null && id.length()>0 && password!=null && password.length()>0 ){
				String pwdHash=BCrypt.hashpw(password, BCrypt.gensalt());
				if(!BCrypt.checkpw(password, pwdHash)){
					log.severe("test failed:password hash does not match the plain txt");
					throw new ServletException("test failed:password hash does not match the plain txt");
				}
			}
		}*/
		///////////////////////////////////////////////////////////////
		//ObjectifyService.run()
		/*
		*/
	}
}
