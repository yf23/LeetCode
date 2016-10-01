/*  
    Missing Ranges

    Given a sorted integer array where the range of elements are [lower, upper] inclusive,
    return its missing ranges.

    For example, 
    given  [0, 1, 3, 50, 75], lower = 0 and upper = 99,
    return ["2", "4->49", "51->74", "76->99"].

    Yu Fu, 09/30/2016
 */

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        // Set start and end both as lower bound.
        int start = lower;
        int end = lower;
        for (int i = 0; i < nums.length; i++) {
            end = nums[i] - 1;    // end is 1 less than current element.
            // If end < start, the range is not missing.
            // If end > start, the missring range is from start to end.
            // If end == start, it's the only missing number.
            if (end > start) {
                result.add(start + "->" + end);
            } else if (end == start) {
                result.add(start + "");
            }
            start = nums[i] + 1;
        }
        // Add range from the last start to upper bound.
        if (start == upper) result.add("" + upper);
        if (start < upper) result.add(start + "->" + upper);
        return result;
    }
}
