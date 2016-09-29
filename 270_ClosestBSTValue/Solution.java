/*
    Closest Binary Search Tree Value

    Given a non-empty binary search tree and a target value,
    find the value in the BST that is closest to the target.

    Note:
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.

    Yu Fu, 09/29/2016
 */

public class Solution {
    public int closestValue(TreeNode root, double target) {
        // If root is leave, or target equals root value, return root value.
        if (root.left == null && root.right == null || target == root.val) return root.val;
        // If target is less than root value,
        // get the closest value starting from left child if exist.
        // Compare with root value and return the closer one.
        if (target < root.val) {
            if (root.left == null) return root.val;
            else {
                int leftClosestValue = closestValue(root.left, target);
                return (Math.abs(leftClosestValue - target) > Math.abs(root.val - target)) ? root.val : leftClosestValue;
            }
        // If target is greater than root value,
        // get the closest value starting from right child if exist.
        // Compare with root value and return the closer one.
        } else {
            if (root.right == null) return root.val;
            else {
                int rightClosestValue = closestValue(root.right, target);
                return (Math.abs(rightClosestValue - target) > Math.abs(root.val - target)) ? root.val : rightClosestValue;
            }
        }
    }
}