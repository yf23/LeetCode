/*
    Find All Numbers Disappeared in an Array

    Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
    some elements appear twice and others appear once.

    Find all the elements of [1, n] inclusive that do not appear in this array.

    Could you do it without extra space and in O(n) runtime?
    You may assume the returned list does not count as extra space.

    Example:

    Input:
    [4,3,2,7,8,2,3,1]

    Output:
    [5,6]

    Solution:
    Go through the array once, for a number n, mark the number at index n - 1 as negative.
    Go through the array again. Number at index d with positive value means d + 1 does not appear.

    Yu Fu, 01/03/2017
 */

public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        // Mark number at index n - 1 negative.
        for (int n : nums) {
            int ind = Math.abs(n) - 1;
            if (nums[ind] > 0) nums[ind] = -nums[ind];
        }
        // nums[i] is positive means i + 1 does not appear.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) result.add(i + 1);
        }
        return result;
    }
}