/*
    Find Peak Element

    A peak element is an element that is greater than its neighbors.

    Given an input array where num[i] ≠ num[i+1],
    find a peak element and return its index.

    The array may contain multiple peaks,
    in that case return the index to any one of the peaks is fine.

    You may imagine that num[-1] = num[n] = -∞.

    For example, in array [1, 2, 3, 1],
    3 is a peak element and your function should return the index number 2.

    Note:
    Your solution should be in logarithmic complexity.

    Solution:
    Binary Search

    Yu Fu, 10/02/2016
*/

public class Solution {
    public int findPeakElement(int[] nums) {
        int high = nums.length - 1;
        int low = 0;
        
        while (low < high) {
            // For neighbour elements, higher one is the peak.
            if (high - low == 1) return (nums[low] > nums[high] ? low : high);

            int mid = (high - low) / 2 + low;
            // Mid is at down hill, peak on left
            if (nums[mid] > nums[mid + 1]) {
                high = mid;
            // Mid is at up hill, peak on right
            } else {
                low = mid;
            }
        }
        return 0;
    }
}