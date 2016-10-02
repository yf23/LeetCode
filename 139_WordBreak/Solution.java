/*
    Word Break

    Given a string s and a dictionary of words dict,
    determine if s can be segmented into a space-separated sequence of one or more dictionary words.

    For example, given
    s = "leetcode",
    dict = ["leet", "code"].

    Return true because "leetcode" can be segmented as "leet code".

    Solution:
    Let dp[i] be s.substring(0, i) can be segmented into dict words.
    Set dp[0] to be true.

    If s.substring(0, j) can be segmented into dict words (dp[j] is true),
    and s.substring(j, i) is a word in dict,
    dp[i] can be segmented into dict words.

    Return dp[s.length()] as result.

    Yu Fu, 10/02/2016
 */

public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // A pesudo case to handle first word.
        // For a substring from 0 to i
        for (int i = 1; i <= s.length(); i++) {
            // For any j in 0 < j < i, 
            // if substring from 0 to j can be broken into dict words,
            // and substring from j to i is in dict,
            // substring from 0 to i can be broken into dict words.
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true; break;
                }
            }
        }
        return dp[s.length()];
    }
}