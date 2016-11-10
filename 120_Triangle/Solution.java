/*
    Triangle

    Given a triangle, find the minimum path sum from top to bottom.
    Each step you may move to adjacent numbers on the row below.

    For example, given the following triangle
    [
         [2],
        [3,4],
       [6,5,7],
      [4,1,8,3]
    ]
    The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

    Solution:
    From buttom to top.
    Starting from 2nd row from bottom,
    increase with smaller of two neighbouring elements from next row.

    Yu Fu, 11/01/2016
 */

public class Solution {   
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int i = 0; i < triangle.get(row).size(); i++) {
                int num = triangle.get(row).get(i);
                int add = Math.min(triangle.get(row + 1).get(i), triangle.get(row + 1).get(i + 1));
                triangle.get(row).set(i, num + add);
            }
        }
        return triangle.get(0).get(0);
    }
}