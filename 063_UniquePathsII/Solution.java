/*
    Unique Paths II

    Follow up for "Unique Paths":

    Now consider if some obstacles are added to the grids. How many unique paths would there be?

    An obstacle and empty space is marked as 1 and 0 respectively in the grid.

    For example,
    There is one obstacle in the middle of a 3x3 grid as illustrated below.

    [[0,0,0],
     [0,1,0],
     [0,0,0]]

    The total number of unique paths is 2.

    Solution:
    Let dp[x][y] means number of unique paths from (x, y) to FINISH.
    If (x, y) is obstacle, we cannot move to FINISH from there.
    Otherwise, robot can move right to (x + 1, y) or move down to (x, y + 1).
    
    Therefore, dp[x][y] = 0                         , (x, y) is obstacle
                          dp[x+1][y] + dp[x][y+1]   , (x, y) is not obstacle
    When initialize, if the block is in the same row or the same column as FINISH, 
    if there is no blocking obstacle on its right / lower, it has 1 path to FINISH.
    if there is blocking obstacle, it has 0 path to FINISH.  
    Finally we return dp[0][0] as result.
 */

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        
        // Initialize. Block on last row / column has 1 path to FINISH if no obstacles on the way.
        // Any block above obstacles on last column or any block at left side of obstacles on last row has 0 path to FINISH.
        for (int i = m - 1; i >= 0; i--) {
            if (obstacleGrid[i][n-1] == 1) break;
            dp[i][n-1] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (obstacleGrid[m-1][i] == 1) break;
            dp[m-1][i] = 1;
        }
        
        // dp[i][j] = 0 if it's obstacle
        // Otherwise, dp[i][j] = dp[i+1][j] + dp[i][j+1]
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = (obstacleGrid[i][j] == 1) ? 0 : dp[i+1][j] + dp[i][j+1];
            }
        }
        
        return dp[0][0];
    }
}