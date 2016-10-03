/*
    Reverse Words in a String

    Given an input string, reverse the string word by word.

    For example,
    Given s = "the sky is blue",
    return "blue is sky the".

    click to show clarification.

    Clarification:
    What constitutes a word?
    A sequence of non-space characters constitutes a word.
    Could the input string contain leading or trailing spaces?
    Yes. However, your reversed string should not contain leading or trailing spaces.
    How about multiple spaces between two words?
    Reduce them to a single space in the reversed string.

    Yu Fu, 09/29/2016
 */

public class Solution {
    public String reverseWords(String s) {
        ArrayList<String> l = new ArrayList<String>();
        boolean isWord = false;     // Is currently reading a word?
        StringBuffer sb = null;
        for (int i = 0; i < s.length(); i++) {
            // If current char is not space
            if (s.charAt(i) != ' ') {
                // If not currently reading a word, 
                // start a new StringBuffer and update the status to is reading a word.
                if (!isWord) {
                    sb = new StringBuffer();
                    isWord = true;
                }
                // Append current character into word StringBuffer.
                sb.append(s.charAt(i));
                
            // If current char is space,
            } else {
                // If current reading a word,
                // end reading status and put word into list.
                if (isWord) {
                    isWord = false;
                    l.add(0, sb.toString());
                }
            }
        }
        // Put last word in list if still reading.
        if (isWord) l.add(0, sb.toString());
        
        // If no words, return empty String.
        if (l.size() == 0) return "";

        // Join words with one space.
        StringBuffer result = new StringBuffer();
        for (String word : l) {
            result.append(word);
            result.append(' ');
        }
        // Remove end space.
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}