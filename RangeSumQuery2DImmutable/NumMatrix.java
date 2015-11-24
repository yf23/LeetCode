/* 
    Range Sum Query 2D - Immutable

	Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

	Example:
	Given matrix = [
	  [3, 0, 1, 4, 2],
	  [5, 6, 3, 2, 1],
	  [1, 2, 0, 1, 5],
	  [4, 1, 0, 1, 7],
	  [1, 0, 3, 0, 5]
	]

	sumRegion(2, 1, 4, 3) -> 8
	sumRegion(1, 1, 2, 2) -> 11
	sumRegion(1, 2, 2, 4) -> 12

	Note:
	You may assume that the matrix does not change.
	There are many calls to sumRegion function.
	You may assume that row1 ≤ row2 and col1 ≤ col2.

	Your NumMatrix object will be instantiated and called as such:
	NumMatrix numMatrix = new NumMatrix(matrix);
	numMatrix.sumRegion(0, 1, 2, 3);
	numMatrix.sumRegion(1, 2, 3, 4);
	 _____________
    |      |      |
    |  A1  |  A2  |
   	|______|______|
    |      |      |
    |  A3  |  A4  |
	|______|______|

	Build sumMatrix:
	sum(A1 + A2 + A3 + A4) = sum(A1 + A2) + sum(A1 + A3) - A1 + A4
	
	Caculate sumRegion:
	A4 = sum(A1 + A2 + A3 + A4) - sum(A1 + A2) - sum(A1 + A3) + A1
              
    Yu Fu, Nov 23 2015  
*/

public class NumMatrix {
    
    public int[][] sumMatrix;
    
    public NumMatrix(int[][] matrix) {
        int rowCount = matrix.length;
        if (rowCount == 0) {
            sumMatrix = null;
            return;
        }
        int colCount = matrix[0].length;
        sumMatrix = new int[rowCount + 1][colCount + 1];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                sumMatrix[row + 1][col + 1] = sumMatrix[row][col + 1] + sumMatrix[row + 1][col]
                                            - sumMatrix[row][col] + matrix[row][col];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (sumMatrix == null) return 0;
        return sumMatrix[row1][col1] + sumMatrix[row2 + 1][col2 + 1]
             - sumMatrix[row1][col2 + 1] - sumMatrix[row2 + 1][col1];
    }
}