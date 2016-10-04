/*
    Fraction to Recurring Decimal

    Given two integers representing the numerator and denominator of a fraction,
    return the fraction in string format.

    If the fractional part is repeating, enclose the repeating part in parentheses.

    For example,
    Given numerator = 1, denominator = 2, return "0.5".
    Given numerator = 2, denominator = 1, return "2".
    Given numerator = 2, denominator = 3, return "0.(6)".

    Hint:
    No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
    Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
    Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.

    Solution:
    For decimal part, each loop multiplicates numerator by 10.
    Put numerator and the index of its produced digit into HashMap.
    If repeating numerator, it is the start of recurring decimal.
    Add parenthese before the digit produced by the numerator.

    Also For potential overflow error, change input to long format.

    Yu Fu, 10/04/16
 */

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // 0 / N = 0
        if (numerator == 0) return "0";
        
        // sign
        long numer = Math.abs((long) numerator);
        long denom = Math.abs((long) denominator);
        boolean isNeg = (numerator / numer + denom / denominator == 0);
        
        // Interger part
        String intPart = numer / denom + "";
        numer %= denom;
        
        // Decimal part
        HashMap<Long, Integer> mp = new HashMap<Long, Integer>();
        mp.put(numer, 0);
        StringBuilder dec = new StringBuilder();
        // If numerator becomes 0, the division ends.
        while (numer != 0) {
            numer *= 10;
            dec.append(numer / denom);
            numer %= denom;
            // Start repeat pattern if same numerator has been used before.
            if (mp.containsKey(numer)) {
                // Insert left parenthese such that it appears
                // before the digit produced by repeating numerator.
                dec.insert((int) mp.get(numer), '(');
                // Append right parenthese at end and break.
                dec.append(')');
                break;
            // If hasn't seen repeat, store its index.
            } else mp.put(numer, dec.length());
        }
        
        // Put together
        StringBuilder result = new StringBuilder();
        if (isNeg) result.append('-');
        result.append(intPart);
        // If there is decimal part, append digital point and decimal part.
        if (dec.length() > 0) {
            result.append('.');
            result.append(dec);
        }
        return result.toString();
    }
}