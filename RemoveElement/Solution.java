/*
    Remove Element

	Given an array and a value, remove all instances of that value in place and return the new length.

	The order of elements can be changed. It doesn't matter what you leave beyond the new length.

    Yu Fu, Jan 5 2015
 */

public class Solution {
    public int removeElement(int[] nums, int val) {
        int newLength = nums.length;
        for (int i = 0; i < newLength; i++) {
            if (nums[i] == val) {
                if (nums[newLength - 1] != val) flip(nums, i, newLength - 1);
                else i--;
                newLength--;
            }
        }
        if (newLength - 1 >= 0 && nums[newLength - 1] == val) newLength--;
        return newLength;
    }
    
    private void flip(int[] arr, int ind1, int ind2) {
        int temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
    }
}