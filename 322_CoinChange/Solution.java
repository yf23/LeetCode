/*
    Coin Change

    You are given coins of different denominations and a total amount of money amount.
    Write a function to compute the fewest number of coins that you need to make up that amount.
    If that amount of money cannot be made up by any combination of the coins, return -1.

    Example 1:
    coins = [1, 2, 5], amount = 11
    return 3 (11 = 5 + 5 + 1)

    Example 2:
    coins = [2], amount = 3
    return -1.

    Note:
    You may assume that you have an infinite number of each kind of coin.

    Solution:
    For coin with value n,
    dp[m] = min(dp[m - n] + 1, ...other possible values)

    Yu Fu, 11/28/2016
 */

public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        // Fill with max int.
        for (int i = 0; i <= amount; i++) dp[i] = Integer.MAX_VALUE;
        // Initialize amounts with value of coins to be 1.
        for (int coin : coins) {
            if (coin <= amount) {
                dp[coin] = 1;
            }
        }
        // If current amount m can be reached by adding one coin with value n,
        // dp[m] = min(dp[m - n] + 1, dp[m])
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i > coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // The last entry is the result.
        // If it is still max int, it cannot be combined by given coins. 
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }
}