/*
    Shuffle an Array

    Shuffle a set of numbers without duplicates.

    Example:

    // Init an array with set 1, 2, and 3.
    int[] nums = {1,2,3};
    Solution solution = new Solution(nums);

    // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
    solution.shuffle();

    // Resets the array back to its original configuration [1,2,3].
    solution.reset();

    // Returns the random shuffling of array [1,2,3].
    solution.shuffle();

    Yu Fu, 09/29/2016
 */

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

public class Solution {
    int[] ori;
    int[] cur;
    
    public Solution(int[] nums) {
        ori = new int[nums.length];
        cur = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ori[i] = nums[i];
            cur[i] = nums[i];
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return ori;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random r = new Random();
        // Starting from a_(n-1) to a_0,
        // For a_i, find a random int j between 0 and i, swap a_i and a_j.
        for (int i = cur.length - 1; i >= 0; i--) {
            int j = r.nextInt(i + 1);
            int temp = cur[j];
            cur[j] = cur[i];
            cur[i] = temp;
        }
        return cur;
    }
}