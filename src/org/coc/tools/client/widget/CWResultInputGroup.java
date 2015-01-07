package org.coc.tools.client.widget;

import java.util.ArrayList;
import java.util.List;

import org.coc.tools.client.view.UiStrConstants;
import org.coc.tools.shared.CocConstant;
import org.coc.tools.shared.FieldVerifier;
import org.coc.tools.shared.WarResultBuilder;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class CWResultInputGroup {

	private static final int BAD_NUMBER=-99999999;
	private	final TextBox		attacksUsed;
	private	final TextBox		attacksWon;
	private	final TextBox		totalStars1Count;
	private	final TextBox		totalStars2Count;
	private	final TextBox		totalStars3Count;
	private final TextBox		heroicAttackPlayer;
	private final TextBox		heroicDefensePlayer;
	/// auto field
	private	final TextBox		attacksRemaining;
	private	final TextBox		attacksLost;
	private final TextBox 	finalStars;
	private	final TextBox	newStarPeerAttack;
	/// no impl
	//private	float	averageDestruction=0;
	//private	int		averageAttackDurationSecond=0;
	
	private  int	scope;
	private boolean autoFillEnabled=true;

	public List<Widget>		getInputWidgets(){
		List<Widget> list= getRequiredInputWidgets();
		list.addAll(getOptionalInputWidgets());
		return list;
	}
	
	public List<Widget>		getRequiredInputWidgets(){
		List<Widget> list= new ArrayList<Widget>();
		/// human friendly ordering 
		list.add(attacksUsed);
		list.add(attacksWon);
		list.add(totalStars1Count);
		list.add(totalStars2Count);
		list.add(totalStars3Count);
		list.add(heroicAttackPlayer);
		list.add(heroicDefensePlayer);
		return list;
	}
	
	public List<Widget>		getOptionalInputWidgets(){

		List<Widget> list= new ArrayList<Widget>();
		/// human friendly ordering 
		list.add(attacksRemaining);
		list.add(attacksLost);
		list.add(finalStars);
		list.add(newStarPeerAttack);
		return list;
	}
	public CWResultInputGroup(){
		this.scope=CocConstant.WarCounters.MIN_PLAYER_COUNT;
		
		attacksUsed=new TextBox();
		attacksUsed.setTitle(UiStrConstants.ValueNames.WAR_ATTACKS_USED);
		
		attacksWon=new TextBox();
		attacksWon.setTitle(UiStrConstants.ValueNames.WAR_ATTACKS_WON);
		
		totalStars1Count=new TextBox();
		totalStars1Count.setTitle(UiStrConstants.ValueNames.WAR_1_STAR);
		
		totalStars2Count=new TextBox();
		totalStars2Count.setTitle(UiStrConstants.ValueNames.WAR_2_STAR);
		
		totalStars3Count=new TextBox();
		totalStars3Count.setTitle(UiStrConstants.ValueNames.WAR_3_STAR);
		
		heroicAttackPlayer=new TextBox();
		heroicAttackPlayer.setTitle(UiStrConstants.ValueNames.WAR_MOST_HEROIC_ATTACK);
		
		heroicDefensePlayer=new TextBox();
		heroicDefensePlayer.setTitle(UiStrConstants.ValueNames.WAR_MOST_HEROIC_DEFENSE);
		
		/// auto field
		attacksRemaining=new TextBox();
		attacksRemaining.setTitle(UiStrConstants.ValueNames.WAR_ATTACKS_REMAINING);
		
		attacksLost=new TextBox();
		attacksLost.setTitle(UiStrConstants.ValueNames.WAR_ATTACKS_LOST);
		
		finalStars=new TextBox();
		finalStars.setTitle(UiStrConstants.ValueNames.WAR_FINAL_STARS);
		
		newStarPeerAttack=new TextBox();
		newStarPeerAttack.setTitle(UiStrConstants.ValueNames.WAR_NEW_STAR_PEER_ATTACK);
		
		
		attacksUsed.setMaxLength(3);
		attacksWon.setMaxLength(3);
		totalStars1Count.setMaxLength(2);
		totalStars2Count.setMaxLength(2);
		totalStars3Count.setMaxLength(2);
		heroicAttackPlayer.setMaxLength(32);
		heroicDefensePlayer.setMaxLength(32);
		
		attacksRemaining.setMaxLength(3);
		attacksLost.setMaxLength(3);
		finalStars.setMaxLength(3);
		newStarPeerAttack.setMaxLength(4);
		
		setEbableAutoFill(true);
		setAutoFillHander();
	}

	public void		clearInputs(){
		attacksUsed.setText("");
		attacksWon.setText("");
		totalStars1Count.setText("");
		totalStars2Count.setText("");
		totalStars3Count.setText("");
		heroicAttackPlayer.setText("");
		heroicDefensePlayer.setText("");
		
		attacksRemaining.setText("");
		attacksLost.setText("");
		finalStars.setText("");
		newStarPeerAttack.setText("");
	}
	public void 	reset(int	scope){
		clearInputs();
		this.scope=scope;
	}
		
	private void	updateLockedField(){
		attacksRemaining.setEnabled(!autoFillEnabled);
		attacksLost.setEnabled(!autoFillEnabled);
		finalStars.setEnabled(!autoFillEnabled);
		newStarPeerAttack.setEnabled(!autoFillEnabled);
	}
	/// need : scope ,attack used
	private void	autoFillAttacksRemaining(){

		int used=FieldVerifier.tryParseInt(attacksUsed.getValue(), BAD_NUMBER);
		if(used != BAD_NUMBER){
			int val= WarResultBuilder.calcMaxAttacks(scope)-used;
			attacksRemaining.setValue(Integer.toString(val),true);
		}
	}
	
	/// need : attack used,attack won
	private void	autoFillAttacksLost(){
		int used=FieldVerifier.tryParseInt(attacksUsed.getValue(), BAD_NUMBER);
		int won= FieldVerifier.tryParseInt(attacksWon.getValue(), BAD_NUMBER);
		if(used != BAD_NUMBER && won != BAD_NUMBER){
			attacksLost.setValue(Integer.toString(used-won),true);
		}
	}
	/// need : attack star1,star2,star2
	private void	autoFillFinalStar(){
		int s1=FieldVerifier.tryParseInt(totalStars1Count.getValue(), BAD_NUMBER);
		int s2= FieldVerifier.tryParseInt(totalStars2Count.getValue(), BAD_NUMBER);
		int s3= FieldVerifier.tryParseInt(totalStars3Count.getValue(), BAD_NUMBER);
		if(s1 != BAD_NUMBER && s2 != BAD_NUMBER  && s3 != BAD_NUMBER){
			int total=WarResultBuilder.calcFinalStars(s1, s2, s3);
			finalStars.setValue(Integer.toString(total),true);
		}
	}
	/// need final stars,attack used
	private void	autoFillNewStarPeerAttack(){
		int used=FieldVerifier.tryParseInt(attacksUsed.getValue(), BAD_NUMBER);
		int total= FieldVerifier.tryParseInt(finalStars.getValue(), BAD_NUMBER);
		if(used != BAD_NUMBER && total != BAD_NUMBER ){
			float nspa=WarResultBuilder.calcNspaPercent(used, total);
			newStarPeerAttack.setValue(Float.toString(nspa),true);
		}
	}
	private void	setAutoFillHander(){

		// attacksUsed attacksWon totalStars1Count totalStars2Count totalStars3Count
		attacksUsed.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	if(autoFillEnabled) {
		    		autoFillAttacksRemaining();
		    		autoFillAttacksLost();
		    		autoFillNewStarPeerAttack();
		    	}
		    }
		});
		attacksWon.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	if(autoFillEnabled){
		    		autoFillAttacksLost();
		    	}
		    }
		});
		////
		totalStars1Count.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	if(autoFillEnabled){
		    		autoFillFinalStar();
		    	}
		    }
		});
		totalStars2Count.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	if(autoFillEnabled) {
		    		autoFillFinalStar();
		    	}
		    }
		});
		totalStars3Count.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	if(autoFillEnabled) {
		    		autoFillFinalStar();
		    	}
		    }
		});
		finalStars.addValueChangeHandler(new ValueChangeHandler<String>() {
		    @Override
		    public void onValueChange(ValueChangeEvent<String> event) {
		    	if(autoFillEnabled) {
		    		autoFillNewStarPeerAttack();
		    	}
		    }
		});
	}

	public void setEbableAutoFill(boolean enabled){
		autoFillEnabled=enabled;
		updateLockedField();
	}

	public TextBox getAttacksUsed() {
		return attacksUsed;
	}


	public TextBox getAttacksWon() {
		return attacksWon;
	}


	public TextBox getTotalStars1Count() {
		return totalStars1Count;
	}


	public TextBox getTotalStars2Count() {
		return totalStars2Count;
	}


	public TextBox getTotalStars3Count() {
		return totalStars3Count;
	}


	public TextBox getHeroicAttackPlayer() {
		return heroicAttackPlayer;
	}


	public TextBox getHeroicDefensePlayer() {
		return heroicDefensePlayer;
	}


	public TextBox getAttacksRemaining() {
		return attacksRemaining;
	}


	public TextBox getAttacksLost() {
		return attacksLost;
	}


	public TextBox getFinalStars() {
		return finalStars;
	}


	public TextBox getNewStarPeerAttack() {
		return newStarPeerAttack;
	}
	
	
}