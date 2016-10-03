/* 
    Evaluate Reverse Polish Notation

    Evaluate the value of an arithmetic expression in Reverse Polish Notation.

    Valid operators are +, -, *, /.
    Each operand may be an integer or another expression.

    Some examples:
      ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
      ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
    
    Solution:
    When reads a number, push into stack.
    When reads an operator,
    pop two numbers to apply operator, first popped one is second arg of operator.
    Then push result back into stack.
        pop() -> a
        pop() -> b
        push(operator(b, a))
    After loop through the input, pop to get final result.

    Yu Fu, 09/30/2016
 */

public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<Integer>();
        for (String s : tokens) {
            // If operator, push(operator(second popped, first popped)).
            if (s.equals("+")) st.push(st.pop() + st.pop());
            else if (s.equals("*")) st.push(st.pop() * st.pop());
            else if (s.equals("-")) {
                int back = st.pop();
                int front = st.pop();
                st.push(front - back);
            } else if (s.equals("/")) {
                int back = st.pop();
                int front = st.pop();
                st.push(front / back);
            // If number, push(num)            
            } else {
                st.push(Integer.parseInt(s));
            }
        }
        // Pop last value as result.
        return st.pop();
    }
}