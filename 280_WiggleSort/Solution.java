/*
    Wiggle Sort

    Given an unsorted array nums,
    reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

    For example, given nums = [3, 5, 2, 1, 6, 4],
    one possible answer is [1, 6, 2, 5, 3, 4].
    
    Solution:
    Loop through array, if order is not right, swap it with neighbour.

    Yu Fu, 10/02/2016
 */
public class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // Odd entry
            if (i % 2 == 0) {
                // If previous entry is smaller, swap.
                if (i != 0 && nums[i - 1] < nums[i]) {
                    swap(nums, i, i - 1);
                }
            // Even entry
            } else {
                // If previous entry is larger, swap.
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i, i - 1);
                }
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}