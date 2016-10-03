/*  
    Rotate List

    Given a list, rotate the list to the right by k places, where k is non-negative.

    For example:
    Given 1->2->3->4->5->NULL and k = 2,
    return 4->5->1->2->3->NULL.
    
    Solution:
    A list with length n.
    Let k = k mod n (which makes k < n).
    List 
        a_0 -> a_1 -> ... -> a_(n-k-1) -> a_(n-k) -> a_(n-k+1) -> ... -> a_(n-1) -> NULL 
    will become
        a_(n-k) -> a_(n-k+1) -> ... -> a_(n-1) -> a_0 -> a_1 -> ... -> a_(n-k-1) -> NULL
    
    Yu Fu, 09/27/2016
 */

public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // If empty list or only one node, return as the same.
        if (head == null || head.next == null) return head;
        // Count length of the list.
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }

        k = k % length;
        if (k == 0) return head;    // if k = 0, the list does not rotate.

        // Put pointer tempHead on a_0
        ListNode tempHead = head;

        // Put pointer cur on a_(n-k-1)
        cur = head;
        for (int i = 0; i < length - k - 1; i++) {
            cur = cur.next;
        }

        // Put pointer newHead on a_(n-k)
        ListNode newHead = cur.next;
        
        // Put pointer cur2 on a_(n-1)
        ListNode cur2 = cur.next;
        while (cur2.next != null) cur2 = cur2.next;

        cur2.next = tempHead;  // a_(n-1) -> a_0
        cur.next = null;       // a_(n-k-1) -> NULL
        return newHead;        // return a_(n-k) as head
    }
}