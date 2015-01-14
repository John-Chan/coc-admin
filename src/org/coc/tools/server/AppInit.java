package org.coc.tools.server;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.coc.tools.server.config.ConfigUtilities;
//import org.coc.tools.server.dao.SysUserDao;
//import org.coc.tools.shared.model.SysUser;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
//import com.googlecode.objectify.Objectify;
//import com.googlecode.objectify.ObjectifyService;

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
		log.info("init Started ");
        super.init();
        log.info("importRootUser");
        importRootUser();

        log.info("init Done");
    }
	
	private void	importRootUser() throws ServletException{
		
		ConfigUtilities cfg= ConfigUtilities.loadDefaultRoot();
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
		}
		///////////////////////////////////////////////////////////////
		//ObjectifyService.run()
		/*
		ConfigUtilities cfg= ConfigUtilities.loadDefaultRoot();
		if(cfg.loaded()){
			String id=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_ID);
			String password=cfg.getConfigStringValue(ConfigUtilities.KEY_ROOT_USER_PWD);
			if(id!=null && id.length()>0 && password!=null && password.length()>0 ){
				SysUser userInDb=userDao.loadOne(SysUser.class, "loginId", id);
				if(userInDb==null){
					String pwdHash=BCrypt.hashpw(password, BCrypt.gensalt());
					if(BCrypt.checkpw(password, pwdHash)){
						throw new ServletException("test failed:password hash does not match the plain txt");
					}
					userInDb=new SysUser();
					userInDb.setDisplayName("root");
					userInDb.setLoginId(id);
					userInDb.setPassword(pwdHash);
					userDao.save(userInDb);
					
				}
			}
		}
		*/
	}
}
