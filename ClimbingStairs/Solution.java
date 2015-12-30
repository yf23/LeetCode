/*
    Climbing Stairs

	You are climbing a stair case. It takes n steps to reach to the top.

	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

	n = 1, number of ways = 1
	n = 2, number of ways = 2
	For larger n, consider number of ways to climb to (n-1) and (n-2) steps, denoted as f(n-1) and f(n-2)
	From (n-1) point, climb one step to reach n.
	From (n-2) point, climb two steps to reach n.
	Therefore number of ways to climb to n steps is f(n) = f(n-1) + f(n-2).
	It is a Fibonacci number.

	Recursive solution exceeds time limit. Use iteration.
    
    Yu Fu, Dec 30 2015
 */
public class Solution {
    public int climbStairs(int n) {
        int[] arr = {1, 2};
        for (int i = 2; i < n; i++) {
            arr[i % 2] = arr[0] + arr[1];
        }
        return arr[(n - 1) % 2];
    }
}