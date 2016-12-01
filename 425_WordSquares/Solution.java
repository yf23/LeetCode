/*
    Word Squares

    Given a set of words (without duplicates), find all word squares you can build from them.

    A sequence of words forms a valid word square
    if the kth row and column read the exact same string,
    where 0 ≤ k < max(numRows, numColumns).

    For example, the word sequence ["ball","area","lead","lady"] forms a word square
    because each word reads the same both horizontally and vertically.

    b a l l
    a r e a
    l e a d
    l a d y

    Note:
    There are at least 1 and at most 1000 words.
    All words will have the exact same length.
    Word length is at least 1 and at most 5.
    Each word contains only lowercase English alphabet a-z.

    Example 1:
    Input:
    ["area","lead","wall","lady","ball"]

    Output:
    [
      [ "wall",
        "area",
        "lead",
        "lady"
      ],
      [ "ball",
        "area",
        "lead",
        "lady"
      ]
    ]

    Explanation:
    The output consists of two word squares.
    The order of output does not matter (just the order of words in each word square matters).
    
    Example 2:
    Input:
    ["abat","baba","atan","atal"]

    Output:
    [
      [ "baba",
        "abat",
        "baba",
        "atan"
      ],
      [ "baba",
        "abat",
        "baba",
        "atal"
      ]
    ]

    Explanation:
    The output consists of two word squares.
    The order of output does not matter (just the order of words in each word square matters).

    Solution:
    When choosing the next word, make sure the word has same prefix as corresponding column of previous words.
    For example:
    
    b a l l

    The next word must has prefix a-

    b a l l
    a r e a

    The next word must has prefix le-

    b a l l
    a r e a
    l e a d

    The next word must has prefix lad-

    b a l l
    a r e a
    l e a d
    l a d y

    In order to fast search for words with given prefix, implement a trie tree.
    Each node has a word list which contains all the words with the prefix.
    Then use backtracking dfs to search for all word squares.

    Yu Fu, 11/28/2016
 */
 
public class Solution {
    
    // Node for trie.
    class Node {
        List<String> words;
        Node[] children;
        
        public Node() {
            words = new ArrayList<String>();
            children = new Node[26];
        }
        
        public void insertWord(String word) {
            words.add(word);
        }
        
        // Get child node of given letter.
        // If build is true, create the child node if does not exist.
        public Node getChild(char c, boolean build) {
            int index = (int) (c - 'a');
            if (children[index] == null && build) {
                children[index] = new Node();
            }
            return children[index];
        }
        
        // Get word list of node.
        public List<String> getWords() {
            return words;
        }
    }

    class Trie {
        Node root;
        
        public Trie() {
            root = new Node();
        }
        
        public void buildTrie(String[] words) {
            for (String word : words) insert(word);
        }

        public void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                cur.insertWord(word);
                cur = cur.getChild(word.charAt(i), true);
            }
            cur.insertWord(word);
        }
        
        // If the prefix does not exist, return empty list.
        public List<String> search(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                cur = cur.getChild(prefix.charAt(i), false);
                if (cur == null) return new ArrayList<String>();
            }
            return cur.getWords();
        }
    }
    
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> results = new ArrayList<List<String>>();
        if (words.length == 0) return results;
        
        int maxLength = words[0].length();
        Trie trie = new Trie();
        trie.buildTrie(words);
        helper(results, new ArrayList<String>(), trie, maxLength);
        return results;
    }
    
    private void helper(List<List<String>> results, List<String> cur, Trie trie, int maxLength) {
        // End case: word square is full, add to result.
        if (cur.size() == maxLength) {
            results.add(new ArrayList<String>(cur));
            return;
        }
        // Generate the prefix. The prefix of ith line is the same as ith column of current words.
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < cur.size(); i++) {
            prefix.append(cur.get(i).charAt(cur.size()));
        }
        // Backtracking to search all words with given prefix.
        for (String candidate : trie.search(prefix.toString())) {
            cur.add(candidate);
            helper(results, cur, trie, maxLength);
            cur.remove(cur.size() - 1);
        }
    }
}