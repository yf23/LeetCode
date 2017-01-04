/*
    Palindrome Permutation II

    Given a string s, return all the palindromic permutations (without duplicates) of it.
    Return an empty list if no palindromic permutation could be form.

    For example:

    Given s = "aabb", return ["abba", "baab"].

    Given s = "abc", return [].

    Hint:
    If a palindromic permutation exists, we just need to generate the first half of the string.
    To generate all distinct permutations of a (half of) string,
    use a similar approach from: Permutations II or Next Permutation.

    Yu Fu, 12/14/2016
 */

public class Solution {
    public List<String> generatePalindromes(String s) {
        // Store characters into HashMap. 
        // Also count characters with odd counts.
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        int oddCount = 0;
        for (int i = 0; i < s.length(); i++) {
            hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i), 0) + 1);
            oddCount += (hm.get(s.charAt(i)) % 2 == 0) ? -1 : 1;
        }

        Set<String> halves = new HashSet<String>();
        List<String> result = new ArrayList<String>();
        if (oddCount > 1) return result;    // If more than one odd count, return empty list.
        
        // Remove single odd count character from HashMap.
        char oddChar = ' ';
        if (oddCount == 1) {
            for (Character c : hm.keySet()) {
                if (hm.get(c) % 2 == 1) {
                    oddChar = c;
                }
            }
            if (hm.get(oddChar) == 1) hm.remove(oddChar);
            else hm.put(oddChar, hm.get(oddChar) - 1);
        }
        
        // Generate permutations of first half of palindromes
        generate(halves, "", hm, s.length() / 2);
        
        // Add second half
        for (String word : halves) {
            if (oddCount == 1) result.add(word + oddChar + new StringBuilder(word).reverse().toString());
            else result.add(word + new StringBuilder(word).reverse().toString());
        }
        return result;
        
    }
    
    // Generate all the permutation of first half part of palindroms.
    public void generate(Set<String> result, String cur, Map<Character, Integer> dict, int target) {
        if (cur.length() == target) {
            result.add(cur);
        } else {
            for (Character c : dict.keySet()) {
                if (dict.get(c) != 0) {
                    dict.put(c, dict.get(c) - 2);
                    generate(result, cur + c, dict, target);
                    dict.put(c, dict.get(c) + 2);
                }
            }
        }
    }
}