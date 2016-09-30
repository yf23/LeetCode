/*
    Pow(x, n)

    Implement pow(x, n).

    Yu Fu, 09/29/2016
 */

public class Solution {
    public double myPow(double x, int n) {
        // 0 ^ k = 0, 1 ^ k = 1 for any k.
        if (x == 0.0 || x == 1.0) return x;
        
        // x ^ 0 = 1, x ^ 1 = x for any x.
        if (n == 0) return 1.0;
        if (n == 1) return x;
        
        // Specaial case: x ^ (MIN_INT) = x ^ (-MAX_INT - 1) = 1 / x ^ (MAX_INT) / x
        if (n == Integer.MIN_VALUE) return 1.0 / myPow(x, Integer.MAX_VALUE) / x;
        // If k < 0, x ^ k = 1 / x ^ (-k)
        if (n < 0) return 1.0 / myPow(x, -1 * n);
        
        // If k is even, x ^ k = x ^ (k / 2 + k / 2) = (x ^ (k / 2)) ^ 2
        // If k is odd, x ^ k = x ^ (k / 2 + k / 2 + 1) = (x ^ (k / 2)) ^ 2 * x
        double r = myPow(x, n / 2);
        return (n % 2 == 0) ? r * r : r * r * x;
    }
}