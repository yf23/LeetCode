/*
    Sqrt(x)

    Implement int sqrt(int x).

    Compute and return the square root of x.

    Solution:
    Use Newton's method to find integer sqrt.
    a_(k+1) = (a_k + x / a_k) / 2

    Yu Fu, 09/29/2016
 */

public class Solution {
    public int mySqrt(int x) {
        double prev = x;
        double next = (prev + x / prev) / 2;
        while (Math.abs(next - prev) >= 1) {
            prev = next;
            next = (prev + x / prev) / 2;
        }
        return (int) Math.floor(next);
    }
}