/*
    Longest Substring with At Most 2 Distinct Characters

    Given a string, find the length of the longest substring T
    that contains at most 2 distinct characters.

    For example, Given s = “eceba”

    T is "ece" which its length is 3.

    Solution:
    Use a HashMap to maintain characters and frequencies in current substring.
    Expand right end of substring. If added character makes more than 2 entries in HashMap,
    keep shrinking substring from left side, remove corresponding characters from HashMap.
    Stop until k entries in HashMap. Keep record max length of substring.

    Yu Fu, 10/03/2016
 */

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s, int k) {
        int maxLength = 0;
        HashMap<Character, Integer> m = new HashMap<Character, Integer>();
        int i = 0;
        int j = 0;
        while (i <= j && j < s.length()) {
            char key = s.charAt(j++);
            // Add next character into HashMap
            m.put(key, m.containsKey(key) ? m.get(key) + 1 : 1);
            // Shrink from left until only k distinct characters (entries) in HashMap
            while (m.size() > 2) {
                char kk = s.charAt(i++);
                int vv = m.get(kk);
                if (vv > 1) m.put(kk, vv - 1);
                else m.remove(kk);
            }
            // Update max length of substring.
            maxLength = Math.max(j - i, maxLength);
        }
        return maxLength;
    }
}