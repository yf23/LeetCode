/*
    Implement Trie

    Implement a trie with insert, search, and startsWith methods.

    Note:
    You may assume that all inputs are consist of lowercase letters a-z.

    Yu Fu, 11/30/2016
 */

class TrieNode {
    // Initialize your data structure here.
    TrieNode[] children;
    boolean isWord;
    
    public TrieNode() {
        isWord = false;
        children = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int ind = word.charAt(i) - 'a';
            if (cur.children[ind] == null) {
                cur.children[ind] = new TrieNode();
            }
            cur = cur.children[ind];
        }
        cur.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int ind = word.charAt(i) - 'a';
            if (cur.children[ind] == null) return false;
            cur = cur.children[ind];
        }
        return cur.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int ind = prefix.charAt(i) - 'a';
            if (cur.children[ind] == null) return false;
            cur = cur.children[ind];
        }
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");