/*
    Walls and Gates

    You are given a m x n 2D grid initialized with these three possible values.

    -1  - A wall or an obstacle.
     0  - A gate.
    INF - Infinity means an empty room. 

    We use the value 231 - 1 = 2147483647 to represent INF
    as you may assume that the distance to a gate is less than 2147483647.

    Fill each empty room with the distance to its nearest gate.
    If it is impossible to reach a gate, it should be filled with INF.

    For example, given the 2D grid:
    INF  -1  0  INF
    INF INF INF  -1
    INF  -1 INF  -1
      0  -1 INF INF

    After running your function, the 2D grid should be:
      3  -1   0   1
      2   2   1  -1
      1  -1   2  -1
      0  -1   3   4

    Solution:
    Start with each gate, use DFS to update distance to that gate,
    if the distance is shorter than current assigned distance in that empty room.
    
    Yu Fu, 10/10/2016
 */

public class Solution {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) fill(rooms, 0, i, j);
            }
        }
    }
    
    // DFS to update distance to the closest gate so far.    
    public void fill(int[][] rooms, int val, int i, int j) {
        if (rooms[i][j] != -1 && rooms[i][j] >= val) {
            rooms[i][j] = val;
            int m = rooms.length;
            int n = rooms[0].length;
            if (i - 1 >= 0) fill(rooms, val + 1, i - 1, j);
            if (i + 1 < m) fill(rooms, val + 1, i + 1, j);
            if (j - 1 >= 0) fill(rooms, val + 1, i, j - 1);
            if (j + 1 < n) fill(rooms, val + 1, i, j + 1);
        }
    }
}