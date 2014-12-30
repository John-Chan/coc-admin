package org.coc.tools.shared;

import org.coc.tools.shared.model.WarResult;

public class WarResultBuilder {

	/**
	 * values are not inited after this call : # averageDestruction #
	 * averageAttackDurationSecond # heroicAttackPlayer # heroicDefensePlayer
	 * 
	 * @param playerCount
	 * @param attacksUsed
	 * @param attacksWon
	 * @param star1
	 * @param star2
	 * @param star3
	 * @return WarResult
	 * @throws Exception
	 */
	public static final WarResult makeSimpleWarResult(int playerCount,
			int attacksUsed, int attacksWon, int star1, int star2, int star3)
			throws Exception {
		WarResult one = new WarResult();
		one.setPlayerCount(playerCount);
		one.setAttacksUsed(attacksUsed);
		one.setAttacksWon(attacksWon);
		one.setTotalStars1Count(star1);
		one.setTotalStars2Count(star2);
		one.setTotalStars3Count(star3);

		one.setMaxAccackCount(playerCount
				* CocConstant.WarCounters.ATTACK_COUNT_EACH_PLAYER);
		one.setAttacksRemaining(one.getMaxAccackCount() - one.getAttacksUsed());
		one.setAttacksLost(one.getAttacksUsed() - one.getAttacksWon());
		one.setFinalStars(one.getTotalStars1Count()
				* CocConstant.WarCounters.MULTIPLES_STAR1
				+ one.getTotalStars2Count()
				* CocConstant.WarCounters.MULTIPLES_STAR2
				+ one.getTotalStars3Count()
				* CocConstant.WarCounters.MULTIPLES_STAR3);

		float percent = ((float) one.getFinalStars())
				/ ((float) one.getAttacksUsed());
		percent = (float) (Math.round(percent * 100)) / 100;
		one.setNewStarPeerAttack(percent);

		checkCounters(one);
		return one;
	}

	// / check input for playerCount, attacksUsed, attacksWon, star1, star2,
	// star3
	public static void checkCounters(final WarResult one) throws Exception {

		if (one.getPlayerCount() < CocConstant.WarCounters.MIN_PLAYER_COUNT
				|| one.getPlayerCount() > CocConstant.WarCounters.MAX_PLAYER_COUNT
				|| (one.getPlayerCount() % CocConstant.WarCounters.PLAYER_COUNT_MULTIPLES) != 0) {
			throw new Exception("bad player count");
		}

		if (one.getAttacksUsed() < 0
				|| one.getAttacksRemaining() < 0
				|| one.getAttacksUsed() + one.getAttacksRemaining() != one
						.getMaxAccackCount()) {
			throw new Exception(
					"bad attack used count,or bad attack remaining count");
		}

		if (one.getAttacksWon() < 0
				|| one.getAttacksLost() < 0
				|| one.getAttacksWon() + one.getAttacksLost() != one
						.getAttacksUsed()) {
			throw new Exception("bad attack won count,or bad attack lost count");
		}

		if (one.getTotalStars1Count() > one.getAttacksWon()
				|| one.getTotalStars2Count() > one.getAttacksWon()
				|| one.getTotalStars3Count() > one.getAttacksWon()) {
			throw new Exception("bad Stars1 or Stars2 or Stars3 Count");
		}
		int allStar = one.getTotalStars1Count()
				* CocConstant.WarCounters.MULTIPLES_STAR1
				+ one.getTotalStars2Count()
				* CocConstant.WarCounters.MULTIPLES_STAR2
				+ one.getTotalStars3Count()
				* CocConstant.WarCounters.MULTIPLES_STAR3;
		if (allStar != one.getFinalStars()) {
			throw new Exception(
					"bad Stars1 or Stars2 or Stars3 Count,not equal to total stars");
		}
	}
}
