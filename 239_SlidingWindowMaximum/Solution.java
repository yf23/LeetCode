/*
    Longest Substring with At Most K Distinct Characters

    Given an array nums, there is a sliding window of size k which is moving
    from the very left of the array to the very right.
    You can only see the k numbers in the window.
    Each time the sliding window moves right by one position.

    For example,
    Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
    Therefore, return the max sliding window as [3,3,5,5,6,7].

    Note: 
    You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

    Follow up:
    Could you solve it in linear time?

    Solution:
    Let the array be divied into parts with size k.

    a_0 a_1 ... a_(k-1)  |  b_0 b_1 .. b_(k-1)  | ...

    Keep max counting from left:
    Max(a_0) ... Max(a_0, a_1, ..., a_(k-1)) | Max(b_0) ... Max(b_0, b_1, ..., b_(k-1)) | ...

    Keep max counting from right:
    Max(a_(k-1), ..., a_0) ... Max(a_(k-1)) | Max(b_(k-1), ..., b_0) ... Max(b_(k-1)) | ...

    Let sliding window be
         (k - i) from a   |   (i from b)
    [a_i, a_(i+1), ..., a_(k-1) | b_0, b_1, ..., b_i]

    Max of left part is Max(a_(k-1), ..., a_i) = RM[Idx(a_i)],
    Max of right part is Max(b_0, b_1, ..., b_i) = LM[Idx(b_i)],
    where Idx(b_i) = Idx(a_i) + k - 1.

    The result is Max(RM[Idx(a_i)], LM[Idx(b_i)])

    Yu Fu, 10/03/2016
 */

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int[] leftMax = new int[nums.length];
        int[] rightMax = new int[nums.length];
        
        // Max in segment counting from left.
        leftMax[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            leftMax[i] = (i % k == 0) ? nums[i] : Math.max(nums[i], leftMax[i - 1]);
        }
        
        // Max in segment counting from right.
        rightMax[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            rightMax[i] = ((i + 1) % k == 0) ? nums[i] : Math.max(nums[i], rightMax[i + 1]);
        }
        
        // Max(RM[start], LM[end]), end = start + k - 1
        int[] maxSlide = new int[nums.length - k + 1];
        for (int i = 0; i + k <= nums.length; i++) {
            maxSlide[i] = Math.max(leftMax[i + k - 1], rightMax[i]);
        }
        return maxSlide;
    }
}}
}