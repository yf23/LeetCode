/*
    Find Leaves of Binary Tree

    Given a binary tree, collect a tree's nodes as if you were doing this:
    Collect and remove all leaves, repeat until the tree is empty.

    Example:
    Given binary tree 
              1
             / \
            2   3
           / \     
          4   5    
    Returns [4, 5, 3], [2], [1].

    Explanation:
    1. Removing the leaves [4, 5, 3] would result in this tree:

              1
             / 
            2          
    2. Now removing the leaf [2] would result in this tree:

              1          
    3. Now removing the leaf [1] would result in the empty tree:

              []         
    Returns [4, 5, 3], [2], [1].
    
    Solution:
    Index of a node should be reverse height from lowest children leaf.
    h(NULL) = -1
    h(leaf) = 0
    h(Node) = MAX(h(Node.L), h(Node.R)) + 1

    Yu Fu, Jan/9/2017
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        addNode(root, result);
        return result;
    }
    
    public int addNode(TreeNode root, List<List<Integer>> result) {
        if (root == null) return -1;
        int ind  = Math.max(addNode(root.left, result), addNode(root.right, result)) + 1;
        if (ind > result.size() - 1) result.add(new ArrayList<Integer>());
        result.get(ind).add(root.val);
        return ind;
        
    }
}