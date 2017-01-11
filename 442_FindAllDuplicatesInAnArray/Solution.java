/*
    Find All Duplicates in an Array

    Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

    Find all the elements that appear twice in this array.

    Could you do it without extra space and in O(n) runtime?

    Example:
    Input:
    [4,3,2,7,8,2,3,1]

    Output:
    [2,3]

    Solution:
    Flip the sign of nums[n-1] for any number n in the array.
    If a number is fliped twice (already negative when trying to flip),
    (n + 1) appears twice in the array.

    Yu Fu, Jan/10/2017
 */

public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int n : nums) {
            int ind = Math.abs(n) - 1;
            if (nums[ind] < 0) result.add(ind + 1);
            nums[ind] = -1 * nums[ind];
        }

        return result;
    }
}