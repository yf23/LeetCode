/*  
    Implement Trie (Prefix Tree)
    
    Implement a trie with insert, search, and startsWith methods.

    Note:
    You may assume that all inputs are consist of lowercase letters a-z.

    Your Trie object will be instantiated and called as such:
    trie.insert("somestring");
    trie.search("key");

    Yu Fu, Nov 14 2015
 */

// Implementation of TrieNode
class TrieNode {
    // Initialize your data structure here.
    boolean isWord;
    TrieNode[] children;
    
    public TrieNode() {
        children = new TrieNode[26];
    }
}

// Implementation of Trie tree
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        insertHelper(word, root);
    }
    
    private void insertHelper(String sequence, TrieNode current) {
        if (sequence.length == 0) current.isWord = true;
        if (current.children[sequence.charAt(0) - 'a'] == null) {
            current.children[sequence.charAt(0) - 'a'] = new TrieNode();
        }
        insertHelper(sequence.substring(1), current.children[sequence.charAt(0) - 'a']);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return searchHelper(word, root);
    }
    
    private boolean searchHelper(String sequence, TrieNode current) {
        if (current == null) return false;
        if (sequence.length() == 0) return current.isWord;
        return searchHelper(sequence.substring(1), current.children[sequence.charAt(0) - 'a']);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return startsWithHelper(prefix, root);
    }
    
    private boolean startsWithHelper(String sequence, TrieNode current) {
        if (current == null) return false;
        if (sequence.length() == 0) {
            boolean haveChildren = false;
            for (TrieNode child : current.children) {
                haveChildren = haveChildren || (child != null);
            }
            return haveChildren || current.isWord;
        }
        return startsWithHelper(sequence.substring(1), current.children[sequence.charAt(0) - 'a']);
    }
}