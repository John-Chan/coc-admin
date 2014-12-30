package org.coc.tools.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class WarDetail  extends ObjectifyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9014965538681368610L;

	private Long	warId;
	private int targetNumber=1;
	private int targetTHLevel=5;
	private Long	targetClanId=0L;
	private String attacker="";
	private int attackerTHLevel=5;
	private Long	attackerClanId=0L;
	private int	thisStars=0;
	private int historyBestStars=0;
	private int	damagePrecent=0;
	private Date attackDate=new Date();
	
	
	public Long getWarId() {
		return warId;
	}
	public void setWarId(Long warId) {
		this.warId = warId;
	}
	public int getTargetNumber() {
		return targetNumber;
	}
	public void setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
	}
	public int getTargetTHLevel() {
		return targetTHLevel;
	}
	public void setTargetTHLevel(int targetTHLevel) {
		this.targetTHLevel = targetTHLevel;
	}
	public Long getTargetClanId() {
		return targetClanId;
	}
	public void setTargetClanId(Long targetClanId) {
		this.targetClanId = targetClanId;
	}
	public String getAttacker() {
		return attacker;
	}
	public void setAttacker(String attacker) {
		this.attacker = attacker;
	}
	public int getAttackerTHLevel() {
		return attackerTHLevel;
	}
	public void setAttackerTHLevel(int attackerTHLevel) {
		this.attackerTHLevel = attackerTHLevel;
	}
	public Long getAttackerClanId() {
		return attackerClanId;
	}
	public void setAttackerClanId(Long attackerClanId) {
		this.attackerClanId = attackerClanId;
	}
	public int getThisStars() {
		return thisStars;
	}
	public void setThisStars(int thisStars) {
		this.thisStars = thisStars;
	}
	public int getHistoryBestStars() {
		return historyBestStars;
	}
	public void setHistoryBestStars(int historyBestStars) {
		this.historyBestStars = historyBestStars;
	}
	public int getDamagePrecent() {
		return damagePrecent;
	}
	public void setDamagePrecent(int damagePrecent) {
		this.damagePrecent = damagePrecent;
	}
	public Date getAttackDate() {
		return attackDate;
	}
	public void setAttackDate(Date attackDate) {
		this.attackDate = attackDate;
	}
	
	
}
