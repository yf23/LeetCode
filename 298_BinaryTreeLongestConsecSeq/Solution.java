/*
    Binary Tree Longest Consecutive Sequence

    Given a binary tree, find the length of the longest consecutive sequence path.

    The path refers to any sequence of nodes from some starting node
    to any node in the tree along the parent-child connections.
    The longest consecutive path need to be from parent to child (cannot be the reverse).

    For example,
       1
        \
         3
        / \
       2   4
            \
             5
    Longest consecutive sequence path is 3-4-5, so return 3.

       2
        \
         3
        / 
       2    
      / 
     1
    Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

    Solution:
    Use DFS approach, keep track of the length of current consecutive sequence.
    If the length exceeds recorded max, replace it.

    Yu Fu, 10/01/2016
 */

public class Solution {
    private int max = 0;   // Record max length
    
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        DFS(root, 0, root.val);
        return max;
    }
    
    // DFS search for longest consecutive sequence.
    // If root's value equals target, it is part of consecutive sequence.
    // Add 1 to current length and update max.
    // If it is not part of consecutive sequence,
    // set as new start of sequence, and set current length to 1.
    // Set children nodes target to be root.val + 1, and recursively search.
    public void DFS(TreeNode root, int cur, int target) {
        if (root == null) return;
        if (root.val == target) cur++;
        else cur = 1;
        max = Math.max(max, cur);
        DFS(root.left, cur, root.val + 1);
        DFS(root.right, cur, root.val + 1);
    }
}