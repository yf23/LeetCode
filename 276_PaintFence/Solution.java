/*
    Paint Fence

    There is a fence with n posts, each post can be painted with one of the k colors.

    You have to paint all the posts such that no more than two adjacent fence posts have the same color.

    Return the total number of ways you can paint the fence.

    Note:
    n and k are non-negative integers
    
    Yu Fu, 09/29/2016
 */

public class Solution {
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        
        int same = k;             // Number of ways to paint fence with same color as previous one.
        int diff = k * (k - 1);   // Number of ways to paint fence with different color as previous one.
        
        // Only fences painted differently during last step can be printed with the same color again.
        // All of fences can be printed with different color,
        for (int i = 3; i <= n; i++) {
            int oldSame = same;
            same = diff;          
            diff = (diff + oldSame) * (k - 1);
        }
        return diff + same;
    }
}