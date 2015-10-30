/* 
    Add Digits

    Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.    

    For example:    

    Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.  

    Follow up:
    Could you do it without any loop/recursion in O(1) runtime? 

    Hint:   
    A naive implementation of the above process is trivial. Could you come up with other methods?
    What are all the possible results?
    How do they occur, periodically or randomly?
    You may find this Wikipedia article useful. https://en.wikipedia.org/wiki/Digital_root
    
    dr(n) = 0         if n = 0
          = 9         if n =/ 0 and n = 0 mod 9
          = n mod 9   if n =/ 0 mod 9
          
    Yu Fu, Oct 23 2015  
*/

public class Solution {
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        int a = num % 9;
        if (a == 0) {
            return 9;
        } else {
            return a;
        }
    }
}