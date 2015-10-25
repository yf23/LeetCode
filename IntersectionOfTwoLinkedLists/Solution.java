/*
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

	Definition for singly-linked list:

	public class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
	
	First, get length of two linked list by trasverse through both lists.
	Then, search the part with same length for intersection.
	Skip the starting parts of the longer linked list.

	O(m+n) running time, O(1) space. 2ms Java solution.
	
	Yu Fu, Oct 25 2015
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	// Null case
        if (headA == null || headB == null) { return null; }

        // Count length of both list
        ListNode tempA = headA;
        ListNode tempB = headB;
        int lenA = 0;
        int lenB = 0;
        while (tempA != null) {
            lenA++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            lenB++;
            tempB = tempB.next;
        }

        // Skip starting part of longer list.
        if (lenA > lenB) {
            tempA = headA;
            tempB = headB;
        } else {
            tempA = headB;
            tempB = headA;
        }
        for (int i = 0; i < Math.abs(lenA - lenB); i++) {
            tempA = tempA.next;
        }

        // Search for intersection.
        while (tempA != null && tempB != null) {
            if (tempA.val == tempB.val) { return tempA; }
            else {
                tempA = tempA.next;
                tempB = tempB.next;
            }
        }
        return null;
    }
}