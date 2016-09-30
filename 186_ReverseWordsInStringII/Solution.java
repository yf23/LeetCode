/*
    Reverse Words in a String II

    Given an input string, reverse the string word by word.
    A word is defined as a sequence of non-space characters.

    The input string does not contain leading or trailing spaces,
    and the words are always separated by a single space.

    For example,
    Given s = "the sky is blue",
    return "blue is sky the".

    Could you do it in-place without allocating extra space?

    Yu Fu, 09/29/2016
 */

public class Solution {
    public void reverseWords(char[] s) {
        // Reverse the whole array.
        reverse(s, 0, s.length);
        
        // Reverse each word.
        // When there is a space, reverse from word start to space.
        // Record next word start as next character.
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i);
                start = i + 1;
            }
        }
        // Reverse last word.
        reverse(s, start, s.length);
    }
    
    // Reverse from index start (inclusive) to index end (exclusive).
    public void reverse(char[] s, int start, int end) {
        int i = start;
        int j = end - 1;
        while (i < j) {
            char temp = s[i];
            s[i++] = s[j];
            s[j--] = temp;
        }
    }
}