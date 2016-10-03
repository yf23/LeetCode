/*
    Reorder List

    Given a singly linked list L: L0→L1→…→Ln-1→Ln,
    reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

    You must do this in-place without altering the nodes' values.

    For example,
    Given {1,2,3,4}, reorder it to {1,4,2,3}.

    Yu Fu, 09/27/2016
 */

public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        // Find the begging of second half of the list (shorter half).
        ListNode pt1 = head;
        ListNode pt2 = head;
        while (pt2.next != null && pt2.next.next != null) {
            pt1 = pt1.next;
            pt2 = pt2.next.next;
        }
        ListNode secondHalf = pt1.next;
        pt1.next = null;

        // Reverse the second half.
        secondHalf = reverse(secondHalf);

        // Join first half and reversed second half.
        pt1 = head;
        while (secondHalf != null) {
            ListNode temp = secondHalf;
            secondHalf = secondHalf.next;
            temp.next = pt1.next;
            pt1.next = temp;
            pt1 = temp.next;
        }
    }
    
    // Reverse given linked list
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        
        ListNode i = newHead.next;
        ListNode j = i.next;
        ListNode k = j.next;
        while (j != null) {
            k = j.next;
            j.next = newHead.next;
            i.next = k;
            newHead.next = j;
            j = i.next;
        }
        return newHead.next;
    }
}