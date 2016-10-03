/*
    3Sum

    Merge Intervals

    For example,
    Given [1,3],[2,6],[8,10],[15,18],
    return [1,6],[8,10],[15,18].

    Solution:
    Sort given list based on start.
    For two neighbor intervals,
    If the start time of second interval is larger then the end time of first interval,
    they are two seperate intervals. Store the previous interval in result list.
    Otherwise, they can be merged into one interval.
    The start of mergered interval is start time of first interval.
    The end of mergered interval is the larger end time of both intervals.

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