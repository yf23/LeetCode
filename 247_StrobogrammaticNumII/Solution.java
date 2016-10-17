/*
    Strobogrammatic Number II

    A strobogrammatic number is a number
    that looks the same when rotated 180 degrees (looked at upside down).

    Find all strobogrammatic numbers that are of length = n.

    For example,
    Given n = 2, return ["11","69","88","96"].

    Hint:
    Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.

    Yu Fu, 10/16/2016
 */

public class Solution {
    public List<String> findStrobogrammatic(int n) {
        if (n % 2 == 0) return helper(n, new ArrayList<String>(Arrays.asList("")));
        // If length is odd, add single strobogrammatic in the middle position.
        else return helper(n - 1, new ArrayList<String>(Arrays.asList("0", "1", "8")));
    }
    
    public List<String> helper(int n, List<String> prev) {
        List<String> result = new ArrayList<String>();
        // Base case:
        // Exclude all the numbers that starts with 0 and return result.
        if (n == 0) {
            for (String s : prev) {
                if (s.length() == 1 || s.charAt(0) != '0') result.add(s);
            }
            return result;
        // Add strobogrammatic pairs on the start and end of previous strings.
        // Recursion with n - 2.
        } else {
            for (String s : prev) {
                result.add("1" + s + "1");
                result.add("6" + s + "9");
                result.add("8" + s + "8");
                result.add("9" + s + "6");
                result.add("0" + s + "0");
            }
            return helper(n - 2, result);
        }
    }
}