/*
    Number of Islands

    Given a 2d grid map of '1's (land) and '0's (water),
    count the number of islands.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    11110
    11010
    11000
    00000
    Answer: 1

    Example 2:

    11000
    11000
    00100
    00011
    Answer: 3
    
    Solution:
    Loop through grid, when find an island.
    use DFS to sink all the island.
    Number of sink performed is the number of islands.

    Yu Fu, 09/30/2016
 */
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        // Loop through grid. If there is an island, sink it.
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    sink(grid, i, j);
                }
            }
        }
        return count;
    }
    
    // DFS to recursively sink island.
    // Sink the block in grid[i][j],
    // and recursively sink its neighbor if it's part of island. (neighbor with value '1')
    private void sink(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        sink(grid, i - 1, j);
        sink(grid, i + 1, j);
        sink(grid, i, j - 1);
        sink(grid, i, j + 1);
    }
}