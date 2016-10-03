/*
    Product of Array Except Self

    Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

    Solve it without division and in O(n).

    For example, given [1,2,3,4], return [24,12,8,6].

    Solution:
    Given Array: a_0, a_1, ..., a_(n-2), a_(n-1)
    Forward Product Array: 1, a_0, a_0 * a_1, ..., a_0 * a_1 * ... * a_(n-3), a_0 * a_1 * ... * a_(n-2)
    Backward Product Array: a_1 * ... * a_(n-1), a_2 * ... * a_(n-1), ..., a_(n-1), 1
    Result Array is the elementwise product of forward and backward product array.

    Yu Fu, 09/28/2016
 */

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] forwardProd = new int[nums.length];
        int[] backwardProd = new int[nums.length];
        
        forwardProd[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            forwardProd[i] = forwardProd[i - 1] * nums[i - 1];
        }
        
        backwardProd[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            backwardProd[i] = backwardProd[i + 1] * nums[i + 1];
        }
        
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = forwardProd[i] * backwardProd[i];
        }
        
        return result;
    }
}