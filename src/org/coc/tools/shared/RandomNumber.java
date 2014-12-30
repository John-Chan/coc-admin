package org.coc.tools.shared;

import java.util.Random;

public class RandomNumber {
	private Random rand = new Random();
	
	public static	RandomNumber	  getInstance()
	{
		return new RandomNumber();
	}
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer [min,max].inclusive
	 * @see java.util.Random#nextInt(int)
	 */
	public  int randInt(int min, int max) {
		// Random.nextInt(n) will got [0,n)
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	public	void	reSeed()
	{
		rand = new Random();
	}
}
