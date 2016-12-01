/*
    Number Of Islands II

    A 2d grid map of m rows and n columns is initially filled with water.
    We may perform an addLand operation which turns the water at position (row, col) into a land.

    Given a list of positions to operate,
    count the number of islands after each addLand operation.

    An island is surrounded by water and is formed by
    connecting adjacent lands horizontally or vertically.

    You may assume all four edges of the grid are all surrounded by water.

    Example:

    Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
    Initially, the 2d grid grid is filled with water.
    (Assume 0 represents water and 1 represents land).

    0 0 0
    0 0 0
    0 0 0

    Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
    1 0 0
    0 0 0   Number of islands = 1
    0 0 0

    Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
    1 1 0
    0 0 0   Number of islands = 1
    0 0 0

    Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
    1 1 0
    0 0 1   Number of islands = 2
    0 0 0

    Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
    1 1 0
    0 0 1   Number of islands = 3
    0 1 0

    We return the result as an array: [1, 1, 2, 3]

    Challenge:
    Can you do it in time complexity O(k log mn), where k is the length of the positions?

    Solution:
    Maintain an union-find structure. When each time create a land,
    union it with neighbouring lands. Number of islands is number of
    roots in union-find structure.

    Yu Fu, 11/30/2016
 */
 
public class Solution {
    class UnionFind {
        int[] unionArr;
        int rootCount;
        
        public UnionFind(int size) {
            unionArr = new int[size];
            for (int i = 0; i < size; i++) unionArr[i] = -1;
            rootCount = 0;
        }
        
        public void addNode(int ind) {
            if (unionArr[ind] == -1) {
                unionArr[ind] = ind;
                rootCount++;
            }
        }
        
        public void union(int indNew, int indOld) {
            if (find(indNew) != find(indOld)) {
                unionArr[find(indNew)] = find(indOld);
                rootCount--;
            }
        }
        
        public int find(int ind) {
            while (unionArr[ind] != ind) {
                ind = unionArr[ind];
            }
            return unionArr[ind];
        }
        
        public int getRootCount() { return rootCount; }
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m * n);
        int[][] grid = new int[m][n];
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < positions.length; i++) {
            int y = positions[i][0];
            int x = positions[i][1];
            grid[y][x] = 1;
            
            uf.addNode(y * n + x);
            
            if (x > 0 && grid[y][x-1] == 1) uf.union(y * n + x, y * n + x - 1);
            if (x < n - 1 && grid[y][x+1] == 1) uf.union(y * n + x, y * n + x + 1);
            if (y > 0 && grid[y-1][x] == 1) uf.union(y * n + x, (y - 1) * n + x);
            if (y < m - 1 && grid[y+1][x] == 1) uf.union(y * n + x, (y + 1) * n + x);
            
            result.add(uf.getRootCount());
        }
        return result;
    }
}