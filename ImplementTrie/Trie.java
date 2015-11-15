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

// Implementation of TrieNode.
class TrieNode {
    String word;
    TrieNode[] children;
    
    public TrieNode() {
        children = new TrieNode[27];
    }
}

// Implementation of Trie tree.
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode current = root;
        String wordCopy = word;
        while (word.length() != 0) {
            if (current.children[word.charAt(0) - 'a'] == null) {
                current.children[word.charAt(0) - 'a'] = new TrieNode();
            }
            current = current.children[word.charAt(0) - 'a'];
            word = word.substring(1);
        }
        while (current.word != null) {
            if (current.word.equals(wordCopy)) {
                return;
            } else {
                if (current.children[26] == null) {
                    current.children[26] = new TrieNode();
                }
                current = current.children[26];
            }
        }
        current.word = wordCopy;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode current = root;
        String wordCopy = word;
        while (word.length() != 0) {
            current = current.children[word.charAt(0) - 'a'];
            if (current == null) return false;
            word = word.substring(1);
        }
        while (current != null) {
            if (!wordCopy.equals(current.word)) {
                current = current.children[26];
            } else {
                return true;
            }
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        while (prefix.length() != 0) {
            current = current.children[prefix.charAt(0) - 'a'];
            if (current == null) return false;
            prefix = prefix.substring(1);
        }
        if (current != null) {
            if (current.word != null) return true;
            for (TrieNode child : current.children) {
                if (child != null) {
                    return true;
                }
            }
        }
        return false;
    }
}