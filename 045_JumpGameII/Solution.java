/*
    Jump Game II

    Given an array of non-negative integers,
    you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.

    Your goal is to reach the last index in the minimum number of jumps.

    For example:
    Given array A = [2,3,1,1,4]

    The minimum number of jumps to reach the last index is 2.
    (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

    Note:
    You can assume that you can always reach the last index.

    Solution:
    Use greedy to calculate farthest reachable index.
    Also calculate the farthest end of current jump.
    Add one more jump if exceed current end, and set end to curent reachable.
    When reach end, add one more jump to there and return it.

    Yu Fu, 11/09/2016
 */

public class Solution {
    public int jump(int[] nums) {
        int reachable = 0, end = 0, step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            reachable = Math.max(reachable, nums[i] + i);
            if (reachable >= nums.length - 1) {
                step++; break;
            }
            
            if (i == end) {
                step++;
                end = reachable;
            }
        }
        return step;
    }
}