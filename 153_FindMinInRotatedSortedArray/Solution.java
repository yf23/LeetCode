/*
    Find Minimum in Rotated Sorted Array

    Suppose a sorted array is rotated at some pivot unknown to you beforehand.

    (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

    Find the minimum element.

    Solution:
    Binary search for a[j+1] < a[j], then a[j+1] is minimum.

    Yu Fu, 09/28/2016
 */

public class Solution {
    public int findMin(int[] nums) {
        // The sorted array is not rotated. Retrun first element of array.
        if (nums[0] <= nums[nums.length - 1]) return nums[0];
        
        // Binary search for where a[j+1] < a[j].
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;

            // Return if min found.
            if (nums[mid + 1] < nums[mid]) return nums[mid + 1];
            
            // If a[mid] >= a[0], target is on the right side of mid + 1.
            if (nums[mid] >= nums[0]) low = mid + 1;

            // If a[mid+1] <= a[n-1], target is on the left side of mid.
            if (nums[mid + 1] <= nums[nums.length - 1]) high = mid;
        }

        return Math.min(nums[low], nums[high]);
    }
}