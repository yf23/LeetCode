/*
    Convert a Number to Hexadecimal

    Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

    Note:
    All letters in hexadecimal (a-f) must be in lowercase.
    The hexadecimal string must not contain extra leading 0s.
    If the number is zero, it is represented by a single zero character '0';
    otherwise, the first character in the hexadecimal string will not be the zero character.
    The given number is guaranteed to fit within the range of a 32-bit signed integer.
    You must not use any method provided by the library which converts/formats the number to hex directly.

    Example 1:
    Input:
    26
    Output:
    "1a"

    Example 2:
    Input:
    -1
    Output:
    "ffffffff"

    Yu Fu, 09/29/2016
 */
    
public class Solution {
    public String toHex(int num) {
        // Speical case when num is 0.
        if (num == 0) return "0";
        
        // Calculate the hex representation of abs of num.
        // Store in an integer array.
        int[] result = new int[8];
        int n = Math.abs(num);
        int ind = 7;
        while (n != 0) {
            result[ind--] = n % 16;
            n /= 16;
        }
        
        // If num is negative, perform 2's complement method.
        // Filp each digit (15 - digit) then add 1.
        if (num < 0) {
            for (int i = 0; i < 8; i++) {
                result[i] = 15 - result[i];
            }
            int carry = 1;
            for (int i = 7; i >= 0; i--) {
                result[i] += carry;
                carry = result[i] / 16;
                result[i] %= 16;
            }
        }
        
        // Translate from integer to String.
        // Omit leading zeros.
        char[] map = "0123456789abcdef".toCharArray();
        StringBuffer bf = new StringBuffer();
        boolean start = false;
        for (int i = 0; i < 8; i++) {
            if (result[i] != 0) start = true;
            if (start) bf.append(map[result[i]]);
        }
        return bf.toString();
    }
}