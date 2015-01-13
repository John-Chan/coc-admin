package org.coc.tools.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class WarResult   implements Serializable,ObjectifyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6282962981468178879L;

	@Index
	private Long	warId;
	@Index
	private Long	clanId;
	private int		playerCount=0;
	private	int		maxAccackCount=0;
	private	int		attacksUsed=0;
	private	int		attacksLost=0;
	private	int		attacksWon=0;
	private	int		attacksRemaining=0;
	private	int		totalStars1Count=0;
	private	int		totalStars2Count=0;
	private	int		totalStars3Count=0;
	private int 	finalStars=0;
	private	float	newStarPeerAttack=0;
	private	float	averageDestruction=0;
	private	int		averageAttackDurationSecond=0;
	@Index
	private String	heroicAttackPlayer="";
	@Index
	private String	heroicDefensePlayer="";
	@Id
	private Long	id;

	@Index
	private boolean locked=false;
	
	@Override
	public Long getRowId() {
		return this.id;
	}
	@Override
	public void setRowId(Long id) {
		this.id = id;
	}
	
	public Long getClanId() {
		return clanId;
	}
	public void setClanId(Long clanId) {
		this.clanId = clanId;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public int getAttacksUsed() {
		return attacksUsed;
	}
	public void setAttacksUsed(int attacksUsed) {
		this.attacksUsed = attacksUsed;
	}
	public int getAttacksWon() {
		return attacksWon;
	}
	public void setAttacksWon(int attacksWon) {
		this.attacksWon = attacksWon;
	}
	public int getAttacksLost() {
		return this.attacksLost;
	}
	public void setAttacksLost(int attacksLost) {
		this.attacksLost = attacksLost;
	}
	public int getAttacksRemaining() {
		return attacksRemaining;
	}
	public void setAttacksRemaining(int attacksRemaining) {
		this.attacksRemaining = attacksRemaining;
	}
	
	public int getTotalStars1Count() {
		return this.totalStars1Count;
	}
	public void setTotalStars1Count(int totalStars1Count) {
		this.totalStars1Count = totalStars1Count;
	}
	public int getTotalStars2Count() {
		return this.totalStars2Count;
	}
	public void setTotalStars2Count(int totalStars2Count) {
		this.totalStars2Count = totalStars2Count;
	}
	public int getTotalStars3Count() {
		return this.totalStars3Count;
	}
	public void setTotalStars3Count(int totalStars3Count) {
		this.totalStars3Count = totalStars3Count;
	}
	public float getNewStarPeerAttack() {
		return newStarPeerAttack;
	}
	public void setNewStarPeerAttack(float newStarPeerAttack) {
		this.newStarPeerAttack = newStarPeerAttack;
	}
	public float getAverageDestruction() {
		return averageDestruction;
	}
	public void setAverageDestruction(float averageDestruction) {
		this.averageDestruction = averageDestruction;
	}
	public int getAverageAttackDurationSecond() {
		return averageAttackDurationSecond;
	}
	public void setAverageAttackDurationSecond(int averageAttackDurationSecond) {
		this.averageAttackDurationSecond = averageAttackDurationSecond;
	}
	public String getHeroicAttackPlayer() {
		return heroicAttackPlayer;
	}
	public void setHeroicAttackPlayer(String heroicAttackPlayer) {
		this.heroicAttackPlayer = heroicAttackPlayer;
	}
	public String getHeroicDefensePlayer() {
		return heroicDefensePlayer;
	}
	public void setHeroicDefensePlayer(String heroicDefensePlayer) {
		this.heroicDefensePlayer = heroicDefensePlayer;
	}

	public int getFinalStars() {
		return this.finalStars;
	}
	public int setFinalStars(int finalStars) {
		return this.finalStars=finalStars;
	}
	public int getMaxAccackCount(){
		return this.maxAccackCount;
	}

	public int setMaxAccackCount(int maxAccackCount){
		return this.maxAccackCount=maxAccackCount;
	}
	public Long getWarId() {
		return warId;
	}
	public void setWarId(Long warId) {
		this.warId = warId;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
