/*
    Spiral Matrix

    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

    For example,
    Given the following matrix:

    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    You should return [1,2,3,6,9,8,7,4,5].
    
    Use an count integer (0, 1, 2, 3) to indicate the output place:
    upper, left, lower, right

    Yu Fu, 10/16/2016
 */

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix.length == 0 || matrix[0].length == 0) return result;
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        int upper = 0;
        int lower = m - 1;
        int left = 0;
        int right = n - 1;
        while (upper <= lower && left <= right) {
            if (count == 0) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[upper][i]);
                }
                upper++;
            } else if (count == 1) {
                for (int i = upper; i <= lower; i++) {
                    result.add(matrix[i][right]);
                }
                right--;
            } else if (count == 2) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[lower][i]);
                }
                lower--;
            } else {
                for (int i = lower; i >= upper; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
            count = (count + 1) % 4;
        }
        return result;
    }
}