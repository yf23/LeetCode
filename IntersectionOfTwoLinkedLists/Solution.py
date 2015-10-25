'''
    Write a program to find the node at which the intersection of two singly linked lists begins.

    For example, the following two linked lists:
    
    A:          a1 → a2
                    ↘
                        c1 → c2 → c3
                    ↗            
    B:     b1 → b2 → b3
    begin to intersect at node c1.
    
    Notes:
    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) time and use only O(1) memory.

    Credits:
    Special thanks to @stellari for adding this problem and creating all test cases.

    Definition for singly-linked list.
    class ListNode(object):
        def __init__(self, x):
            self.val = x
            self.next = None

    First, get length of two linked list by trasverse through both lists.
    Then, search the part with same length for intersection.
    Skip the starting parts of the longer linked list.

    O(m+n) running time, O(1) space. 380ms Python solution.
    
    Yu Fu, Oct 25 2015
'''

class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        # Null Case
        if not headA or not headB: return None
        
        # Count length of both list.
        tempA = headA
        tempB = headB
        lenA = 0
        lenB = 0
        while tempA:
            lenA += 1
            tempA = tempA.next
        while tempB:
            lenB += 1
            tempB = tempB.next
        
        # Skip starting part of longer list.
        tempA = headA if (lenA > lenB) else headB
        tempB = headB if (lenA > lenB) else headA
        for i in range(abs(lenA - lenB)):
            tempA = tempA.next
        
        # Search for intersection.
        while tempA and tempB:
            if tempA.val == tempB.val:
                return tempA
            else:
                tempA = tempA.next
                tempB = tempB.next
        return None
        