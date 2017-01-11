/*
    Magical String

    A magical string S consists of only '1' and '2' and obeys the following rules:

    The string S is magical because concatenating the number of contiguous occurrences of
    characters '1' and '2' generates the string S itself.

    The first few elements of string S is the following: S = "1221121221221121122……"

    If we group the consecutive '1's and '2's in S, it will be:

    1 22 11 2 1 22 1 22 11 2 11 22 ......

    and the occurrences of '1's or '2's in each group are:

    1 2 2 1 1 2 1 2 2 1 2 2 ......

    You can see that the occurrence sequence above is the S itself.

    Given an integer N as input, return the number of '1's in the first N number in the magical string S.

    Note: N will not exceed 100,000.

    Example 1:
    Input: 6
    Output: 3
    Explanation: The first 6 elements of magical string S is "122112"
                and it contains three 1's, so return 3.

    Solution:
    Create an int array ms and initialize the first 3 elements with 1, 2, 2.
    Create two pointers head and tail.
    head points to the number which will be used to generate new numbers.
    tail points to the next empty position to put the new number.
    Then keep generating new numbers until tail >= n.
    Need to create the array 1 element more than n to avoid overflow
    because the last round head might points to a number 2.
    A trick to flip number back and forth between 1 and 2: num = num ^ 3
 */

public class Solution {
    public int magicalString(int n) {
        if (n == 0) return 0;
        if (n < 3) return 1;
        
        // head: number used to generate new number
        // tail: new number
        // num: current number
        // result: count of 1's
        int[] ms = new int[n + 1];
        ms[0] = 1; ms[1] = 2; ms[2] = 2;
        int head = 2, tail = 3, num = 1, result = 1;
        
        while (tail < n) {
            // New number repeating for ms[head] times
            for (int i = 0; i < ms[head]; i++) {
                if (num == 1 && tail < n) result++;   // Only add 1's in range
                ms[tail++] = num;                     // Generate new number
            }
            num ^= 3;   // Swap between 1 and 2
            head++;     // Use next number to generate
        }
        
        return result;
    }
}
