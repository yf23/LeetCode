/*
    Maximal Square

    Given a 2D binary matrix filled with 0's and 1's,
    find the largest square containing only 1's and return its area.

    For example, given the following matrix:

    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0
    
    Return 4 

    Solution:
    dp[i][j] = 0                                               if grid[i][j] == 0
             = min(dp[i+1][j+1], dp[i+1][j], dp[i][j+1]) + 1   otherwise

    Yu Fu, 12/04/2016
 */

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        
        // Fill with possible 1's        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                max = Math.max(dp[i][j], max);
            }
        }
        
        // Go through kth row / col from the last row / col
        for (int k = 2; k <= Math.min(m, n); k++) {
            for (int i = m - k; i >= 0; i--) {
                if (matrix[i][n-k] == '1') {
                    dp[i][n-k] = Math.min(dp[i+1][n-k+1], Math.min(dp[i+1][n-k], dp[i][n-k+1])) + 1;
                    max = Math.max(dp[i][n-k], max);
                }
            }
            
            for (int j = n - k; j >= 0; j--) {
                if (matrix[m-k][j] == '1') {
                    dp[m-k][j] = Math.min(dp[m-k+1][j+1], Math.min(dp[m-k+1][j], dp[m-k][j+1])) + 1;
                    max = Math.max(dp[m-k][j], max);
                }
            }
        }

        return max * max;
    }
}