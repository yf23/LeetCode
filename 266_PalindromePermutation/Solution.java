/*
    Palindrome Permutation

    Given a string, determine if a permutation of the string could form a palindrome.

    For example,
    "code" -> False, "aab" -> True, "carerac" -> True.
    
    Solution:
    Calculate the frequency of each characters in given String.
    If there are more than 1 character with odd frequency, 
    the permutations of given String cannot be palindrome.

    Yu Fu, 09/29/2016
 */

public class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] ht = new int[255];
        // Maintain a Hashtable of each character and the frequency of the character in String.
        for (int i = 0; i < s.length(); i++) {
            ht[s.charAt(i)]++;
        }
        // Count number of characters with odd frequency.
        int oddCount = 0;
        for (int n : ht) {
            if (n % 2 == 1) oddCount++;
        }
        // If more than 1, cannot be palindrome.
        return oddCount <= 1;
    }
} 