/*
    Minimum Path Sum

    Given a m x n grid filled with non-negative numbers, 
    find a path from top left to bottom right which minimizes the sum of all numbers along its path.

    Note: You can only move either down or right at any point in time.

    Solution:
    Let dp[x][y] means minimum path sum from (x, y) to FINISH.
    From (x, y), we can move right to (x + 1, y) or move down to (x, y + 1).
    In order to get minimum sum, we move to the direction with lower sum.
    
    Therefore, dp[x][y] = grid[x][y] + min(dp[x+1][y], dp[x][y+1])

    When initialize, the last row or last column can only move in one direction.
    Simply calculate the sum by adding numbers.

    Finally we return dp[0][0] as result.
 */

public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // Initialize last row and last column by adding numbers in grid.
        dp[m-1][n-1] = grid[m-1][n-1];
        for (int i = m - 2; i >= 0; i--) dp[i][n-1] = grid[i][n-1] + dp[i+1][n-1];
        for (int i = n - 2; i >= 0; i--) dp[m-1][i] = grid[m-1][i] + dp[m-1][i+1];
        
        // dp[i][j] = grid[i][j] + min(up, right)
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
            }
        }
        return dp[0][0];
    }
}