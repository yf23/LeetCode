/*
    Additive Number

    Additive number is a string whose digits can form additive sequence.

    A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

    For example:
    "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
    1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

    "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
    1 + 99 = 100, 99 + 100 = 199

    Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

    Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
    
    Yu Fu, 09/27/2016
 */

public class Solution {
    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) return false;

        // Length of first number (if 0, only one digit allowed)
        for (int lengthOne = 1; lengthOne < ((num.charAt(0) == '0') ? 2 : num.length()); lengthOne++) {
            // Length of second number (if 0, only one digit allowed)
            for (int lengthTwo = 1; lengthTwo < ((num.charAt(lengthOne) == '0') ? 2 : num.length() - lengthOne); lengthTwo++) {
                long one = Long.parseLong(num.substring(0, lengthOne));
                long two = Long.parseLong(num.substring(lengthOne, lengthOne + lengthTwo));
                String result = one + two + "";
                int lengthAddSeq = lengthOne + lengthTwo + result.length();    // Length of the first of additive sequence
                
                if (lengthAddSeq > num.length()) break; // If exceeds length of num, invalid.

                // Check the calculated sum is the same with the number in num string.
                if (num.substring(lengthOne + lengthTwo, lengthAddSeq).equals(result)) {
                    // The additive sequence either ends or the rest parts will be examinated.
                    if (lengthAddSeq == num.length() || isAdditiveNumber(num.substring(lengthOne, num.length()))) return true;
                }
            }
        }
        return false;
    }
}