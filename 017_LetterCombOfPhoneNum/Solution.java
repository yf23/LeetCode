/*  
    Letter Combinations of a Phone Number
    
    Given a digit string, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below.
    1         2 abc     3 def
    4 ghi     5 jkl     6 mno
    7 pqrs    8 tuv     9 wxyz

    Input:Digit string "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    
    Yu Fu, 09/27/2016
 */

public class Solution {
    public List<String> letterCombinations(String digits) {
        // Initalize mapping from digit to letters.
        HashMap<Integer, String> mapping = new HashMap<Integer, String>();
        mapping.put(2, "abc");
        mapping.put(3, "def");
        mapping.put(4, "ghi");
        mapping.put(5, "jkl");
        mapping.put(6, "mno");
        mapping.put(7, "pqrs");
        mapping.put(8, "tuv");
        mapping.put(9, "wxyz");
        
        List<String> init = new ArrayList<String>();
        if (digits.equals("")) return init;
        
        init.add("");     // Add an empty String to start recursion.
        return helper(digits, init, mapping);
    }
    
    public List<String> helper(String digits, List<String> prevs, HashMap<Integer, String> mapping) {
        if (digits.length() == 0) return prevs;    // End of recursion if there is no more digits.
        
        List<String> results = new ArrayList<String>();
        int digit = Integer.parseInt(digits.substring(0, 1));   // Get the first digit
        String letters = mapping.get(digit);                    // Get the possible letters of the first digit
        
        // For each word String in previous list, add possible letters to the end.
        for (String prev : prevs) {
            for (int i = 0; i < letters.length(); i++) {
                results.add(prev + letters.charAt(i));
            }
        }
        
        // Recursion case with the rest digits.
        return helper(digits.substring(1, digits.length()), results, mapping);
        
    }
}