/*  
    Convert Sorted Array to Binary Search Tree

    Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

    Solution:
    Take the number in the middle as root of tree.
    
    Yu Fu, 09/27/2016
 */

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        if (nums.length == 1) return new TreeNode(nums[0]);
        int midIdx = nums.length / 2;
        TreeNode result = new TreeNode(nums[midIdx]);
        result.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, midIdx));
        result.right = sortedArrayToBST(Arrays.copyOfRange(nums, midIdx + 1, nums.length));
        return result;
    }
}