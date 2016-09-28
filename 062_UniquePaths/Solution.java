/*
    Unique Paths

    A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

    The robot can only move either down or right at any point in time.
    The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

    How many possible unique paths are there?

    Solution:
    Let dp[x][y] means number of unique paths from (x, y) to FINISH.
    From (x, y), robot can move right to (x + 1, y) or move down to (x, y + 1).
    Therefore, dp[x][y] = dp[x+1][y] + dp[x][y+1].
    When initialize, if the block is in the same row or the same column as FINISH, then it only has 1 path to FINISH.
    Finally we return dp[0][0] as result.
 */

public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // Initialize the last row and last column to be 1.
        // Which means unique path to FINISH.
        for (int i = 0; i < n; i++) dp[m-1][i] = 1;
        for (int i = 0; i < m; i++) dp[i][n-1] = 1;

        // dp[i][j] = dp[i+1][j] + dp[i][j+1]
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        return dp[0][0];
    }
}
