/*
    Decode String

    Given an encoded string, return it's decoded string.

    The encoding rule is: k[encoded_string],
    where the encoded_string inside the square brackets is being repeated exactly k times.
    Note that k is guaranteed to be a positive integer.

    You may assume that the input string is always valid;
    No extra white spaces, square brackets are well-formed, etc.

    Furthermore, you may assume that the original data
    does not contain any digits and that digits are only for those repeat numbers, k.
    For example, there won't be input like 3a or 2[4].

    Examples:
    s = "3[a]2[bc]", return "aaabcbc".
    s = "3[a2[c]]", return "accaccacc".
    s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

    Solution:
    Recursively decode content in outer most bracket.

    Yu Fu, 10/18/2016
 */

public class Solution {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isEncoded = false;    // If in encoded part
        int repCount = 0;             // Repeat count
        int bracketCount = 0;         // Number of open brackets
        int start = -1;               // Start index of content inside outermost bracket
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            // If not in encoded part
            if (!isEncoded) {
                // If current char is a number, it's the 1st digit of repeat count.
                if (cur >= '0' && cur <= '9') {
                    repCount = cur - '0';
                    isEncoded = true;     // Set current in encoded part
                    start = i + 2;        // Set start index to be two chars after
                // Not a number, simply append to result.
                } else sb.append(cur);
            } else {
                // If current char is bracket, open or close brackets and update count.
                if (cur == '[') bracketCount++;
                if (cur == ']') bracketCount--;
                if (bracketCount == 0) {
                    // Another number before any brackets, the digit is also part of repeat count.
                    if (cur >= '0' && cur <= '9') { 
                        repCount = repCount * 10 + (cur - '0');
                        start++;   // Move start index 
                    // After all the brackets are closed,
                    // all the chars from start index to current index is
                    // content inside outermost brackets.
                    // Decode that part and add them repeatedly to result.
                    } else {
                        String content = decodeString(s.substring(start, i));
                        for (int j = 0; j < repCount; j++) sb.append(content);
                        repCount = 0;
                        isEncoded = false;   // Reset encode status
                    }
                }

            }
        }
        return sb.toString();
    }
}
