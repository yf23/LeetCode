/*
    Elimination Game

    There is a list of sorted integers from 1 to n.
    Starting from left to right,
    remove the first number and every other number afterward until you reach the end of the list.

    Repeat the previous step again, but this time from right to left,
    remove the right most number and every other number from the remaining numbers.

    We keep repeating the steps again, alternating left to right and right to left,
    until a single number remains.

    Find the last number that remains starting with a list of length n.

    Example:

    Input:
    n = 9,
    1 2 3 4 5 6 7 8 9
    2 4 6 8
    2 6
    6

    Output:
    6

    Solution:
    Set a step variable starting with 1, each time doubles.
    If elimination starts from left,
    or elimination starts from right but there are odd numbers of remaining numbers,
    the head will be elimated. The new head will move to right by step.

    Yu Fu, 11/30/2016
 */

public class Solution {
    public int lastRemaining(int n) {
        int head = 1;
        int step = 1;
        int remaining = n;
        boolean fromLeft = true;
        
        while (remaining > 1) {
            // From left, or odd numbers of remaining numbers, move head.
            if (fromLeft || remaining % 2 == 1) head += step;
            step *= 2;             // Double step size each time.
            remaining /= 2;        // The number get halved each time.
            fromLeft = !fromLeft;  // Alternate from left and right.
        }
        return head;
    }
}