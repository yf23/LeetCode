/*
    Longest Consecutive Sequence

    Given an unsorted array of integers,
    find the length of the longest consecutive elements sequence.

    For example,
    Given [100, 4, 200, 1, 3, 2],
    The longest consecutive elements sequence is [1, 2, 3, 4].
    Return its length: 4.

    Your algorithm should run in O(n) complexity.

    Yu Fu, 10/02/2016
 */

public class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> s = new HashSet<Integer>();
        for (int n : nums) s.add(n);
        int max = 0;
        for (int n : s) {
            // Only search in postive direction to avoid repeat resarch.
            if (s.contains(n - 1)) continue;
            // Search for next consecutive element.
            // If present length + 1.
            int m = n + 1;
            while (s.contains(m)) m++;
            max = Math.max(m - n, max);
        }
        return max;
    }
}