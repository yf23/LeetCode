/*
    Largest Number

    Given a list of non negative integers, arrange them such that they form the largest number.

    For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

    Note: The result may be very large, so you need to return a string instead of an integer.

    Solution:
    Convert all integers into Strings,
    for each pair a and b, if (a + b) > (b + a), then a should appear earlier than b.

    Yu Fu, 11/30/2016
 */

public class Solution {
    public String largestNumber(int[] nums) {
        String[] numStr = new String[nums.length];
        boolean allZero = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) allZero = false;
            numStr[i] = nums[i] + "";
        }
        if (allZero) return "0";
        
        Arrays.sort(numStr, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String n : numStr) sb.append(n);
        return sb.toString();
    }
}