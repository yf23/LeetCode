/*
    Pacific Atlantic Water Flow

    Given an m x n matrix of non-negative integers
    representing the height of each unit cell in a continent,
    the "Pacific ocean" touches the left and top edges of the matrix
    and the "Atlantic ocean" touches the right and bottom edges.

    Water can only flow in four directions (up, down, left, or right)
    from a cell to another one with height equal or lower.

    Find the list of grid coordinates where water
    can flow to both the Pacific and Atlantic ocean.

    Note:
    The order of returned grid coordinates does not matter.
    Both m and n are less than 150.
    Example:

    Given the following 5x5 matrix:

      Pacific ~   ~   ~   ~   ~ 
           ~  1   2   2   3  (5) *
           ~  3   2   3  (4) (4) *
           ~  2   4  (5)  3   1  *
           ~ (6) (7)  1   4   5  *
           ~ (5)  1   1   2   4  *
              *   *   *   *   * Atlantic

    Return:
    [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
    (positions with parentheses in above matrix).

    Solution:
    Fill two boolean arrays (which indicates if a grid can flow to ocean) with DFS.
    Starting with the edges of the ocean,
    and only goes to neighbours with height equal or higher. (Reverse of direction of water)

    Yu Fu, 12/01/2016
 */
 
public class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<int[]>();
        if (matrix.length == 0 || matrix[0].length == 0) return result;
        int m = matrix.length;
        int n = matrix[0].length;
        
        // DFS to search grids can flow to Pacific ocean.
        boolean[][] pacific = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(pacific, matrix, i, 0, m, n);
        }
        for (int j = 0; j < n; j++) {
            dfs(pacific, matrix, 0, j, m, n);
        }
        
        // DFS to search grids can flow to Atlantic ocean.
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(atlantic, matrix, i, n-1, m, n);
        }
        for (int j = 0; j < n; j++) {
            dfs(atlantic, matrix, m-1, j, m, n);
        }
        
        // If a grid can flow to both oceans, add it to result.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    int[] point = new int[2];
                    point[0] = i;
                    point[1] = j;
                    result.add(point);
                }
            }
        }
        return result;
    }
    
    // DFS to fill reachable grids in boolean array to true.
    // If a neighbour grid has equal or higher value, it is reachable.
    private void dfs(boolean[][] b, int[][] matrix, int i, int j, int m, int n) {
        if (!b[i][j]) {
            int cur = matrix[i][j];
            b[i][j] = true;
            if (i > 0 && matrix[i-1][j] >= cur) dfs(b, matrix, i - 1, j, m, n);
            if (i < m - 1 && matrix[i+1][j] >= cur) dfs(b, matrix, i + 1, j, m, n);
            if (j > 0 && matrix[i][j-1] >= cur) dfs(b, matrix, i, j - 1, m, n);
            if (j < n - 1 && matrix[i][j+1] >= cur) dfs(b, matrix, i, j + 1, m, n);
        }
    }
}
