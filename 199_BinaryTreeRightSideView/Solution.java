/*
    Binary Tree Right Side View

    Given a binary tree, imagine yourself standing on the right side of it,
    return the values of the nodes you can see ordered from top to bottom.

    For example:
    Given the following binary tree,
       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---

    You should return [1, 3, 4].

    Solution:
    Similar to level order traversal.
    Instead of add value to sublist, just renew the value on corresponding level index.

    Yu Fu, 09/28/2016
 */

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        helper(root, 0, result);
        return result;
    }
    
    public void helper(TreeNode root, int level, List<Integer> l) {
        if (root != null) {
            if (l.size() <= level) l.add(root.val);
            else l.set(level, root.val);
            helper(root.left, level + 1, l);
            helper(root.right, level + 1, l);
        }
    }
}