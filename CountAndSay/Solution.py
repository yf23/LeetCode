'''
    Count And Say
    The count-and-say sequence is the sequence of integers beginning as follows:
    1, 11, 21, 1211, 111221, ...
   
    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.
    Given an integer n, generate the nth sequence.

    Yu Fu, Oct 23 2015  
'''

class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        num = "1"
        for i in range(n-1):
            result = ""
            temp = num[0]
            count = 1
            for i in range(len(num) - 1):
                next = num[i+1]
                if temp == next:
                    count += 1
                else:
                    result += str(count)
                    result += temp
                    temp = next
                    count = 1
            result += str(count)
            result += temp
            num = result
        return num
