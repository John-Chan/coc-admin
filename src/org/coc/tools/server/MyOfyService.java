package org.coc.tools.server;

import org.coc.tools.server.model.*;

import org.coc.tools.shared.model.*;

/*import org.coc.tools.server.model.ClanWarEntry;

import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.PermissionList;
import org.coc.tools.shared.model.SysUser;
import org.coc.tools.shared.model.WarBaseOrder;
import org.coc.tools.shared.model.WarDetail;
import org.coc.tools.shared.model.WarResult;*/

import com.google.gwt.core.shared.GWT;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
 
public class MyOfyService {
    static {
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
        
        GWT.log("Objectify Entity register end");
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
