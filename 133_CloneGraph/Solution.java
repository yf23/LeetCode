/*
    Clone Graph

    Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

    Solution:
    Use DFS (recursion) to trasverse graph.
    For visited nodes, store it in Hash table to avoid repeatingly creating new nodes.
    For cycles, put the node in Hash table before recursionly adding neighbors. (IMPORTANT!)

    Yu Fu, 10/11/2016
 */

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> created = new HashMap<Integer, UndirectedGraphNode>();
        return clone(node, created);
    }
    
    public UndirectedGraphNode clone(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> created) {
        if (node == null) return null;
        if (created.containsKey(node.label)) return created.get(node.label);
        else {
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
            // For cycles, put the node in Hash table before recursionly adding neighbors. 
            // IMPORTANT! Wrong order causes infinity recursion.
            created.put(node.label, newNode);
            // Recursively adding all the neighbours.
            for (UndirectedGraphNode n : node.neighbors) {
                newNode.neighbors.add(cloneGraphHelper(n, created));
            }
            return newNode;
        }
    }
}