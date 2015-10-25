'''
    Roman to Integer

    Given a roman numeral, convert it to an integer.

    Input is guaranteed to be within the range from 1 to 3999.
    
    Any of the letters representing numbers in the Roman numerical system:
    I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1,000.
    In this system, a letter placed after another of greater value adds,
    whereas a letter placed before another of greater value subtracts.

    Simple algorithm. Using dictionary to convert input String to number.

    Yu Fu, Oct 25 2015
'''

class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        # Empty Case
        if s == '': return 0
        
        # Create a dictionary that convert Roman digits to integer
        roman_dict = {
            'I' : 1,
            'V' : 5,
            'X' : 10,
            'L' : 50,
            'C' : 100,
            'D' : 500,
            'M' : 1000
        }
        
        # Apply addition and substraction
        total = roman_dict[s[-1]]    
        for i in range(len(s) - 1, 0, -1):
            curr = roman_dict[s[i]]
            prev = roman_dict[s[i-1]]
            if prev >= curr: total += prev
            else:            total -= prev
            
        return total
        