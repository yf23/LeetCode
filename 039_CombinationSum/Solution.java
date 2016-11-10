/*
    Combination Sum

    Given a set of candidate numbers (C) and a target number (T),
    find all unique combinations in C where the candidate numbers sums to T.

    The same repeated number may be chosen from C unlimited number of times.

    Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.
    For example, given candidate set [2, 3, 6, 7] and target 7, 

    A solution set is: 
    [
      [7],
      [2, 2, 3]
    ]
    
    Solution: Backtracking
    
    Yu Fu, 11/9/2016
 */

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        helper(result, new ArrayList<Integer>(), candidates, 0, target, candidates[0]);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> cur, int[] candidates, int sum, int target, int min) {
        if (sum == target) result.add(new ArrayList<Integer>(cur));
        if (sum < target) {
            for (int n : candidates) {
                if (n >= min) {
                    cur.add(n);
                    helper(result, cur, candidates, sum + n, target, n);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}