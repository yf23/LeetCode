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