/*
    Next Permutation

    Implement next permutation, which rearranges numbers into 
    the lexicographically next greater permutation of numbers.

    If such arrangement is not possible,
    it must rearrange it as the lowest possible order (ie, sorted in ascending order).

    The replacement must be in-place, do not allocate extra memory.

    Here are some examples.
    Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1
    
    Solution:
    1. Find the longest nonincreasing suffix.
       The left element of that suffix is the pivot.
    2. If the pivot is -1, it is the permutation with highest order, resort it to lowest.
    3. If the pivot is valid, swap it with the rightmost sucessor (greater than pivot).
    4. Sort the suffix.

    Yu Fu, 10/10/2016
 */

public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;
        int p = nums.length - 2;
        int q = nums.length - 1;
        
        // Find the longest nonincreasing suffix.
        // Do so as find the longest increasing prefix starting from right.
        while (p >= 0 && nums[p] >= nums[p + 1]) p--;
        
        // The whole array is non-increasing. Resort it to lowest order.
        if (p == -1) {
            Arrays.sort(nums);
            return;
        }
        
        // Find the rightmost sucessor (greater) of pivot.
        while (nums[q] <= nums[p]) q--;

        // Swap sucessor with pivot.
        swap(nums, p, q);

        // Sort the suffix.
        Arrays.sort(nums, p + 1, nums.length);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}