/*
    H-Index

    Given an array of citations (each citation is a non-negative integer) of a researcher,
    write a function to compute the researcher's h-index.

    According to the definition of h-index on Wikipedia:
    "A scientist has index h if h of his/her N papers have at least h citations each,
     and the other N − h papers have no more than h citations each."

    For example, given citations = [3, 0, 6, 1, 5],
    which means the researcher has 5 papers in total
    and each of them had received 3, 0, 6, 1, 5 citations respectively.
    Since the researcher has 3 papers with at least 3 citations each
    and the remaining two with no more than 3 citations each, his h-index is 3.

    Note: If there are several possible values for h, the maximum one is taken as the h-index.

    Hint:
    An easy approach is to sort the array first.
    What are the possible values of h-index?
    A faster approach is to use extra space.

    Solution:
    Use a HashMap to store the number of papers m with k citations in pair (k, m).
    Since we can only have h-index no larger than number of papers,
    if we have n papers, and k > n, we will treat it as paper with n citations.

    After we construct the HashMap, start from highest citation number,
    to get the number of papers has citations larger than k', let the number be s.
    If s >= k', then k' is h-index.
    We have k' papers have at least k' citations each,
    and the other n-k' papers have no more than k' citations each.

    Yu Fu, 10/04/16
 */

public class Solution {
    public int hIndex(int[] citations) {
        // A array act as a HashTable.
        // One can not has h-index higher than number of papers n,
        // so the entry ht[k] represents number of papers has k citations.
        // If a paper has citations more than n, it is counted as cited n times (in ht[n]).
        int n = citations.length;
        int[] ht = new int[n + 1];
        for (int k : citations) {
            if (k > n) ht[n]++;
            else ht[k]++;
        }
        
        // Count number of papers from right.
        // Count is the number of papers has at least i citations.
        // If such count is larger or equal to i,
        // it is the h-index number.
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += ht[i];
            if (count >= i) return i;
        }
        return 0;
    }
}