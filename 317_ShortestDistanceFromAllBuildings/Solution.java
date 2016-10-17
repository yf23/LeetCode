/*
    Shortest Distance from All Buildings

    You want to build a house on an empty land
    which reaches all buildings in the shortest amount of distance.
    You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

    Each 0 marks an empty land which you can pass by freely.
    Each 1 marks a building which you cannot pass through.
    Each 2 marks an obstacle which you cannot pass through.
    For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

    1 - 0 - 2 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0
    The point (1,2) is an ideal empty land to build a house,
    as the total travel distance of 3+3+1=7 is minimal. So return 7.

    Note:
    There will be at least one building.
    If it is not possible to build such house according to the above rules, return -1.

    Solution:
    Use BFS starting from each building, add the distance to that budiling to results grid.
    Find the minimum value in results grid, if it is empty land and can reach all the buildings.
    To record if a empty space can reach all building, also keeps a counting grid which counts
    the number of buliding can reach that grid.

    Yu Fu, 10/16/2016
 */

public class Solution {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m][n];        // Sum of distances from reachable buildings
        int[][] reachCount = new int[m][n];    // Number of reachable buildings
        
        int buildingCount = 0;                 // Count of buildings
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, result, reachCount);
                    buildingCount++;
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Only update the value when
                // 1. empty space
                // 2. can reach all buildings
                if (grid[i][j] == 0 && reachCount[i][j] == buildingCount) {
                    min = Math.min(result[i][j], min);
                }
            }
        }
        // If cannot find minimum value, return -1.
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    // BFS to update results grid by distance to building at (i, j)
    private void bfs(int[][] grid, int i, int j, int[][] result, int[][] reachCount) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> bfsQ = new LinkedList<Integer>();
        HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
        bfsQ.add(i * n + j);
        visited.put(i * n + j, 0);
        while (!bfsQ.isEmpty()) {
            int ind = bfsQ.remove();
            List<Integer> neighbours = getNeighbours(ind / n, ind % n, m, n);
            for (int neighbour : neighbours) {
                if (!visited.containsKey(neighbour) && grid[neighbour / n][neighbour % n] == 0) {
                    bfsQ.add(neighbour);
                    visited.put(neighbour, visited.get(ind) + 1);
                    result[neighbour / n][neighbour % n] += visited.get(ind) + 1;
                    reachCount[neighbour / n][neighbour % n]++;
                }
            }
        }
    }
    
    // Get possible neighbours
    private List<Integer> getNeighbours(int i, int j, int m, int n) {
        List<Integer> neighbours = new ArrayList<Integer>();
        if (i - 1 >= 0) neighbours.add((i - 1) * n + j);
        if (i + 1 < m) neighbours.add((i + 1) * n + j);
        if (j - 1 >= 0) neighbours.add(i * n + (j - 1));
        if (j + 1 < n) neighbours.add(i * n + (j + 1));
        return neighbours;
    }
}