/*
    Minimum Unique Word Abbreviation

    A string such as "word" contains the following abbreviations:

    ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2",
     "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
    Given a target string and a set of strings in a dictionary,
    find an abbreviation of this target string with the smallest possible length
    such that it does not conflict with abbreviations of the strings in the dictionary.

    Each number or letter in the abbreviation is considered length = 1.
    For example, the abbreviation "a32bc" has length = 4.

    Note:
    In the case of multiple answers as shown in the second example below,
    you may return any one of them.
    Assume length of target string = m, and dictionary size = n. 
    You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
    
    Examples:
    "apple", ["blade"] -> "a4"
    (because "5" or "4e" conflicts with "blade")

    "apple", ["plain", "amber", "blade"] -> "1p3"
    (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").

    Solution:
    Put dictionary words in a trie tree.
    For each abbreviation of given word, search trie tree to see if the abbreviation can represent a word.
    For numbers in abbreviation,
    do a wildcard search such that all children of current trie node will be searched.

    Yu Fu, 12/04/2016
 */

public class Solution {
    
    class TrieNode {
        boolean isWord;
        TrieNode[] children;
        
        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }
    
    class Trie {
        TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                }
                cur = cur.children[idx];
            }
            cur.isWord = true;
        }
        
        public boolean search(String abbr) {
            return searchHelper(abbr, 0, root);
        }
        
        private boolean searchHelper(String abbr, int wildSearch, TrieNode cur) {
            if (cur == null) return false;     // current node is null, stop dfs
            // Search ends, see if there is a word exists.
            if (abbr.length() == 0 && wildSearch == 0) return cur.isWord;
            // There exists abbrevation, do wildcard search (all children)
            if (wildSearch > 0) {
                boolean result = false;
                for (int i = 0; i < 26; i++) {
                    result = result || searchHelper(abbr, wildSearch - 1, cur.children[i]);
                }
                return result;
            }
            // Test if the abbrevation starts with digit.
            // If start with digit, split it from rest parts and start wildcard search.
            int num = 0;
            int i = 0;
            while (i < abbr.length() && abbr.charAt(i) >= '0' && abbr.charAt(i) <= '9') {
                num = num * 10 + (abbr.charAt(i++) - '0');
            }
            
            // Start with digit
            if (num > 0) return searchHelper(abbr.substring(i), num, cur);
            // Not start with digit
            else return searchHelper(abbr.substring(1), 0, cur.children[abbr.charAt(0) - 'a']);
        }
    }
    
    public String minAbbreviation(String target, String[] dictionary) {
        // Empty dictionary, always return the length of target    
        if (dictionary.length == 0) {
            return target.length() + "";    
        }
        
        TreeMap<Integer, List<String>> abbrs = new TreeMap<Integer, List<String>>();
        generateAbbrs(target, "", 0, 0, 0, abbrs);
        // Insert all dictionary words into trie.
        Trie trie = new Trie();
        for (String s : dictionary) {
            trie.insert(s);
        }
        // Search all abbreviations starting from short to long.
        for (Integer len : abbrs.keySet()) {
            for (String abbr : abbrs.get(len)) {
                if (!trie.search(abbr)) return abbr;
            }
        }
        return "wtf";     // Should never be here
    }
    
    // Generate abbreviations of given word, and put them to a TreeMap with length of abbreviation as key.
    // Idea see #320.
    private void generateAbbrs(String word, String cur, int pos, int count,
                               int len, TreeMap<Integer, List<String>> result) {
        if (pos == word.length()) {
            if (count > 0) {
                cur += count;
                len++;
            }
            if (!result.containsKey(len)) result.put(len, new ArrayList<String>());
            result.get(len).add(cur);
        } else {
            generateAbbrs(word, cur + ((count > 0) ? count : "") + word.charAt(pos), pos + 1, 0, len + 2, result);
            generateAbbrs(word, cur, pos + 1, count + 1, len, result);
        }
    }
}