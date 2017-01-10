/*
    Range Addition

    Assume you have an array of length n initialized with all 0's and are given k update operations.

    Each operation is represented as a triplet: [startIndex, endIndex, inc]
    which increments each element of subarray A[startIndex ... endIndex]
    (startIndex and endIndex inclusive) with inc.

    Return the modified array after all k operations were executed.

    Example:

    Given:

        length = 5,
        updates = [
            [1,  3,  2],
            [2,  4,  3],
            [0,  2, -2]
        ]

    Output:
        [-2, 0, 3, 5, 3]

    Explanation:
    Initial state:
    [ 0, 0, 0, 0, 0 ]

    After applying operation [1, 3, 2]:
    [ 0, 2, 2, 2, 0 ]

    After applying operation [2, 4, 3]:
    [ 0, 2, 5, 5, 3 ]

    After applying operation [0, 2, -2]:
    [-2, 0, 3, 5, 3 ]

    Hint:
    Thinking of using advanced data structures? You are thinking it too complicated.
    For each update operation, do you really need to update all elements between i and j?
    Update only the first and end element is sufficient.
    The optimal time complexity is O(k + n) and uses O(1) extra space.

    Solution:
    Save an array of difference with previous element.
    When updating, suppose increasing form i to j (both inclusive) by m,
    Add m to A[i], means increase by m from previous element.
    Remove m from A[j + 1], means decrease by m from previous element.
    Example:
            A                 Result
     0  0  0  0  0  0
     0  2  0  0 -2  0  =>  0  2  2  2  0
     0  2  3  0 -2 -3  =>  0  2  5  5  3
    -2  2  3  2 -2 -3  => -2  0  3  5  3 

    Yu Fu, Jan/9/2017
 */

public class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        int[] a = new int[length + 1];
        
        for (int[] update : updates) {
            a[update[0]] += update[2];
            a[update[1] + 1] += -1 * update[2];
        }
        
        result[0] = a[0];
        for (int i = 1; i < length; i++) {
            result[i] = a[i] + result[i - 1];
        }
        
        return result; 
    }
}