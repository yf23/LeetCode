/*
    Range Sum Query - Mutable

    Given an integer array nums,
    find the sum of the elements between indices i and j (i ≤ j), inclusive.

    The update(i, val) function modifies nums by updating the element at index i to val.
    Example:
    Given nums = [1, 3, 5]

    sumRange(0, 2) -> 9
    update(1, 2)
    sumRange(0, 2) -> 8

    Solution:
    Maintain a sum array such that sum[i] = a_0 + .. + a_i
    When update, calculate the changed value and applied to every entry in sum after i.

    Note:
    The array is only modifiable by the update function.
    You may assume the number of calls to update and sumRange function is distributed evenly.

    Yu Fu, 10/01/2016
 */

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
public class NumArray {
    int[] nums;
    int[] sum;
    
    public NumArray(int[] nums) {
        this.nums = nums;
        sum = new int[nums.length];
        // sum[i] = nums[0] + ... + nums[i]
        if (nums.length > 0) {
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) sum[i] = sum[i - 1] + nums[i];
        }
    }

    // Calculate change in value and add to every entry after idx in sum.
    void update(int idx, int val) {
        int delta = val - nums[idx];
        nums[idx] = val;
        for (int i = idx; i < sum.length; i++) {
            sum[i] += delta;
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) return sum[j];
        return sum[j] - sum[i - 1];
    }
}