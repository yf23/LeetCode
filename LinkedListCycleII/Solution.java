/*
    Linked List Cycle II

	Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

	Note: Do not modify the linked list.

	Follow up:
	Can you solve it without using extra space?

	Definition for singly-linked list.

	class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}

	Use Floyd's cycle-finding algorithm. Two pointers: fast and slow.
	Slow pointer moves 1 step once. Fast pointer moves 2 steps once.
	Let L be the length of the cycle.
	When fast pointer takes k iterations to meet the slow pointer,
	2k - k = k = L
	Suppose the start point of cycle is m steps from head.
	Slow pointer stops at position k.
	The length of slow pointer has travled inside cycle is (k - m).
	The length of slow pointer hasn't traveled inside cycle is (L - (k - m)) = m,
	which is the distance from slow pointer to the start point of cycle.

	Therefore, the distance from head of linked list to the start point of cycle
	is equals to the distance from slow pointer to the start point of cycle.

	Set fast pointer to the head, move 1 step once.
	Also make slow pointer move 1 step once.
	The place they meet is the start point of cycle.
    
    Yu Fu, Jan 1 2015
 */


public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }
}