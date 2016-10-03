/*
    Palindrome Partitioning

    Given a string s, partition s such that every substring of the partition is a palindrome.

    Return all possible palindrome partitioning of s.

    For example, given s = "aab",
    Return

    [
      ["aa","b"],
      ["a","a","b"]
    ]

    Solution:
    Use backtracking.

    Yu Fu, 10/02/2016
 */

public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> cur = new ArrayList<String>();
        helper(s, 0, cur, result);    // Start with whole String and empty list.
        return result;
    }
    
    private void helper(String s, int startIdx, List<String> cur, List<List<String>> result) {
        // If reach the end of String, add current list into result.
        if (startIdx == s.length()) result.add(new ArrayList<String>(cur));

        for (int i = startIdx; i < s.length(); i++) {
            // Test if s[0:i] is a palindrome.
            if (isPalindrome(s, startIdx, i)) {
                // Add if s[0:i] is a panlindrome.
                cur.add(s.substring(startIdx, i + 1));
                // Recursively deal with rest part of String.
                helper(s, i + 1, cur, result);
                // Remove added panlindrome from list, search for next panlindrome.
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}