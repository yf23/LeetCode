/*
    Linked List Cycle

    Given a linked list, determine if it has a cycle in it.

    Follow up:
    Can you solve it without using extra space?

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
    They meet if cycle exists.
    
    Yu Fu, Jan 2 2015
 */

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}