package org.coc.tools.server;

import java.util.logging.Logger;

import org.coc.tools.server.model.*;
import org.coc.tools.shared.model.*;

import com.google.gwt.core.shared.GWT;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
 
public class MyOfyService {
	private static final Logger log = Logger.getLogger(MyOfyService.class.getName());
    static {
        log.info("Objectify Entity register start");
    	 //// shared entitys    
        //factory().register(ObjectifyEntity.class);
    	
        factory().register(Clan.class);
        factory().register(CWIndex.class);
        factory().register(WarBaseOrder.class);
        factory().register(WarDetail.class);
        factory().register(WarResult.class);
        factory().register(ClanWarEntryPojo.class);
        factory().register(SysUser.class);
        factory().register(PermissionList.class);
        //   
        
        //// server end only entitys
        factory().register(ClanWarEntry.class);
        
        log.info("Objectify Entity register end");

    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
    public static Objectify createTransaction(){
    	return factory().begin();
    }
}
