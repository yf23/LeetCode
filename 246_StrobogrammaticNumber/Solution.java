/*
    Strobogrammatic Number

    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

    Write a function to determine if a number is strobogrammatic.
    The number is represented as a string.

    For example, the numbers "69", "88", and "818" are all strobogrammatic.
    
    Solution:
    0, 1, 8 does not change.
    6 and 9 becomes each other.
    Also reverse the order of String.
    Other numbers are not possible.

    Yu Fu, 09/29/2016
 */

public class Solution {
    public boolean isStrobogrammatic(String num) {
        StringBuffer bf = new StringBuffer();
        for (int i = num.length() - 1; i >= 0; i--) {
            int dig = num.charAt(i) - '0';
            System.out.println(dig);
            if (dig == 0 || dig == 1 || dig == 8) bf.append(dig);
            else if (dig == 6) bf.append(9);
            else if (dig == 9) bf.append(6);
            else return false;
        }
        System.out.println(bf.toString());
        return bf.toString().equals(num);
    }
}