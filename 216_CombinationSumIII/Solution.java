/*
    Combination Sum III

    Find all possible combinations of k numbers that add up to a number n,
    given that only numbers from 1 to 9 can be used
    and each combination should be a unique set of numbers.

    Example 1:
    Input: k = 3, n = 7
    Output:
    [[1,2,4]]

    Example 2:
    Input: k = 3, n = 9
    Output:
    [[1,2,6], [1,3,5], [2,3,4]]
    
    Solution: Backtracking
    
    Yu Fu, 11/9/2016
 */
    
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), k, 0, n, 0);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> cur, int k, int sum, int n, int min) {
        if (cur.size() == k && sum == n) result.add(new ArrayList<Integer>(cur));
        if (cur.size() < k) {
            for (int i = min + 1; i < 10 && i <= n - sum; i++) {
                cur.add(i);
                helper(result, cur, k, sum + i, n, i);
                cur.remove(cur.size() - 1);
            }
        }
    }
}