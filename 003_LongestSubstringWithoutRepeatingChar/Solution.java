/*
    Longest Substring Without Repeating Character
    
    Given a string, find the length of the longest substring without repeating characters.

    Examples:

    Given "abcabcbb", the answer is "abc", which the length is 3.

    Given "bbbbb", the answer is "b", with the length of 1.

    Given "pwwkew", the answer is "wke", with the length of 3.

    Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

    Solution:
    Use a HashMap to maintain characters and frequencies in current substring.
    Expand right end of substring. If added character is in HashMap,
    keep shrinking substring from left side, remove corresponding characters from HashMap.
    Stop until added character is not in HashMap, then add it.
    Keep record max length of substring.

    Yu Fu, 10/03/2016
 */

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        HashSet<Character> hs = new HashSet<Character>();
        int i = 0;
        int j = 0;
        while (i <= j && j < s.length()) {
            char key = s.charAt(j++);
            // If rightmost character is in HashMap,
            // shrink from left side until rightmost character is not in HashMap.
            while (hs.contains(key)) {
                hs.remove(s.charAt(i++));
            }
            hs.add(key);
            // Update max length.
            max = Math.max(j - i, max);
        }
        return max;
    }
}