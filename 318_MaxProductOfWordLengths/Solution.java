/*
    Maximum Product of Word Lengths

    Given a string array words,
    find the maximum value of length(word[i]) * length(word[j])
    where the two words do not share common letters.
    You may assume that each word will contain only lower case letters.
    If no such two words exist, return 0.

    Example 1:
    Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
    Return 16
    The two words can be "abcw", "xtfn".

    Example 2:
    Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
    Return 4
    The two words can be "ab", "cd".

    Example 3:
    Given ["a", "aa", "aaa", "aaaa"]
    Return 0
    No such pair of words.

    Solution:
    Use the last 26 bits of interger to store the presence of letters in a string.
    0000 0000 0000 0000 0000 0000 0000 0000
    ---- --zy xwvu tsrq ponm lkji hgfe dcba

    For example:
    "abc" => 0000 0000 0000 0000 0000 0000 0000 0111

    Then, to test if two string has letters in common,
    let integer m and n represent two strings.
    If m & n is 0, there are no common letters.

    Yu Fu, 10/18/2016
 */

public class Solution {
    public int maxProduct(String[] words) {
        int[] wordsInBits = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                // Update position of corresponding letter to 1
                wordsInBits[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // If two words do not have common letters,
                // update the product of their length.
                if ((wordsInBits[i] & wordsInBits[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
