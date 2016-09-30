/*
    3Sum

    Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note: The solution set must not contain duplicate triplets.

    For example, given array S = [-1, 0, 1, 2, -1, -4],

    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]

    Solution:
    First sort the array to avoid duplicate triplets.
    Then for each number from the start of array,
    use the negative of the number as target value in 2-sum problem,
    and the subarray after the number as candidates in 2-sum.
    Use 2sum to find possible combinations.

    2Sum:
    Maintain a HashMap of number and frequency.
    If n + n = target and n appears more than once, [n, n] is a 2sum pair.
    Else if n and (target - n) both are keys of HashMap, [n, target - n] is a 2sum pair.
    Set frequency of already checked solution to 0 to avoid duplication.
    Return all possible 2sum pairs as list.

    Yu Fu, 09/30/2016
 */

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array.
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length == 0) return result;
        
        // Keep track of previous calculated number to avoid duplicate numbers.
        int prev = nums[0] - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != prev) {
                prev = nums[i];
                // Get all possible 2sum pairs with target -num[i] using numbers after cur.
                for (List<Integer> l : twoSum(nums, 0 - nums[i], i + 1)) {
                    l.add(nums[i]);
                    result.add(l);
                }
            }
        }
        return result;
    }
    
    // Return all 2sum pairs as list using nums[start:]
    public List<List<Integer>> twoSum(int[] nums, int value, int start) {
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // Keep track of frequency of numbers
        for (int i = start; i < nums.length; i++) {
            mp.put(nums[i], mp.containsKey(nums[i]) ? mp.get(nums[i]) + 1 : 1);
        }
        
        for (int i = start; i < nums.length; i++) {
            int key = value - nums[i];
            // If n + n = target and n appears more than once, [n, n] is a 2sum pair.
            // If n and (target - n) both are keys of HashMap, [n, target - n] is a 2sum pair.
            if ((key == nums[i] && mp.get(nums[i]) >= 2) ||
                (key != nums[i] && mp.containsKey(key) && mp.get(key) > 0)) {
                result.add(new ArrayList<Integer>(Arrays.asList(nums[i], key)));
                // Set freq to 0 to avoid duplication.
                mp.put(nums[i], 0);
                mp.put(key, 0);
            }
        }
        return result;
    }
}