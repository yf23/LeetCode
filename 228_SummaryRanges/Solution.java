/*
    Summary Ranges

    Given a sorted integer array without duplicates, return the summary of its ranges.

    For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

    Yu Fu, 10/01/2016
 */

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        if (nums.length == 0) return result;
        
        int start = nums[0];    // Set start as first number of array.
        for (int i = 1; i < nums.length; i++) {
            // If not continuous, range is from start to last continuous number
            if (nums[i - 1] + 1 != nums[i]) {
                // If more than one number in range
                if (start != nums[i - 1]) {
                    result.add(start + "->" + nums[i - 1]);
                // Only start in range
                } else {
                    result.add(start + "");
                }
                // Set next number as new start
                start = nums[i];
            }
        }
        // Put last range into list.
        if (start == nums[nums.length - 1]) result.add(start + "");
        else result.add(start + "->" + nums[nums.length - 1]);
        return result;
    }
}