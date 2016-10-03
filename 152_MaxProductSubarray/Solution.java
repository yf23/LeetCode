/* 
    Maximum Product Subarray

    Find the contiguous subarray within an array (containing at least one number)
    which has the largest product.

    For example, given the array [2,3,-2,4],
    the contiguous subarray [2,3] has the largest product = 6.
    
    Solution:
    Keep track of local minimum and maximum product.
    When next number is negative, swap min and max and then perform multiplication.

    Yu Fu, 09/28/2016
 */
public class Solution {
    public int maxProduct(int[] nums) {
        // Max product value seen so far.
        int maxProdGlobal = nums[0];
        
        // Max and min product ends locally.
        int maxProdLocal = nums[0];
        int minProdLocal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // Multiply a negative value will swap the local min and max.
            if (nums[i] < 0) {
                int temp = minProdLocal;
                minProdLocal = maxProdLocal;
                maxProdLocal = temp;
            }
            
            // Keep track of local min and max after multiply with next number.
            minProdLocal = Math.min(nums[i], nums[i] * minProdLocal);
            maxProdLocal = Math.max(nums[i], nums[i] * maxProdLocal);
            
            // Update global max.
            maxProdGlobal = Math.max(maxProdGlobal, maxProdLocal);
        }
        return maxProdGlobal;
    }
}