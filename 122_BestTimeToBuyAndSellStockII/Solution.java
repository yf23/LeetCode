/*
    Best Time to Buy and Sell Stock II

    Say you have an array for which the ith element is the price of a given stock on day i.

    Design an algorithm to find the maximum profit.
    You may complete as many transactions as you like.
    (ie, buy one and sell one share of the stock multiple times.)
    However, you may not engage in multiple transactions at the same time.
    (ie, you must sell the stock before you buy again.)

    Solution:
    Since one can buy and sell at any time,
    he can capture all the profits once there is any increase.
    AND WHY IS THIS ONE MEDIUM?

    Yu Fu, 09/30/2016
 */

public class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0;
        // Once there is increase on price, count it as profit.
        for (int i = 0; i < prices.length - 1; i++) {
            sum += Math.max(0, prices[i + 1] - prices[i]);
        }
        return sum;
    }
}