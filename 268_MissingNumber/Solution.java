/*
    Missing Number
    
    Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
    find the one that is missing from the array.

    For example,
    Given nums = [0, 1, 3] return 2.

    Note:
    Your algorithm should run in linear runtime complexity.
    Could you implement it using only constant extra space complexity?

    Solution:
    Sum of 0, 1, ..., n is n * (n + 1) / 2.
    The missing number is SUM(0, 1, ..., n) - SUM(nums)
 */

public class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int k : nums) sum += k;
        return (n * (n + 1) / 2) - sum;
    }
}