/*
    Alien Dictionary

    There is a new alien language which uses the latin alphabet.
    However, the order among letters are unknown to you.
    You receive a list of words from the dictionary 
    where words are sorted lexicographically by the rules of this new language.
    Derive the order of letters in this language.

    For example,
    Given the following words in dictionary,

    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]

    The correct order is: "wertf".

    Note:
    You may assume all letters are in lowercase.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.

    Solution:
    View characters as nodes.
    If a character c1 appears before c2, there is an edge from c1 to c2 (directed).
    Use topology sort to get orders after building the graph.

    Kahn's Algorithm for Topology Sort (In this case use BFS Queue to replace Set):
    L ← Empty list that will contain the sorted elements
    S ← Set of all nodes with no incoming edges
    while S is non-empty do
        remove a node n from S
        add n to tail of L
        for each node m with an edge e from n to m do
            remove edge e from the graph
            if m has no other incoming edges then
                insert m into S
    if graph has edges then
        return error (graph has at least one cycle)
    else 
        return L (a topologically sorted order)

    Yu Fu, 11/10/2016
 */

public class Solution {
    public String alienOrder(String[] words) {
        // Build HashMap of Nodes and their degrees.
        HashMap<Character, Integer> nodes = new HashMap<Character, Integer>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                nodes.put(c, 0);
            }
        }
        
        // Build HashMap of nodes and a set of nodes with it connects to (edges).
        // Also calculate degrees (in) for each node.
        HashMap<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (!word1.equals(word2)) {
                // SPECIAL CASE: Prefix should comes first.
                // Example: ["wrtkj","wrt"] is invalid.
                if (word1.startsWith(word2)) return "";
                for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                    char c1 = word1.charAt(j);
                    char c2 = word2.charAt(j);
                    if (c1 != c2) {
                        if (!graph.containsKey(c1)) graph.put(c1, new HashSet<Character>());
                        // Add the edge if the edge does not exist, and update degree.
                        if (graph.get(c1).add(c2)) nodes.put(c2, nodes.get(c2) + 1);
                        break;
                    } 
                }
            }
        }
        
        // Topological Sort using BFS.
        StringBuilder sb = new StringBuilder();
        Queue<Character> nodesWithZeroDeg = new LinkedList<Character>();
        for (char c : nodes.keySet()) {
            if (nodes.get(c) == 0) nodesWithZeroDeg.add(c);
        }
        while (!nodesWithZeroDeg.isEmpty()) {
            char cur = nodesWithZeroDeg.remove();
            sb.append(cur);
            if (graph.containsKey(cur)) {
                for (char c : graph.get(cur)) {
                    nodes.put(c, nodes.get(c) - 1);
                    if (nodes.get(c) == 0) nodesWithZeroDeg.add(c);
                }
            }
        }
        
        if (nodes.size() != sb.length()) return "";   // Cycles exist, invalid.
        return sb.toString();
    }
}