/*
    Generalized Abbreviation

    Write a function to generate the generalized abbreviations of a word.

    Example:
    Given word = "word", return the following list (order does not matter):
    ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d",
     "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

    Solution:
    For each character, it can be abbreviated or kept in result string.
    If abbreviated, increment a count of how many consecutive characters are abbreviated,
    and move to next character.
    If kept, add previous abbreviated count followed by current character in current string.
    At end, add count if it is not 0.

    Yu Fu, 12/03/2016
 */

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<String>();
        helper(word, "", 0, 0, result);
        return result;
    }
    
    public void helper(String word, String cur, int pos, int count, List<String> result) {
        // At end, add count to result string if present.
        if (pos == word.length()) {
            if (count > 0) cur += count;
            result.add(cur);
        } else {
            // Current char abbreviated, increment count.
            helper(word, cur, pos + 1, count + 1, result);
            // Current char kept, add count followed by churrent char to result string.
            helper(word, cur + ((count > 0) ? count : "") + word.charAt(pos), pos + 1, 0, result);
        }
    }
}