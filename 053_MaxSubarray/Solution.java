/*
    Maximum Subarray

    Find the contiguous subarray within an array (containing at least one number)
    which has the largest sum.

    For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
    the contiguous subarray [4,-1,2,1] has the largest sum = 6.

    Solution:
    Kadane's algorithm.
    If SUM(a_i to a_j) < 0, i < j, then a_i to a_j will be excluded from maximum subarray.
    New sum starts with a_(j + 1).
    Keep record the maximum sum in record and return it as result.

    Yu Fu, 09/28/2016
 */

public class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize the sum as the first element of array.
        int sum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // If SUM(a_i to a_j) + a(j+1) < a(j+1) <=> SUM(a_i to a_j) < 0,
            // cut a_i to a_j, set new sum to a_(j+1).
            // Else continue with the sum.
            sum = Math.max(nums[i], sum + nums[i]);
            // Record the maximum sum.
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
}