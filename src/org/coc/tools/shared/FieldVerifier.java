package org.coc.tools.shared;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client-side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier {

	public static final String	BAD_CLAN_TAG_CHARS="o`~!@#$%^&*()-_=+;:'\",<.>/?\\";
	public static final String	GOOD_CLAN_TAG_CHARS_UPER="ABCDEFGHIJKLMNPQRSTUVWXYZ1234567890";
	public static  int tryParseInt(String val,int defVal){
		try{
			return Integer.parseInt(val);
		}catch(Exception e){
			//NumberFormatException
			return defVal;
		}
	}
	public static boolean isDigit(String val) {
		//int count=0;
		boolean good=false;
		try{
			//count=Integer.parseInt(val);
			Integer.parseInt(val);
			good=true;
		}catch(Exception e){
			//NumberFormatException
		}
		return good;
	}
	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * In this example, we only require that the name is at least four
	 * characters. In your application, you can use more complex checks to ensure
	 * that usernames, passwords, email addresses, URLs, and other fields have the
	 * proper syntax.
	 * 
	 * @param name the name to validate
	 * @return true if valid, false if invalid
	 */
	public static VerifieStatus isValidClanName(String val) {
		String what="Clan Name";
		if (val == null) {
			return VerifieStatus.NullError(what);
		}
		if (val.length()<3) {
			return VerifieStatus.LengthError(what);
		}
		return VerifieStatus.NoError();
	}
	
	public static VerifieStatus isValidClanTag(String val) {
		String what="Clan Tag";
		if (val == null) {
			return VerifieStatus.NullError(what);
		}
		if (val.length()<5) {
			return VerifieStatus.LengthError(what);
		}
		if (false == val.startsWith(CocConstant.ClanInfo.CLAN_TAG_PREFIX)) {
			return new VerifieStatus(false,what+" should start with a '"+CocConstant.ClanInfo.CLAN_TAG_PREFIX+"'");
		}
		int pos=1;
		String txtUper=val.substring(pos).toUpperCase();
		
		for(char c:txtUper.toCharArray()){
			if(GOOD_CLAN_TAG_CHARS_UPER.indexOf(c)<0){
				return new VerifieStatus(false,what+" found bad character:"+c+" at pos:"+pos);
			}
			pos++;
		}
		return VerifieStatus.NoError();
	}
	public static VerifieStatus isValidClanSymbol(String val) {
		String what="Clan Symbol";
		if (val == null) {
			return VerifieStatus.NullError(what);
		}
		if (!isDigit(val)) {
			return VerifieStatus.IllegalCharacterError(what);
		}
		int count=Integer.parseInt(val);
		if (count<CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE || count>CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE) {
			return VerifieStatus.RangeError(what,CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE,CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE);
		}
		return VerifieStatus.NoError();
	}
	
	public static VerifieStatus isValidCwPlayerCount(String val) {
		String what="Clan War Player Count";
		if (val == null) {
			return VerifieStatus.NullError(what);
		}
		if (!isDigit(val)) {
			return VerifieStatus.IllegalCharacterError(what);
		}
		int count=Integer.parseInt(val);
		if(count <CocConstant.WarCounters.MIN_PLAYER_COUNT || count> CocConstant.WarCounters.MAX_PLAYER_COUNT ){
			return VerifieStatus.RangeError(what,CocConstant.WarCounters.MIN_PLAYER_COUNT,CocConstant.WarCounters.MAX_PLAYER_COUNT);
		}
		if ((count%CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES)!=0 ) {
			return new VerifieStatus(false,what+" should increnment by "+CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES);
		}
		return VerifieStatus.NoError();
	}
}
