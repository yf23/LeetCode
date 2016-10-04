/*
    Regular Expression Matching

    Implement regular expression matching with support for '.' and '*'.

    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.

    The matching should cover the entire input string (not partial).

    The function prototype should be:
    bool isMatch(const char *s, const char *p)

    Some examples:
    isMatch("aa","a") → false
    isMatch("aa","aa") → true
    isMatch("aaa","aa") → false
    isMatch("aa", "a*") → true
    isMatch("aa", ".*") → true
    isMatch("ab", ".*") → true
    isMatch("aab", "c*a*b") → true

    Solution:
    For normal char and '.', match the first char to see if it's a fit.
    For '*k' pattern, it either matches an empty string or become 'k*k' pattern.

    Yu Fu, 09/30/2016
 */

public class Solution {
    public boolean isMatch(String s, String p) {
        // Pattern is empty, only matches when string is empty.
        if (p.length() == 0) return s.length() == 0;
        // Matches star
        if (p.length() > 1 && p.charAt(1) == '*') {
            boolean mapEmptyString = isMatch(s, p.substring(2));              // * matches an empty string
            // *k => k*k, matches one char k
            boolean mapOneChar = s.length() > 0                       // Not match if text string is empty
                              && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') // Match on first char
                              && isMatch(s.substring(1), p);               // Recursive with same parttern
            return mapEmptyString || mapOneChar; 
            
        // Matches one char
        } else {
            return s.length() > 0                              // Not match if text string is empty
                && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))        // match on first char
                && isMatch(s.substring(1), p.substring(1)); // Recursive with rest text and pattern
        }
    }
}