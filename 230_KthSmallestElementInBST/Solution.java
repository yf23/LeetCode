/*
    Kth Smallest Element in a BST

    Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

    Note: 
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

    Follow up:
    What if the BST is modified (insert/delete operations) often
    and you need to find the kth smallest frequently?
    How would you optimize the kthSmallest routine?

    Hint:
    Try to utilize the property of a BST.
    What if you could modify the BST node's structure?
    The optimal runtime complexity is O(height of BST).

    Solution:
    If a node has (k - 1) nodes on the left, then it's the kth smallest element.
    If a node has more than (k - 1) nodes on the left, the kth smallest element is in its left subtree.
    If a node has less than (k - 1) nodes on the left, the kth samllest element is in its right subtree.

    Yu Fu, 10/16/2016
 */

public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int leftCount = getCount(root.left);
        // If a node has (k - 1) nodes on the left, then it's the kth smallest element.
        if (leftCount == k - 1) return root.val;
        // Less than (k - 1) nodes on the left, the kth samllest element is in its right subtree.
        else if (leftCount < k - 1) return kthSmallest(root.right, k - leftCount - 1);
        // More than (k - 1) nodes on the left, the kth smallest element is in its left subtree.
        else return kthSmallest(root.left, k);
    }
    
    // Count number of nodes
    private int getCount(TreeNode root) {
        if (root == null) return 0;
        return getCount(root.left) + getCount(root.right) + 1;
    }
}