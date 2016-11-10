/*
    Combination Sum II

    Given a collection of candidate numbers (C) and a target number (T),
    find all unique combinations in C where the candidate numbers sums to T.

    Each number in C may only be used once in the combination.

    Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

    For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
    A solution set is: 
    
    [
      [1, 7],
      [1, 2, 5],
      [2, 6],
      [1, 1, 6]
    ]
    
    Solution: Backtracking
    
    Yu Fu, 11/9/2016
 */

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        helper(result, new ArrayList<Integer>(), candidates, 0, target, 0);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> cur, int[] candidates, int sum, int target, int minIdx) {
        if (sum == target) result.add(new ArrayList<Integer>(cur));
        if (sum < target) {
            for (int i = minIdx; i < candidates.length; i++) {
                if (i > minIdx && candidates[i] == candidates[i - 1]) continue;  // Prevent duplication
                int n = candidates[i];
                cur.add(n);
                helper(result, cur, candidates, sum + n, target, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }
}