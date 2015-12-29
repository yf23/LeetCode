/*
	Binary Tree Inorder Traversal

    Given a binary tree, return the inorder traversal of its nodes' values.

    For example:
    Given binary tree {1,#,2,3},
       1
        \
         2
        /
       3
    return [1,3,2].
    	
	Definition for a binary tree node:

	public class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	Both iterative and recursive solutions are implemented.
	
	Yu Fu, Dec 28 2015
 */
 
public class Solution {
    public List<Integer> inorderTraversalItr(TreeNode root) {
        Stack<TreeNode> st = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        TreeNode ptr = root;
        while (!st.isEmpty() || ptr != null) {
            if (ptr != null) {
                st.push(ptr);
                ptr = ptr.left;
            } else {
                ptr = st.pop();
                result.add(ptr.val);
                ptr = ptr.right;
            }
        }
        return result;
    }
 
    public List<Integer> preorderTraversalRec(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) {
            result.addAll(inorderTraversal(root.left));
            result.add(root.val);
            result.addAll(inorderTraversal(root.right));
        }
        return result;
    }
}
