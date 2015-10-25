/*
    Roman to Integer

    Given a roman numeral, convert it to an integer.

    Input is guaranteed to be within the range from 1 to 3999.
    
    Any of the letters representing numbers in the Roman numerical system:
    I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1,000.
    In this system, a letter placed after another of greater value adds,
    whereas a letter placed before another of greater value subtracts.

    Simple algorithm. Using case switch converting input String to number.

    Yu Fu, Oct 25 2015
*/

public class Solution {
    public int romanToInt(String s) {
        
        // Get string length, and convert to upper case.
        int length = s.length();
        s = s.toUpperCase();
        
        // Create an integer value array of each digit of input roman number.
        int[] intArr = new int[length];
        for (int i = 0; i < length; i++) {
            switch (s.charAt(i)) {
                default : intArr[i] = 0; break;
                case 'I': intArr[i] = 1; break;
                case 'V': intArr[i] = 5; break;
                case 'X': intArr[i] = 10; break;
                case 'L': intArr[i] = 50; break;
                case 'C': intArr[i] = 100; break;
                case 'D': intArr[i] = 500; break;
                case 'M': intArr[i] = 1000; break;
            }
        }
        
        // Apply the addition and substraction.
        int total = intArr[length - 1];
        for (int i = length - 1; i > 0; i--) {
            int curr = intArr[i];
            int prev = intArr[i - 1];
            if (prev >= curr) {
                total += prev;
            } else {
                total -= prev;
            }
        }

        return total;
    }
}