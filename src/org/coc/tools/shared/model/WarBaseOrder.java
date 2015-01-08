package org.coc.tools.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

//@Subclass(index=true)
@Entity
public class WarBaseOrder  implements Serializable,ObjectifyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402974585267023573L;

	@Index
	private Long	warId;
	@Index
	private String attacker="";
	private int attackerTHLevel=5;
	private int targetNumber=1;
	private int targetTHLevel=5;
	@Index
	private int goal=3;
	@Index
	private Long	targetClanId=0L;
	@Index
	private Long	attackerClanId=0L;
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
	
	public Long getTargetClanId() {
		return targetClanId;
	}
	public void setTargetClanId(Long targetClanId) {
		this.targetClanId = targetClanId;
	}
	public Long getAttackerClanId() {
		return attackerClanId;
	}
	public void setAttackerClanId(Long attackerClanId) {
		this.attackerClanId = attackerClanId;
	}
	public Long getWarId() {
		return warId;
	}
	public void setWarId(Long warId) {
		this.warId = warId;
	}
	public String getAttacker() {
		return attacker;
	}
	public int getAttackerTHLevel() {
		return attackerTHLevel;
	}
	public int getTargetNumber() {
		return targetNumber;
	}
	public int getTargetTHLevel() {
		return targetTHLevel;
	}
	public int getGoal() {
		return goal;
	}
	public void setAttacker(String attacker) {
		this.attacker = attacker;
	}
	public void setAttackerTHLevel(int attackerTHLevel) {
		this.attackerTHLevel = attackerTHLevel;
	}
	public void setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
	}
	public void setTargetTHLevel(int targetTHLevel) {
		this.targetTHLevel = targetTHLevel;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	

}
