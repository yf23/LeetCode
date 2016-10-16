/*
    Spiral Matrix II

    Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

    For example,
    Given n = 3,

    You should return the following matrix:
    [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
    ]
    
    Use an count integer (0, 1, 2, 3) to indicate the output position:
    upper, left, lower, right

    Yu Fu, 10/16/2016
 */

public class Solution {
    public int[][] generateMatrix(int n) {
        if (n == 0) return new int[0][0];
        int[][] result = new int[n][n];
        int num = 1;
        
        int count = 0;
        int upper = 0;
        int lower = n - 1;
        int left = 0;
        int right = n - 1;
        while (num <= n * n) {
            if (count == 0) {
                for (int i = left; i <= right; i++) {
                    result[upper][i] = num++;
                }
                upper++;
            } else if (count == 1) {
                for (int i = upper; i <= lower; i++) {
                    result[i][right] = num++;
                }
                right--;
            } else if (count == 2) {
                for (int i = right; i >= left; i--) {
                    result[lower][i] = num++;
                }
                lower--;
            } else {
                for (int i = lower; i >= upper; i--) {
                    result[i][left] = num++;
                }
                left++;
            }
            count = (count + 1) % 4;
        }
        return result;
    } 
}