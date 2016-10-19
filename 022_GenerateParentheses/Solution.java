/*
    Generate Parentheses

    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    For example, given n = 3, a solution set is:

    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]
    
    Solution One:
    Use DFS (recursion) to search for result.
    Use HashSet to store result list to avoid duplication.
    Use HashMap to store route (parenthesis list of different length).
    Two ways to form parenthesis with n pairs:
    1. [k][n-k], k from 1 to (n - 1)
    2. ([n-1]), wrap a pair of parenthesis outside.

    Solution Two:
    If "(" is less than number of pairs, it's ok to add.
    If ")" is less than number of "("s, it's ok to add.
    Recursively add above two cases.

    Yu Fu, 10/18/2016
 */

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        HashMap<Integer, HashSet<String>> ht = new HashMap<Integer, HashSet<String>>();
        helper(n, ht);
        result.addAll(ht.get(n));
        return result;
    }
    
    public void helper(int n, HashMap<Integer, HashSet<String>> ht) {
        // Already exist, no need to search.
        if (ht.containsKey(n)) return;
        // Base cases.
        if (n == 0) ht.put(0, new HashSet<String>());
        if (n == 1) ht.put(1, new HashSet<String>(Arrays.asList("()")));
        else {
            HashSet<String> result = new HashSet<String>();
            // [k][n-k], k from 1 to (n - 1)
            for (int i = 1; i < n; i++) {
                if (!ht.containsKey(i)) helper(i, ht);
                if (!ht.containsKey(n - i)) helper(n - i, ht);
                for (String a : ht.get(i)) {
                    for (String b : ht.get(n - i)) {
                        result.add(a + b);
                    }
                }
            }
            // ([n-1]), wrap a pair of parenthesis outside.
            if (!ht.containsKey(n - 1)) helper(n - 1, ht);
            for (String s : ht.get(n - 1)) {
                result.add("(" + s + ")");
            }
            // Store new list into HashMap.
            ht.put(n, result);
        }
    } 
}

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        helper(n, "", 0, 0, result);
        return result;
    }
    
    public void helper(int n, String s, int open, int close, List<String> result) {
        // Valid solution, add to list.
        if (s.length() == 2 * n) {
            result.add(s);
            return;
        }
        // Number of "(" is less than number of pairs.
        if (open < n) helper(n, s + "(", open + 1, close, result);
        // Number of ")" is less than number of "("
        if (close < open) helper(n, s + ")", open, close + 1, result);
    } 
}
