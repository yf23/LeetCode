/*
    Range Sum Query 2D - Mutable

    Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by
    its upper left corner (row1, col1) and lower right corner (row2, col2).

    Example:
    Given matrix = [
      [3, 0, 1, 4, 2],
      [5, 6, 3, 2, 1],
      [1, 2, 0, 1, 5],
      [4, 1, 0, 1, 7],
      [1, 0, 3, 0, 5]
    ]

    sumRegion(2, 1, 4, 3) -> 8
    update(3, 2, 2)
    sumRegion(2, 1, 4, 3) -> 10

    Note:
    The matrix is only modifiable by the update function.
    You may assume the number of calls to update and sumRegion function is distributed evenly.
    You may assume that row1 ≤ row2 and col1 ≤ col2.

    Solution:
    Let sum(i, j) be sum of rectangle from (0, 0) to (i, j).
    If row1 = 0 and col1 = 0, return sum(row2, col2).
    If only row1 = 0, return sum(row2, col2) - sum(row2, col1 - 1) (No upper rectangles).
    If only col1 = 0, return sum(row2, col2) - sum(row1 - 1, col2) (No left rectangles).
    For other cases,
    return sum(row2, col2) - sum(row1 - 1, col2) - sum(row2, col1 - 1) + sum(row1 - 1, col1 - 1).
    The same as (S1 + S2 + S3 + S4) - (S1 + S2) - (S1 + S3) + S1 = S4
    ---------------
    |      |      |
    |  S1  |  S2  |
    |      |      |
    |------|------|
    |      |      |
    |  S3  |  S4  |   
    |      |      |
    ---------------

    When update value at (i, j), cacluate changed value,
    and add to all the values in rectagle start from (i, j) to the lower right corner.

    Yu Fu, 10/01/2016
 */

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);
public class NumMatrix {
    int[][] nums;
    int[][] sums;
    
    public NumMatrix(int[][] matrix) {
        nums = matrix;
        if (nums.length > 0 && nums[0].length > 0) {
            int m = nums.length;
            int n = nums[0].length;
            sums = new int[m][n];
            // Build a matrix such that sum[i][j] is the sum in rectangle (0, 0) to (i, j)
            sums[0][0] = nums[0][0];
            for (int i = 1; i < m; i++) sums[i][0] = sums[i - 1][0] + nums[i][0];
            for (int j = 1; j < n; j++) sums[0][j] = sums[0][j - 1] + nums[0][j];
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    sums[i][j] = sums[i - 1][j] + sums[i][j - 1] + nums[i][j] - sums[i - 1][j - 1];
                }
            }
        }
    }


    public void update(int row, int col, int val) {
        // Calcualte change
        int delta = val - nums[row][col];
        // Update origin matrix
        nums[row][col] = val;
        // Update sum matrix start from (i, j) to lower right corner
        for (int i = row; i < nums.length; i++) {
            for (int j = col; j < nums[0].length; j++) {
                sums[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // (0, 0) to (row2, col2)
        if (row1 == 0 && col1 == 0) return sum[row2][col2];
        // (0, col1) to (row2, col2) = (0, 0) to (row2, col2) - (0, 0) to (row2, col1 - 1)
        if (row1 == 0) return sum[row2][col2] - sum[row2][col1 - 1];
        // (row1, 0) to (row2, col2) = (0, 0) to (row2, col2) - (0, 0) to (row1 - 1, col2)
        if (col1 == 0) return sum[row2][col2] - sum[row1 - 1][col2];
        // See solution part above. Large - left - up + upperleft.
        return sum[row2][col2] - sum[row1-1][col2] - sum[row2][col1-1] + sum[row1-1][col1-1];
    }
}