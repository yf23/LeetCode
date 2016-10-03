/*
    Gray Code

    The gray code is a binary numeral system where two successive values differ in only one bit.

    Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
    A gray code sequence must begin with 0.

    For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
    00 - 0
    01 - 1
    11 - 3
    10 - 2
    
    Solution:
    If gray code with length (n - 1) is [a_0, a_1, ..., a_(k-1), a_k]
    Then gray code with length n is
    0 a_0
    0 a_1
    ...
    0 a_(k-1)
    0 a_k
    1 a_k        (= 2^(n-1) + a_k)
    1 a_(k-1)
    ...
    1 a_1
    1 a_0

    Yu Fu, 09/27/2016
 */

public class Solution {
    public List<Integer> grayCode(int n) {
        // Base case: if n = 0, only return list contains 0.
        if (n == 0) return new ArrayList<Integer>(Arrays.asList(0));

        // Get grayCode with one less length.
        // Add them to result list in order.
        // Then add each number + 2 ^ (n - 1) in reverse order.
        List<Integer> prevResult = grayCode(n - 1);
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < prevResult.size(); i++) result.add(prevResult.get(i));
        int adder = (int) Math.pow(2, n - 1);
        for (int i = prevResult.size() - 1; i >= 0; i--) result.add(prevResult.get(i) + adder);
        return result;
    }
}