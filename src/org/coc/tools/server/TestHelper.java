package org.coc.tools.server;

import java.util.ArrayList;
import java.util.Date;
import org.coc.tools.shared.RandomNumber;
import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;


public class TestHelper {
	
	public static Clan createClanForTest(){
		RandomNumber rd=RandomNumber.getInstance();
		return new Clan("#"+rd.randInt(1, 9999),"clan-name-"+rd.randInt(1, 9999),"0"+rd.randInt(1, 50));
	}
	
	public	static ArrayList<CWIndex> createCWIndexForTest(long count){
		ArrayList<CWIndex> list= new ArrayList<CWIndex>();
		for(long i=0;i<count;++i){
			CWIndex one=TestHelper.createCWIndexForTest();
			one.setRowId(i);
			list.add(one);
		}
		return list;
	}
	
	@SuppressWarnings("deprecation")
	public	static CWIndex createCWIndexForTest(){
		RandomNumber rd=RandomNumber.getInstance();
		CWIndex one = new CWIndex();

		one.setEnemyClan(createClanForTest());
		one.setHomeClan(createClanForTest());
		one.setScope(rd.randInt(3, 10)*5);
		Date prepareDay=new Date();
		prepareDay.setDate(rd.randInt(2, 25));
		one.setPrepareDate(prepareDay);
		return one;
	}
}
