/*
    Longest Palindromic Substring

    Given a string S, find the longest palindromic substring in S.
    You may assume that the maximum length of S is 1000,
    and there exists one unique longest palindromic substring.
    
    Solution:
    For each char in String,
        expand palindrom from itself
        expand palindrom from itself and the next char
    Keep track of the longest substring by recording start index and length.

    Yu Fu, 09/29/2016
 */

public class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 0;       // Length of longest palindrome.
        int startIdx = 0;     // Starting index of longest palindrome.
        
        // For each character, expand from itself.
        for (int i = 0; i < s.length(); i++) {
            int len = expandPalindrome(s, i, i);
            if (len > maxLen) {
                maxLen = len;
                startIdx = i - len / 2;
            }
        }
        
        // For each character, expand from itself and its next char.
        for (int i = 0; i < s.length() - 1; i++) {
            int len = expandPalindrome(s, i, i + 1);
            if (len > maxLen) {
                maxLen = len;
                startIdx = i + 1 - len / 2;
            }     
        }
        
        return s.substring(startIdx, startIdx + maxLen);
    }
    
    // Expand palindrom to both sides with starting index i and j.
    // If i == j, expand from one char;
    // If i != j, expand from two chars.
    // Return the length of palindrome.
    private int expandPalindrome(String s, int i, int j) {
        int len = 0;
        // If i, j are not out of index bound and s[i] == s[j]
        // The palindrome can expand and add 2 to length.
        // Special Case: when i == j only add 1 to length.
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            len += (i-- == j++) ? 1 : 2;
        }
        return len;
    }
}