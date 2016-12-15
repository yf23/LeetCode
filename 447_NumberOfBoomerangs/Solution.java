/*
    Number of Boomerangs

    Given n points in the plane that are all pairwise distinct,
    a "boomerang" is a tuple of points (i, j, k) such that
    the distance between i and j equals the distance between i and k
    (the order of the tuple matters).

    Find the number of boomerangs. You may assume that n will be at most 500
    and coordinates of points are all in the range [-10000, 10000] (inclusive).

    Example:
    Input:
    [[0,0],[1,0],[2,0]]

    Output:
    2

    Explanation:
    The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

    Solution:
    Calculate the distances between points.
    For each point, build a HashMap such that the distance is key,
    a list of indices of points with such distance as value.
    Every time adding a indices, there are 2 * size(list) (before adding) more boomerangs.
    As new points can form 2 more new boomerangs with each point already in the list.

    Yu Fu, 12/14/2016
 */

 public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ptNum = points.length;
        
        // Calculate all the distances.
        double[][] distances = new double[ptNum][ptNum];
        for (int i = 0; i < ptNum; i++) {
            for (int j = 0; j < ptNum; j++) {
                if (distances[i][j] == 0.0) {
                    double dis = getDistance(points[i], points[j]);
                    distances[i][j] = dis;
                    distances[j][i] = dis;
                }
            }
        }
        
        // For each points find boomerangs
        int count = 0;
        for (int i = 0; i < ptNum; i++) {
            Map<Double, List<Integer>> map = new HashMap<Double, List<Integer>>();
            for (int j = 0; j < ptNum; j++) {
                double dis = distances[i][j];
                if (!map.containsKey(dis)) map.put(dis, new ArrayList<Integer>());
                count += map.get(dis).size() * 2;
                map.get(dis).add(j); 
            }
        }

        return count;
    }
    
    public double getDistance(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}