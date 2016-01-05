/*
    Two Sum

    Given an array of integers, find two numbers such that they add up to a specific target number.

    The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

    You may assume that each input would have exactly one solution.

    Input: numbers={2, 7, 11, 15}, target=9
    Output: index1=1, index2=2

    Yu Fu, Jan 5 2015
 */

public class Solution {
    
    /* Use HashMap, only loop through array once.
       Since containsKey and get are both O(1),
       total is O(n).                             
     */
    public int[] twoSumHashMap(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (mp.containsKey(target - nums[i])) {
                result[0] = mp.get(target - nums[i]);
                result[1] = i + 1;
                return result;
            }
            mp.put(nums[i], i + 1);
        }
        return result;
    }

    /* Use two pointers to loop through array. O(n^2). */
    public int[] twoSumWithTwoPts(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                }       
            }
        }
        return result;
    }
}