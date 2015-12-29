/*
	Binary Tree Preorder Traversal

	Given a binary tree, return the preorder traversal of its nodes' values.

	For example:
	Given binary tree {1,#,2,3},
	   1
	    \
	     2
	    /
	   3
	return [1,2,3].
	
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
    public List<Integer> preorderTraversalItr(TreeNode root) {
        Stack<TreeNode> st = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) {
            st.push(root);
            while (!st.isEmpty()) {
                TreeNode node = st.pop();
                result.add(node.val);
                if (node.right != null) {
                    st.push(node.right);
                }
                if (node.left != null) {
                    st.push(node.left);
                }
            }
        }
        return result;
    }
 
    public List<Integer> preorderTraversalRec(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) {
            result.add(root.val);
            result.addAll(preorderTraversal(root.left));
            result.addAll(preorderTraversal(root.right));
        }
        return result;
    }
}
