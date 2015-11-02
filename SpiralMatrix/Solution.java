/*
    Spiral Matrix

    Given a matrix of m x n elements (m rows, n columns),
    return all elements of the matrix in spiral order.

    For example,
    Given the following matrix:

    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]

    You should return [1,2,3,6,9,8,7,4,5].

    Java solution using recursion. Turn the matrix counter-clockwise each step of the recursion.

    Yu Fu, Nov 1 2015
*/

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Base Case: If empty or null martrix, return empty list.
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        // Add first row to the list
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < col; i++) {
            result.add(matrix[0][i]);
        }

        // Turn the matrix
        int[][] turnedMatrix = new int[col][row - 1];
        int turnedCol = 0;
        for (int i = 1; i < row; i++) {
            int turnedRow = 0;
            for (int j = col - 1; j >= 0; j--) {
                turnedMatrix[turnedRow][turnedCol] = matrix[i][j];
                turnedRow++;
            }
            turnedCol++;
        }

        // Recursively output the spiral order of turned matrix
        result.addAll(spiralOrder(turnedMatrix));
        return result;
    }
}