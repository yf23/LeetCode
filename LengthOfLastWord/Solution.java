/*
    Length of Last Word

    Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

    If the last word does not exist, return 0.

    Note: A word is defined as a character sequence consists of non-space characters only.

    For example, 
    Given s = "Hello World",
    return 5.

    There are cases such as "a " and "   a ".
    Not using Java trim() and split() methods because they are boring.

    Yu Fu, Jan 3 2015
 */

public class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0;
        boolean spaceFound = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                spaceFound = true;
            } else {
                if (spaceFound) count = 1;
                else count++;
                spaceFound = false;
            }
        }
        return count;
    }
}