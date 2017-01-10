/*
    Arithmetic Slices

    A sequence of number is called arithmetic
    if it consists of at least three elements and
    if the difference between any two consecutive elements is the same.

    For example, these are arithmetic sequence:
    1, 3, 5, 7, 9
    7, 7, 7, 7
    3, -1, -5, -9

    The following sequence is not arithmetic.
    1, 1, 2, 5, 7

    A zero-indexed array A consisting of N numbers is given.
    A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

    A slice (P, Q) of array A is called arithmetic if the sequence:
    A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

    The function should return the number of arithmetic slices in the array A.

    Example:

    A = [1, 2, 3, 4]

    return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

    Solution:
    Let dp[i][j] means from index i to index j is an arithmetic slice.
    dp[i][j] is an arithemetic slice iff. dp[i][j-1] is also an arithemetic slice,
    and A[j] - A[j-1] == A[j-1] - A[j-2]

    Also when counting we ignore those with length 1 and 2, although dp[i][j] still be true.

    Yu Fu, Jan/9/2017
 */

public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if (n < 3) return 0;
        int count = 0;
        boolean[][] dp = new boolean[n][n];

        // Initialize length 1 and 2 to be true.
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            if (i - 1 >= 0) dp[i - 1][i] = true;
            if (i + 1 < n) dp[i][i + 1] = true;
        }
        
        // DP and count arithmetic slices.
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i][j - 1] && (A[j] - A[j - 1] == A[j - 1] - A[j - 2]);
                if (dp[i][j]) count++;
            }
        }
        
        return count;
    }
}