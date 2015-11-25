'''
    Evaluate Reverse Polish Notation

    Evaluate the value of an arithmetic expression in Reverse Polish Notation.

    Valid operators are +, -, *, /. Each operand may be an integer or another expression.

    Some examples:
      ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
      ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

    Yu Fu, Nov 25 2015
'''

class Solution(object):
    def evalRPN(self, tokens):
        """
        :type tokens: List[str]
        :rtype: int
        """
        nums = []
        for s in tokens:
            if s.lstrip('-').isdigit():
                nums.append(float(s))
            else:
                nums.append(int(eval(str(nums.pop(-2)) + s + str(nums.pop(-1)))))
        return int(nums[0])
        