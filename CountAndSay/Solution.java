/* 
    Count And Say
    The count-and-say sequence is the sequence of integers beginning as follows:
    1, 11, 21, 1211, 111221, ...
   
    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.
    Given an integer n, generate the nth sequence.

    Yu Fu, Oct 23 2015  
*/

public class Solution {
    public String countAndSay(int n) {
        String output = "1";
        for (int i = 1; i < n; i++) {
            output = countHelper(output);
        }
        return output;
    }
    
    private String countHelper(String num) {
        String result = "";
        char temp = num.charAt(0);
        int count = 1;
        for (int i = 1; i < num.length(); i++) {
            char next = num.charAt(i);
            if (temp != next) {
                result += (count + "");
                result += temp;
                temp = next;
                count = 1;
            } else {
                count++;
            }
        }
        result += (count + "");
        result += temp;
        return result;
    }
}