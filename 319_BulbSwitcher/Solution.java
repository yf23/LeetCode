/*
	Bulb Switcher

	There are n bulbs that are initially off. You first turn on all the bulbs.
	Then, you turn off every second bulb.
	On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
	For the ith round, you toggle every i bulb.
	For the nth round, you only toggle the last bulb.
	Find how many bulbs are on after n rounds.

	Example:

	Given n = 3. 

	At first, the three bulbs are [off, off, off].
	After first round, the three bulbs are [on, on, on].
	After second round, the three bulbs are [on, off, on].
	After third round, the three bulbs are [on, off, off]. 

	So you should return 1, because there is only one bulb is on.

	Solution:
	Bulb m will be toogled at round k if k is factor of m.
	Only bulb is square of some integer has odd number of factors, so they are toogled odd numbers of times after n rounds.
	Therefore, they are on after n rounds.

    Yu Fu, 09/27/2016
 */

/*

 */
public class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}