/*
    Valid Perfect Square

    Given a positive integer num, write a function which returns True
    if num is a perfect square else False.

    Note: Do not use any built-in library function such as sqrt.

    Example 1:

    Input: 16
    Returns: True
    Example 2:

    Input: 14
    Returns: False

    Solution:
    Use Newton's method to find integer sqrt.
    a_(k+1) = (a_k + x / a_k) / 2
    When find integer square root, if its square equals x, x is perfect square.

    Yu Fu, 09/29/2016
 */

public class Solution {
    public boolean isPerfectSquare(int num) {
        double prev = num;
        double next = (prev + num / prev) / 2;
        while (Math.abs(prev - next) >= 1) {
            prev = next;
            next = (prev + num / prev) / 2;
        }
        return Math.floor(next) * Math.floor(next) == num;
    }
}