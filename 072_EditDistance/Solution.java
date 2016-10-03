/*
    Edit Distance

    Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

    You have the following 3 operations permitted on a word:

    a) Insert a character
    b) Delete a character
    c) Replace a character

    Solution:
    
    Add a fake (assuming equal) head to both Strings.

    dp[i][j] represents the distance from word1[0:i] to word2[0:j]

    if word1.charAt(i) != word2.charAt(j)
    min of
        Distance if insert:  dp[i-1][j] + 1
        Distance if delete:  dp[i][j-1] + 1
        Distance if replace: dp[i-1][j-1] + 1

    if word1.charAt(i) == word2.charAt(j)
        Distance remians the same as dp[i-1][j-1]

    Yu Fu, 09/27/2016
 */

public class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        if (word2.length() == 0 || word2.length() == 0) return word1.length() + word2.length();
        
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        for (int i = 0; i <= word1.length(); i++) dp[i][0] = i;
        for (int i = 0; i <= word2.length(); i++) dp[0][i] = i;
        
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                } else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }  
        return dp[word1.length()][word2.length()];       
    }
}