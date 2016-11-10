/*
    Combinations

    Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

    For example,
    If n = 4 and k = 2, a solution is:

    [
      [2,4],
      [3,4],
      [2,3],
      [1,2],
      [1,3],
      [1,4],
    ]
    
    Solution: Backtracking
    
    Yu Fu, 11/9/2016
 */

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), k, n, 1);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> cur, int k, int n, int min) {
        if (cur.size() == k) result.add(new ArrayList<Integer>(cur));
        if (cur.size() < k) {
            for (int i = min; i <= n; i++) {
                cur.add(i);
                helper(result, cur, k, n, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }
}