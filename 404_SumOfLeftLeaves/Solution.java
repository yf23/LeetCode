/*
    Sum of Left Leaves

    Find the sum of all left leaves in a given binary tree.

    Example:

        3
       / \
      9  20
        /  \
       15   7

    There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

    Yu Fu, 09/29/2016
*/

public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        // Return 0 if root is null.
        if (root == null) return 0;

        // If root has no left child, return left leaves sum of right child.
        if (root.left == null) return sumOfLeftLeaves(root.right);

        // If root has left child and left child is leaf,
        // return value of left child + left leaves sum of right child.
        if (root.left.left == null && root.left.right == null) return root.left.val + sumOfLeftLeaves(root.right);

        // If root has left child and left child is not leaf,
        // return left leaves sum of left child + return left leaves sum of right child.
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}