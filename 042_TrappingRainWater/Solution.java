/*
    Trapping Rain Water

    Given n non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it is able to trap after raining.

    For example, 
    Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
    
           @
       @...@@.@
     @.@@.@@@@@@

    Solution:
    For position at i, it can hold water up to the same level as
    the lower of highest wall on left and highest wall on right.
    For each position, find its highest wall on the left and right.
    Amonunt of water in each position is
    the water level it can hold - the height of wall in that position.
    If the wall is higher than water it can hold, count as 0.

 */

public class Solution {
    public int trap(int[] height) {
        if (height.length <= 1) return 0;
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        
        // Find highest wall on left side for each bar.
        maxLeft[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }
        
        // Find highest wall on right side for each bar.
        maxRight[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }
        
        // Max Water Level = min(highest wall on left, highest wall on right)
        // Water in this bar = Max Water Level - Wall in this bar or zero.
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            sum += Math.max(0, Math.min(maxLeft[i], maxRight[i]) - height[i]);
        }
        return sum;
    }
}