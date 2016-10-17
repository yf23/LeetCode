/*
    Bomb Enemy

    Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
    return the maximum enemies you can kill using one bomb.
    The bomb kills all the enemies in the same row and column from the planted point,
    until it hits the wall since the wall is too strong to be destroyed.
    Note that you can only put the bomb at an empty cell.

    Example:
    For the given grid
    0 E 0 0
    E 0 W E
    0 E 0 0

    return 3. (Placing a bomb at (1,1) kills 3 enemies)

    Solution:
    For each cell, count the number of enemy on its left, right, up and down,
    by running a increment counting pointer from that direction.
    When hitting a wall, the counting becomes zero.
    
    Yu Fu, 10/10/2016
 */

public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            // Fill number of left enemies
            int countLeft = 0;
            for (int j = 0; j < n; j++) {
                dp[i][j] += countLeft;
                if (grid[i][j] == 'E') {
                    countLeft++;
                } else if (grid[i][j] == 'W') {
                    countLeft = 0;
                }
            }
            // Fill number of right enemies
            int countRight = 0;
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] += countRight;
                if (grid[i][j] == 'E') {
                    countRight++;
                } else if (grid[i][j] == 'W') {
                    countRight = 0;
                }
            }
        }
        
        for (int j = 0; j < n; j++) {
            // Fill number of up enemies
            int countUp = 0;
            for (int i = 0; i < m; i++) {
                dp[i][j] += countUp;
                if (grid[i][j] == 'E') {
                    countUp++;
                } else if (grid[i][j] == 'W') {
                    countUp = 0;
                } 
            }
            // Fill number of down enemies
            int countDown = 0;
            for (int i = m - 1; i >= 0; i--) {
                dp[i][j] += countDown;
                if (grid[i][j] == 'E') {
                    countDown++;
                } else if (grid[i][j] == 'W') {
                    countDown = 0;
                }
            }
        }
        
        // Find max on empty cells.
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }
}