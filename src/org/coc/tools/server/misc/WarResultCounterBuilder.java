package org.coc.tools.server.misc;

import org.coc.tools.shared.misc.MathHelper;
import org.coc.tools.shared.model.CounterWarResult;

public class WarResultCounterBuilder {
	
	/**
	 * 
	 * @param countWin
	 * @param countLose
	 * @param countDraw
	 * @note CounterWarResult.clan is not inited
	 */
	public static CounterWarResult	makeCounterWarResult(int	countWin,int	countLose,int	countDraw){
		CounterWarResult cw=new CounterWarResult(countWin,countLose,countDraw);
		int total=countWin+countLose+countDraw;
		cw.setPercentWin(MathHelper.getPercentS(countWin, total));
		cw.setPercentLose(MathHelper.getPercentS(countLose, total));
		cw.setPercentDraw(MathHelper.getPercentS(countDraw, total));
		
		return cw;
	}
}
