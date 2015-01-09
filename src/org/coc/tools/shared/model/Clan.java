package org.coc.tools.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.*;

@Entity
public class Clan /*extends ObjectifyEntity*/ implements Serializable,ObjectifyEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5291890087317473736L;

	public enum REG_STATUS{NON_REGED,REGED};
	
	@Index
	private String	clanName="";
	private String	clanSymbol="1";
	@Index
	private String	clanTag="";
	private String	clanLocate="International";
	private int		trophiesRequired=1000;
	private	int		clanWarFrequencyWeekly=1;
	@Index
	private	REG_STATUS		registered=REG_STATUS.NON_REGED;

	@Id
	private Long	id;

	@Override
	public Long getRowId() {
		return this.id;
	}
	@Override
	public void setRowId(Long id) {
		this.id = id;
	}

	public Clan(){
		
	}
	public Clan(String	clanTag,String	clanName,String	clanSymbol){
		this.setClanTag(clanTag);
		this.setClanName(clanName);
		this.setClanSymbol(clanSymbol);
		//this.clanTag=clanTag;
		//this.clanName=clanName;
		//this.clanSymbol=clanSymbol;
	}

	public Clan(String	clanTag,String	clanName,String	clanSymbol,REG_STATUS reged){
		this.setClanTag(clanTag);
		this.setClanName(clanName);
		this.setClanSymbol(clanSymbol);
		this.registered=reged;
	}
	
	public Clan	copyWithoutId(final Clan another){
		this.clanTag=another.clanTag;
		this.clanName=another.clanName;
		this.clanSymbol=another.clanSymbol;
		this.registered=another.registered;
		this.clanLocate=another.clanLocate;
		this.trophiesRequired=another.trophiesRequired;
		this.clanWarFrequencyWeekly=another.clanWarFrequencyWeekly;
		
		return this;
	}
	public Clan	copyFull(final Clan another){
		copyWithoutId(another);
		this.setRowId(another.getRowId());
		return this;
	}
	
	public String getClanName() {
		return clanName;
	}
	public String getClanSymbol() {
		return clanSymbol;
	}
	public String getClanTag() {
		return clanTag;
	}
	public String getClanLocate() {
		return clanLocate;
	}
	public int getTrophiesRequired() {
		return trophiesRequired;
	}
	public int getClanWarFrequencyWeekly() {
		return clanWarFrequencyWeekly;
	}
	public REG_STATUS getRegistered() {
		return registered;
	}
	
	public void setClanName(String clanName) {
		this.clanName = clanName.trim();
	}
	public void setClanSymbol(String clanSymbol) {
		this.clanSymbol = clanSymbol.trim();
	}
	public void setClanTag(String clanTag) {
		this.clanTag = clanTag.trim().toUpperCase();
	}
	public void setClanLocate(String clanLocate) {
		this.clanLocate = clanLocate;
	}
	public void setTrophiesRequired(int trophiesRequired) {
		this.trophiesRequired = trophiesRequired;
	}
	public void setClanWarFrequencyWeekly(int clanWarFrequencyWeekly) {
		this.clanWarFrequencyWeekly = clanWarFrequencyWeekly;
	}
	public void setRegistered(REG_STATUS registered) {
		this.registered = registered;
	}
	
	

}
