/*
    Counting Bits

    Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

    Example:
    For num = 5 you should return [0,1,1,2,1,2].

    Solution:
    0    0     0
    1    1     1
    2    10    1 + 0
    3    11    1 + 1
    4    100   1 + 0 + 0
    5    101   1 + 0 + 1
    6    110   1 + 1 + 0
    7    111   1 + 1 + 1

    a_n = a_(n / 2) + 0 if even
                    + 1 if odd
 */

public class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        // Initialize first two numbers of array as 0 and 1
        result[0] = 0;
        if (num == 0) return result;
        result[1] = 1;
        if (num == 1) return result;

        // a_i = a_(i / 2) + (i mod 2) 
        for (int i = 2; i <= num; i++) {
            result[i] = result[i / 2] + (i % 2);
        }
        return result;
    }
}