/*
    Perfect Squares

    Given a positive integer n,
    find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

    For example,
    given n = 12, return 3 because 12 = 4 + 4 + 4;
    given n = 13, return 2 because 13 = 4 + 9.
    
    Solution:
    Let dp[i] be number of perfect square numbers which sum to i.
    dp[0] = 0
    dp[1] = 1
    dp[2] = dp[2 - 1 * 1] + 1
    ...
    dp[5] = Min(dp[5 - 1 * 1] + 1, dp[5 - 2 * 2] + 1)
    ...
    dp[10] = Min(dp[10 - 1 * 1] + 1, dp[10 - 2 * 2] + 1, dp[10 - 3 * 3] + 1)
    ...
    dp[k] = Min(dp[k - j * j] + 1, j starts from 1, ends when k - j * j <= 0)
    
    Yu Fu, 10/01/2016
 */

public class Solution {
    public int numSquares(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; i - j * j >= 0; j++) {
                min = Math.min(min, dp[i - j * j] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
}