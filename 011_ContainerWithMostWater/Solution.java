/*
    Container With Most Water

    Given n non-negative integers a1, a2, ..., an,
    where each represents a point at coordinate (i, ai).
    n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
    Find two lines, which together with x-axis forms a container,
    such that the container contains the most water.

    Note: You may not slant the container.

    Solution:
    Start at both end of arrays as boundary of container.
    Each step move the lower side (in the direction to the other side).

    Yu Fu, 09/30/2016
 */

public class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        
        // Change the lower side of container to the next value;
        while (left < right) {
            max = Math.max((right - left) * Math.min(height[left], height[right]), max);
            if (height[left] <= height[right]) left++;
            else right--;
        }
        
        return max;
    }
}