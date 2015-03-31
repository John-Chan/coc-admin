package org.coc.tools.shared.model;


public class CounterWarResult {
	private	int	countWin=0;	
	private	int	countLose=0;
	private	int	countDraw=0;
	private	String	percentWin="0";
	private	String	percentLose="0";
	private	String	percentDraw="0";
	private	Clan	clan=null;
	
	public CounterWarResult(int	countWin,int	countLose,int	countDraw){
		this.countWin=countWin;
		this.countLose=countLose;
		this.countDraw=countDraw;
	}
	public CounterWarResult(){
		//
	}
	public int getCountWin() {
		return countWin;
	}

	public int getCountLose() {
		return countLose;
	}

	public int getCountDraw() {
		return countDraw;
	}

	public String getPercentWin() {
		return percentWin;
	}

	public String getPercentLose() {
		return percentLose;
	}

	public String getPercentDraw() {
		return percentDraw;
	}

	public Clan getClan() {
		return clan;
	}

	public void setCountWin(int countWin) {
		this.countWin = countWin;
	}

	public void setCountLose(int countLose) {
		this.countLose = countLose;
	}

	public void setCountDraw(int countDraw) {
		this.countDraw = countDraw;
	}

	public void setPercentWin(String percentWin) {
		this.percentWin = percentWin;
	}

	public void setPercentLose(String percentLose) {
		this.percentLose = percentLose;
	}

	public void setPercentDraw(String percentDraw) {
		this.percentDraw = percentDraw;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}
	
}
