/*
    Dungeon Game

    The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
    The dungeon consists of M x N rooms laid out in a 2D grid.
    Our valiant knight (K) was initially positioned in the top-left room and must fight his way through
    the dungeon to rescue the princess.

    The knight has an initial health point represented by a positive integer.
    If at any point his health point drops to 0 or below, he dies immediately.

    Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
    other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

    In order to reach the princess as quickly as possible,
    the knight decides to move only rightward or downward in each step.

    Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

    For example, given the dungeon below, the initial health of the knight must be at least 7,
    if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
    
    -2   -3    3
    -5   -10   1
     10   30  -5
    
    Notes:
    The knight's health has no upper bound.
    Any room can contain threats or power-ups,
    even the first room the knight enters and the bottom-right room where the princess is imprisoned.

    Solution:
    For the bottom-right grid, the minimum health after need to be exactly 1 to satisfy the minimum initial health.
    Therefore we have dp(m-1, n-1) + dungeon(m-1, n-1) = 1  ==>  dp(m-1, n-1) = 1 - dungeon(m-1, n-1)
    Also, the initial health cannot be less than 1. So we have dp(m-1, n-1) = max(1, 1 - dungeon(m-1, n-1))

    For other grids, the knight can either go right or go down.
    In order to satisfy the minimum initial health,
    the knight will choose to go to the grid which requires less initial health.
    Therefore we have dp(i, j) + dungeon(i, j) = min(dp(i+1, j), dp(i, j+1)).
    Also, the initial health cannot be less than 1. So we have
    dp(i, j) = max(1, min(dp(i+1, j), dp(i, j+1)) - dungeon(i, j)).

    For grids in last row/col, only one direction will be considered.

    Return the minimum initial health if starts from top-left, which is dp(0, 0).
 */
    
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];

        // For last block, initialHealth + dungeon >= 1, therefore initialHealth = max(1, 1 - dungeon)
        dp[m-1][n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);

        // For last row/col, initialHealth[k] + dungeon[k] = initalHealth[k + 1]
        // Therefore, initialHealth[k] = max(1, initialHealth[k + 1] - dungeon[k]);
        for (int i = m - 2; i >= 0; i--) dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);
        for (int i = n - 2; i >= 0; i--) dp[m-1][i] = Math.max(1, dp[m-1][i+1] - dungeon[m-1][i]);
        
        // For other blocks, initialHealth[x, y] + dungeon[x, y] = initialHealth[x+1, y] or initialHealth[x, y+1]
        // Since we want min initial health,
        // initialHealth[x, y] = max(1, min(initialHealth[x+1, y], initialHealth[x, y+1]) - dungeon[x, y])
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}