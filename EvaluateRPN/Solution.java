/*
    Evaluate Reverse Polish Notation

    Evaluate the value of an arithmetic expression in Reverse Polish Notation.

    Valid operators are +, -, *, /. Each operand may be an integer or another expression.

    Some examples:
      ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
      ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

    Yu Fu, Nov 25 2015
 */

import java.util.Stack;

public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<Integer>();
        for (String s : tokens) {
            switch (s) {
                case "+":
                    nums.push(nums.pop() + nums.pop());
                    break;
                case "-":
                    nums.push(-nums.pop() + nums.pop());
                    break;
                case "*":
                    nums.push(nums.pop() * nums.pop());
                    break;
                case "/": 
                    int num1 = nums.pop();
                    int num2 = nums.pop();
                    nums.push(num2 / num1);
                    break;
                default:
                    nums.push(Integer.parseInt(s));
                    break;
            }
        }
        return nums.pop();
    }
}