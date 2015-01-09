package org.coc.tools.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.coc.tools.shared.model.CWIndex;
import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.ClanWarEntryPojo;
import org.coc.tools.shared.model.WarResult;


public class TestHelper {
	
	public static int makeCwScope(){

		RandomNumber rd=RandomNumber.getInstance();
		int base=rd.randInt(3, 10);
		return base*CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES;

	}
	@SuppressWarnings("deprecation")
	public static Date makeDate(){

		RandomNumber rd=RandomNumber.getInstance();
		Date dt=new Date();
		dt.setDate(rd.randInt(2, 25));
		return dt;

	}
	public static Clan createClanForTest(){
		RandomNumber rd=RandomNumber.getInstance();
		String tag="#"+rd.randInt(1, 9999);
		String name="clan-name-"+rd.randInt(1, 9999);
		String symbol =Integer.toString(rd.randInt(1, 50));
		return new Clan(tag,name,symbol);
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
	
	public	static CWIndex createCWIndexForTest(){
		CWIndex one = new CWIndex();

		one.setEnemyClan(createClanForTest());
		one.setHomeClan(createClanForTest());
		one.setScope(makeCwScope());
		one.setPrepareDate(makeDate());
		return one;
	}
	
	public static WarResult	makeWarResult(int scope){
		RandomNumber rd=RandomNumber.getInstance();
		int attacksUsed=WarResultBuilder.calcMaxAttacks(scope);
		int attacksWon=rd.randInt(attacksUsed/4 +1, attacksUsed);
		int star1=attacksWon-rd.randInt(1, attacksWon);
		int star2=attacksWon-rd.randInt(1, attacksWon);
		int star3=attacksWon-rd.randInt(1, attacksWon);
		WarResult one;
		try {
			one = WarResultBuilder.makeSimpleWarResult(scope, attacksUsed, attacksWon, star1, star2, star3);
		} catch (Exception e) {
			one=new WarResult();
			e.printStackTrace();
		}

		return one;
	}
	public static List<ClanWarEntryPojo>	makeClanWarEntryPojoList(int size,String homeClanTag,String homeClanName,String homeClanSymbol){
		List<ClanWarEntryPojo> list= new ArrayList<ClanWarEntryPojo>();
		Clan homeClan=new Clan(homeClanTag,homeClanName,homeClanSymbol);
		for(int i=0;i<size;++i){
			ClanWarEntryPojo one =new ClanWarEntryPojo();
			CWIndex cwindex=createCWIndexForTest();
			cwindex.setHomeClan(homeClan);
			one.setWarIndex(cwindex);
			one.setHomeClanWarResult(makeWarResult(cwindex.getScope()));
			one.setEnemyClanWarResult(makeWarResult(cwindex.getScope()));
			list.add(one);
		}
		return list;
	}
}
